package oopsdesign.mmt.models.trip;


import oopsdesign.flipkart.models.accounts.Account;
import oopsdesign.mmt.models.payment.Payment;

public class Order {
    private TravelPlan travelPlan;
    private Payment payment;
    private OrderStatus status;
    private Account account;
}
