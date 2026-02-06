package codingRound.oopsdesign.mmt.models.trip;


import codingRound.oopsdesign.flipkart.models.accounts.Account;
import codingRound.oopsdesign.mmt.models.payment.Payment;

public class Order {
    private TravelPlan travelPlan;
    private Payment payment;
    private OrderStatus status;
    private Account account;
}
