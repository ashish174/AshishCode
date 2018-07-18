package designpattern.structural.decoratorpattern.playarena;

public class HardCourt extends CourtBooking {
  @Override
  public String getInfo() {
    return "Hard Court +";
  }

  @Override
  public double getCost() {
    return 100;
  }
}
