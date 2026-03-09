package lldcodingRound.oopsdesign.mmt.models.trip;


import lldcodingRound.oopsdesign.flipkart.models.accounts.Account;
import lldcodingRound.oopsdesign.mmt.models.payment.Payment;

public class Order {
    private TravelPlan travelPlan;
    private Payment payment;
    private OrderStatus status;
    private Account account;
}
