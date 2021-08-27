package oopsdesign.bookmyshow.models;


import oopsdesign.bookmyshow.models.enums.SeatStatus;
import oopsdesign.bookmyshow.models.enums.SeatType;

public class Seat {
    private String number;
    private SeatType type;
    private SeatStatus status;
    private double price;
}
