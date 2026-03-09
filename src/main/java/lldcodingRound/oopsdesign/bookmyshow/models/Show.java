package lldcodingRound.oopsdesign.bookmyshow.models;

import lldcodingRound.oopsdesign.bookmyshow.models.enums.ShowTime;

import java.util.List;

public class Show {
    private Long id;
    private ShowTime showTime;
    private Movie movie;
    private Hall hall;
    private List<ShowSeat> showSeats;
}
