package designpattern.structural.decoratorpattern.playarena;

public class GrassCourt extends CourtBooking {
  @Override
  public String getInfo() {
    return "Grass Court +";
  }

  @Override
  public double getCost() {
    return 200;
  }
}
