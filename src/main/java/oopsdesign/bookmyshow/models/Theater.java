package oopsdesign.bookmyshow.models;

import oopsdesign.flipkart.models.accounts.Address;

import java.util.List;

public class Theater {
    private Long id;
    private String name;
    private City city;
    private Address address;
    private List<Hall> halls;
}
