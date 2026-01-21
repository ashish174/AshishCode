package codingsolutions.webhook;

// BrokerClient.java
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

class BrokerClient {
    private String clientId;
    private String webhookUrl;
    private HttpServer webhookServer;

    public BrokerClient(String clientId, int webhookPort) throws IOException {
        this.clientId = clientId;
        this.webhookUrl = "http://localhost:" + webhookPort + "/webhook";

        // Start webhook listener
        startWebhookListener(webhookPort);
    }

    private void startWebhookListener(int port) throws IOException {
        webhookServer = HttpServer.create(new InetSocketAddress(port), 0);
        webhookServer.createContext("/webhook", new WebhookHandler());
        webhookServer.setExecutor(null);
        webhookServer.start();

        System.out.println("Client webhook listener started on port " + port);
    }

    class WebhookHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

                Map<String, String> params = parseQueryString(body);

                // Print order processed message
                System.out.println("\n=== ORDER PROCESSED ===");
                System.out.println("Order ID: " + params.get("orderId"));
                System.out.println("Symbol: " + params.get("symbol"));
                System.out.println("Quantity: " + params.get("quantity"));
                System.out.println("Price: " + params.get("price"));
                System.out.println("Status: " + params.get("status"));
                System.out.println("======================\n");

                // Send response
                String response = "Webhook received";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }

        private Map<String, String> parseQueryString(String query) {
            Map<String, String> result = new HashMap<>();
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                if (pair.length == 2) {
                    result.put(pair[0], URLDecoder.decode(pair[1], StandardCharsets.UTF_8));
                }
            }
            return result;
        }
    }

    public void submitOrder(String exchangeUrl, StockOrder order) {
        try {
            URL url = new URL(exchangeUrl + "/order");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String data = String.format("orderId=%s&symbol=%s&quantity=%d&price=%.2f&webhookUrl=%s",
                    order.getOrderId(), order.getSymbol(), order.getQuantity(),
                    order.getPrice(), URLEncoder.encode(webhookUrl, StandardCharsets.UTF_8));

            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String response = br.readLine();

            System.out.println("Client submitted order: " + order.getOrderId());
            System.out.println("Exchange response: " + response);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        webhookServer.stop(0);
    }

    public static void main(String[] args) throws Exception {
        BrokerClient client = new BrokerClient("CLIENT-001", 9090);

        // Submit some orders
        StockOrder order1 = new StockOrder("ORD-001", "AAPL", 100, 150.50);
        client.submitOrder("http://localhost:8080", order1);

        Thread.sleep(1000);

        StockOrder order2 = new StockOrder("ORD-002", "GOOGL", 50, 2800.75);
        client.submitOrder("http://localhost:8080", order2);

        // Keep running to receive webhooks
        System.out.println("\nClient waiting for webhook notifications...");
    }
}