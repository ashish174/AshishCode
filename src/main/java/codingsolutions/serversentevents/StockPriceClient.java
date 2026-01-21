package codingsolutions.serversentevents;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StockPriceClient {
    private static final String SERVER_URL = "http://localhost:8080/stocks/stream";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static String lastEventId = null;  // Track last received event ID

    public static void main(String[] args) {
        System.out.println("Connecting to SSE server...");
        connectToSSE();
    }

    private static void connectToSSE() {
        try {
            URL url = new URL(SERVER_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/event-stream");

            // Send Last-Event-ID on reconnection
            if (lastEventId != null) {
                conn.setRequestProperty("Last-Event-ID", lastEventId);
                System.out.println("Reconnecting with Last-Event-ID: " + lastEventId);
            }

            conn.setDoInput(true);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("✓ Connected to stock price stream\n");
                readStream(conn.getInputStream());
            } else {
                System.err.println("Failed to connect: " + responseCode);
            }

        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
            reconnect();
        }
    }

    private static void readStream(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        String eventId = null;
        String event = null;
        StringBuilder data = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("id:")) {
                eventId = line.substring(4).trim();
            } else if (line.startsWith("event:")) {
                event = line.substring(7).trim();
            } else if (line.startsWith("data:")) {
                data.append(line.substring(6).trim());
            } else if (line.isEmpty()) {
                // End of message
                if (event != null && data.length() > 0) {
                    if (eventId != null) {
                        lastEventId = eventId;  // Store for reconnection
                    }
                    handleEvent(eventId, event, data.toString());
                }
                eventId = null;
                event = null;
                data.setLength(0);
            }
        }
    }

    private static void handleEvent(String id, String event, String data) {
        String time = LocalDateTime.now().format(formatter);

        switch (event) {
            case "connected":
                System.out.println("[" + time + "] " + data + " (Event ID: " + id + ")\n");
                break;

            case "stock-update":
                parseAndDisplayStock(id, data, time);
                break;

            default:
                System.out.println("[" + time + "] Unknown event: " + event);
        }
    }

    private static void parseAndDisplayStock(String id, String json, String time) {
        try {
            String symbol = extractValue(json, "symbol");
            String price = extractValue(json, "price");
            String change = extractValue(json, "change");

            double changeVal = Double.parseDouble(change);
            String arrow = changeVal >= 0 ? "↑" : "↓";
            String color = changeVal >= 0 ? "+" : "";

            System.out.printf("[%s] [ID:%s] %s: $%s %s %s%s%n",
                    time, id, symbol, price, arrow, color, change);

        } catch (Exception e) {
            System.err.println("Error parsing stock data: " + e.getMessage());
        }
    }

    private static String extractValue(String json, String key) {
        String pattern = "\"" + key + "\":";
        int start = json.indexOf(pattern) + pattern.length();
        if (json.charAt(start) == '"') {
            start++;
            int end = json.indexOf("\"", start);
            return json.substring(start, end);
        } else {
            int end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            return json.substring(start, end).trim();
        }
    }

    private static void reconnect() {
        System.out.println("Attempting to reconnect in 3 seconds...");
        try {
            Thread.sleep(3000);
            connectToSSE();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
