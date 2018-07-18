package designpattern.structural.decoratorpattern.playarena;

public class RacketDecorator extends BookingDecorator {
  
  CourtBooking courtBooking;

  public RacketDecorator(CourtBooking courtBooking) {
    this.courtBooking = courtBooking;
  }

  @Override
  public String getInfo() {
    return courtBooking.getInfo() + "Rackets +";
  }

  @Override
  public double getCost() {
    return courtBooking.getCost() + 50;
  }
}
