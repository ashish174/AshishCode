package codingsolutions.serversentevents;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.*;

public class StockPriceServer {
    private static final List<OutputStream> clients = new CopyOnWriteArrayList<>();
    private static final Random random = new Random();

    // Event history for replay (stores last 100 events)
    private static final LinkedList<StockEvent> eventHistory = new LinkedList<>();
    private static final int MAX_HISTORY = 100;
    private static long eventIdCounter = 0;

    static class StockEvent {
        long id;
        String event;
        String data;
        long timestamp;

        StockEvent(long id, String event, String data) {
            this.id = id;
            this.event = event;
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // SSE endpoint for stock updates
        server.createContext("/stocks/stream", StockPriceServer::handleSSE);

        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

        System.out.println("SSE Server started on http://localhost:8080");
        System.out.println("Connect to http://localhost:8080/stocks/stream");

        // Start broadcasting stock updates
        startStockUpdates();
    }

    private static void handleSSE(HttpExchange exchange) throws IOException {
        // Check for Last-Event-ID header (sent by client on reconnection)
        String lastEventId = exchange.getRequestHeaders().getFirst("Last-Event-ID");

        // Set SSE headers
        exchange.getResponseHeaders().set("Content-Type", "text/event-stream");
        exchange.getResponseHeaders().set("Cache-Control", "no-cache");
        exchange.getResponseHeaders().set("Connection", "keep-alive");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");

        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();

        // Replay missed events if client reconnected
        if (lastEventId != null) {
            try {
                long lastId = Long.parseLong(lastEventId);
                System.out.println("Client reconnected. Last event ID: " + lastId);
                replayEvents(os, lastId);
            } catch (NumberFormatException e) {
                System.err.println("Invalid Last-Event-ID: " + lastEventId);
            }
        }

        // Add client to list
        clients.add(os);
        System.out.println("Client connected. Total clients: " + clients.size());

        // Send initial connection message
        sendEvent(os, nextEventId(), "connected", "Connection established");

        // Keep connection alive
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(Long.MAX_VALUE);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            clients.remove(os);
            System.out.println("Client disconnected. Total clients: " + clients.size());
        }
    }

    private static void replayEvents(OutputStream os, long lastEventId) throws IOException {
        synchronized (eventHistory) {
            for (StockEvent event : eventHistory) {
                if (event.id > lastEventId) {
                    sendEvent(os, event.id, event.event, event.data);
                }
            }
        }
        System.out.println("Replayed events from ID " + lastEventId);
    }

    private static void startStockUpdates() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        String[] symbols = {"AAPL", "GOOGL", "MSFT", "AMZN", "TSLA"};
        Map<String, Double> prices = new HashMap<>();

        // Initialize prices
        for (String symbol : symbols) {
            prices.put(symbol, 100.0 + random.nextDouble() * 400);
        }

        // Send updates every 2 seconds
        scheduler.scheduleAtFixedRate(() -> {
            for (String symbol : symbols) {
                double currentPrice = prices.get(symbol);
                double change = (random.nextDouble() - 0.5) * 5;
                double newPrice = Math.max(10, currentPrice + change);
                prices.put(symbol, newPrice);

                String data = String.format(
                        "{\"symbol\":\"%s\",\"price\":%.2f,\"change\":%.2f,\"timestamp\":%d}",
                        symbol, newPrice, change, System.currentTimeMillis()
                );

                long eventId = nextEventId();
                StockEvent event = new StockEvent(eventId, "stock-update", data);
                addToHistory(event);

                broadcastToClients(eventId, "stock-update", data);
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    private static synchronized long nextEventId() {
        return ++eventIdCounter;
    }

    private static void addToHistory(StockEvent event) {
        synchronized (eventHistory) {
            eventHistory.add(event);
            if (eventHistory.size() > MAX_HISTORY) {
                eventHistory.removeFirst();
            }
        }
    }

    private static void broadcastToClients(long id, String event, String data) {
        Iterator<OutputStream> it = clients.iterator();
        while (it.hasNext()) {
            OutputStream client = it.next();
            try {
                sendEvent(client, id, event, data);
            } catch (IOException e) {
                it.remove();
                System.out.println("Removed disconnected client");
            }
        }
    }

    private static void sendEvent(OutputStream os, long id, String event, String data) throws IOException {
        String message = String.format("id: %d\nevent: %s\ndata: %s\n\n", id, event, data);
        os.write(message.getBytes());
        os.flush();
    }
}