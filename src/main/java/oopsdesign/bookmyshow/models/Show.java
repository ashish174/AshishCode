package oopsdesign.bookmyshow.models;

import oopsdesign.bookmyshow.models.enums.ShowTime;

import java.util.List;

public class Show {
    private Long id;
    private ShowTime showTime;
    private Movie movie;
    private Hall hall;
    private List<ShowSeat> showSeats;
}
