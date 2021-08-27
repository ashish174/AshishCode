package oopsdesign.mmt.models.flight;

import java.util.Date;
import java.util.List;

public class Flight {
    private long id;
    private long flightNumber;
    private String source;
    private String destination;
    private Date startTime;
    private Date finishTime;
    private long duration;
    private AirLine airLine;
    private List<Seat> seats;
    private int numberOfSeatsAvailable;
}
