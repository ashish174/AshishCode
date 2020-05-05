package oopsdesign.mmt.models.trip;

import oopsdesign.mmt.models.accounts.Passenger;
import oopsdesign.mmt.models.search.Itenary;

import java.util.List;

public class TravelPlan {
    Itenary itenary;
    List<Passenger> passengerList;
    private long price;
}
