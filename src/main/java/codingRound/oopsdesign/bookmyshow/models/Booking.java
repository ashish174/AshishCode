package codingRound.oopsdesign.bookmyshow.models;

import java.util.List;

public class Booking {
    private Long id;
    private Show show;
    private List<ShowSeat> bookedSeats;
    //private Account account;
    private BookingStatus status;
}
