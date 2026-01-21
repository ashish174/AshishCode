/*
 * To run this example:
 *
 * 1. Compile all files:
 *    javac StockOrder.java StockExchangeServer.java BrokerClient.java
 *
 * 2. In one terminal, start the exchange server:
 *    java StockExchangeServer
 *
 * 3. In another terminal, run the broker client:
 *    java BrokerClient
 *
 * The client will submit orders to the exchange, and the exchange will
 * process them and call the client's webhook to notify about completion.
 */