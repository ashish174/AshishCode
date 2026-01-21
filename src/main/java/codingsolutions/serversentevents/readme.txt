// HOW RESUMPTION WORKS:
//
// 1. Server sends each event with a unique ID:
//    id: 42
//    event: stock-update
//    data: {...}
//
// 2. Client stores the last received ID
//
// 3. On reconnection, client sends Last-Event-ID header
//
// 4. Server replays all events after that ID from history
//
// TEST THE RESUMPTION:
// 1. Start server: java StockPriceServer
// 2. Start client: java StockPriceClient
// 3. Let it run for a few seconds
// 4. Kill the client (Ctrl+C)
// 5. Restart client - it will resume from last event!
//
// Output example:
// Reconnecting with Last-Event-ID: 23
// ✓ Connected to stock price stream
// [14:23:45] [ID:24] AAPL: $152.34 ↑ +1.23  <- Resumed!
// [14:23:45] [ID:25] GOOGL: $98.76 ↓ -0.45