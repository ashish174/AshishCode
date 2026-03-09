package lldcodingRound.oopsdesign.flipkart.models.order;

import lldcodingRound.oopsdesign.flipkart.models.accounts.AccountUser;
import lldcodingRound.oopsdesign.flipkart.models.accounts.Address;

public class Order {
    private long id;
    private Cart cart;
    private AccountUser user;
    private OrderStatus status;
    private Address address;

    void createOrder(Cart cart){

    }

    void updateOrder(long orderId){

    }

    void cancelOrder(long orderId){

    }
}
