package codingRound.oopsdesign.bookmyshow.models;


import codingRound.oopsdesign.bookmyshow.models.enums.SeatStatus;
import codingRound.oopsdesign.bookmyshow.models.enums.SeatType;

public class Seat {
    private String number;
    private SeatType type;
    private SeatStatus status;
    private double price;
}
