package codingRound.oopsdesign.mmt.models.trip;

import codingRound.oopsdesign.mmt.models.accounts.Passenger;
import codingRound.oopsdesign.mmt.models.search.Itenary;

import java.util.List;

public class TravelPlan {
    Itenary itenary;
    List<Passenger> passengerList;
    private long price;
}
