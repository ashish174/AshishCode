package designpattern.structural.decoratorpattern.playarena;

public class ClayCourt extends CourtBooking {
  @Override
  public String getInfo() {
    return "Clay Court +";
  }

  @Override
  public double getCost() {
    return 150;
  }
}
