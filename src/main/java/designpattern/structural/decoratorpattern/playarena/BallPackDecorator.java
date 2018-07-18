package designpattern.structural.decoratorpattern.playarena;

public class BallPackDecorator extends BookingDecorator {
  
  CourtBooking courtBooking;

  public BallPackDecorator(CourtBooking courtBooking) {
    this.courtBooking = courtBooking;
  }

  @Override
  public String getInfo() {
    return courtBooking.getInfo() + "BallPack +";
  }

  @Override
  public double getCost() {
    return courtBooking.getCost() + 75;
  }
}
