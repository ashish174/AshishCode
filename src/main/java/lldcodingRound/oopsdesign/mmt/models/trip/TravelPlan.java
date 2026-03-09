package lldcodingRound.oopsdesign.mmt.models.trip;

import lldcodingRound.oopsdesign.mmt.models.accounts.Passenger;
import lldcodingRound.oopsdesign.mmt.models.search.Itenary;

import java.util.List;

public class TravelPlan {
    Itenary itenary;
    List<Passenger> passengerList;
    private long price;
}
