package codingsolutions.webhook;

// StockOrder.java
class StockOrder {
    private String orderId;
    private String symbol;
    private int quantity;
    private double price;
    private String status;

    public StockOrder(String orderId, String symbol, int quantity, double price) {
        this.orderId = orderId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.status = "PENDING";
    }

    public String getOrderId() { return orderId; }
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Order{id='%s', symbol='%s', qty=%d, price=%.2f, status='%s'}",
                orderId, symbol, quantity, price, status);
    }
}