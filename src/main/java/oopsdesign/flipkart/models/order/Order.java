package oopsdesign.flipkart.models.order;

import oopsdesign.flipkart.models.accounts.AccountUser;
import oopsdesign.flipkart.models.accounts.Address;

public class Order {
    private long id;
    private Cart cart;
    private AccountUser user;
    private OrderStatus status;
    private Address address;
}
