package lldcodingRound.oopsdesign.bookmyshow.models;


import lldcodingRound.oopsdesign.bookmyshow.models.enums.SeatStatus;
import lldcodingRound.oopsdesign.bookmyshow.models.enums.SeatType;

public class Seat {
    private String number;
    private SeatType type;
    private SeatStatus status;
    private double price;
}
