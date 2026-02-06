package codingRound.oopsdesign.mmt.models.search;

import com.google.common.collect.Sets;
import codingRound.oopsdesign.mmt.models.flight.Flight;

import java.util.*;

public class Catalogue {
    List<Flight> flights;
    static Map<Date, List<Flight>> flightsByDate;
    static Map<String, List<Flight>> flightsBySrc;
    static Map<String, List<Flight>> flightsByDst;

    static {
        flightsByDate = new HashMap();
        flightsBySrc = new HashMap<>();
        flightsByDst = new HashMap<>();
    }

    List<Itenary> searchFlights(String src, String dst, Date date){
        List<Flight> flightsByDate = Catalogue.flightsByDate.get(date);
        List<Flight> flightsBySrc = Catalogue.flightsBySrc.get(src);
        List<Flight> flightsByDst = Catalogue.flightsByDst.get(dst);

        Set<Flight> flightsByDateSet = new HashSet<>(flightsByDate);
        Set<Flight> flightsBySrcSet = new HashSet<>(flightsBySrc);
        Set<Flight> flightsByDstSet = new HashSet<>(flightsByDst);


        List<Itenary> itenaries = getItenary(flightsByDateSet, flightsBySrcSet, flightsByDstSet);
        return null;
    }

    private List<Itenary> getItenary(Set<Flight> flightsByDateSet, Set<Flight> flightsBySrcSet, Set<Flight> flightsByDstSet) {
        List<Itenary> itenaries = new ArrayList<>();

        List<Itenary> directFlightsItenary = getDirectFlightsItenary(flightsByDateSet, flightsBySrcSet, flightsByDstSet);
        List<Itenary> connectingFlightsItenary = getConnectingFlightsItenary(flightsByDateSet, flightsBySrcSet, flightsByDstSet);
        itenaries.addAll(directFlightsItenary);
        itenaries.addAll(connectingFlightsItenary);
        return itenaries;
    }

    private List<Itenary> getConnectingFlightsItenary(Set<Flight> flightsByDateSet, Set<Flight> flightsBySrcSet, Set<Flight> flightsByDstSet) {
        return null;
    }

    private List<Itenary> getDirectFlightsItenary(Set<Flight> flightsByDateSet, Set<Flight> flightsBySrcSet, Set<Flight> flightsByDstSet) {
        Set<Flight> flightsWithSameSrcDst = Sets.intersection(flightsBySrcSet, flightsByDstSet);
        Set<Flight> directFlightsWithDateSrcDst = Sets.intersection(flightsWithSameSrcDst, flightsByDateSet);
        return generateItenaryList(directFlightsWithDateSrcDst);
    }

    private List<Itenary> generateItenaryList(Set<Flight> directFlightsWithDateSrcDst) {
        List<Itenary> itenaries = new ArrayList<>();
        for(Flight flight : directFlightsWithDateSrcDst){
            Itenary itenary = generateItenary(Arrays.asList(flight));
            itenaries.add(itenary);
        }
        return itenaries;
    }

    private Itenary generateItenary(List<Flight> flights){
        Itenary itenary = new Itenary();
        int numOfFlight = flights.size();
        itenary.setFlights(flights);
        if(numOfFlight==1){
            itenary.setDirectFlight(true);
        }
        //itenary.setStartTime();
        //itenary.setFinishTime();
        //itenary.setDuration();
        //itenary.setWaitingTime();
        return itenary;
    }
}
