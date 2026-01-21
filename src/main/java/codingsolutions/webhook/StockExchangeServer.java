package codingsolutions.webhook;

// StockExchangeServer.java
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

class StockExchangeServer {
    private HttpServer server;
    private ExecutorService executor;

    public void start(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        executor = Executors.newFixedThreadPool(10);

        server.createContext("/order", new OrderHandler());
        server.setExecutor(executor);
        server.start();

        System.out.println("Stock Exchange Server started on port " + port);
    }

    public void stop() {
        server.stop(0);
        executor.shutdown();
    }

    class OrderHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                // Read request body
                InputStream is = exchange.getRequestBody();
                String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);

                // Parse order details
                Map<String, String> params = parseQueryString(requestBody);
                String orderId = params.get("orderId");
                String symbol = params.get("symbol");
                int quantity = Integer.parseInt(params.get("quantity"));
                double price = Double.parseDouble(params.get("price"));
                String webhookUrl = params.get("webhookUrl");

                System.out.println("Exchange received order: " + orderId + " for " + symbol);

                // Send immediate response
                String response = "Order received: " + orderId;
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

                // Process order asynchronously and call webhook
                processOrderAsync(orderId, symbol, quantity, price, webhookUrl);
            }
        }

        private void processOrderAsync(String orderId, String symbol, int qty,
                                       double price, String webhookUrl) {
            executor.submit(() -> {
                try {
                    // Simulate processing time
                    Thread.sleep(2000);

                    System.out.println("Exchange processed order: " + orderId);

                    // Call webhook
                    callWebhook(webhookUrl, orderId, symbol, qty, price, "PROCESSED");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        private void callWebhook(String webhookUrl, String orderId, String symbol,
                                 int qty, double price, String status) {
            try {
                URL url = new URL(webhookUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String data = String.format("orderId=%s&symbol=%s&quantity=%d&price=%.2f&status=%s",
                        orderId, symbol, qty, price, status);

                OutputStream os = conn.getOutputStream();
                os.write(data.getBytes());
                os.flush();

                int responseCode = conn.getResponseCode();
                System.out.println("Webhook called for order " + orderId +
                        ", response: " + responseCode);

                conn.disconnect();
            } catch (Exception e) {
                System.err.println("Failed to call webhook: " + e.getMessage());
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

    public static void main(String[] args) throws IOException {
        StockExchangeServer server = new StockExchangeServer();
        server.start(8080);
    }
}