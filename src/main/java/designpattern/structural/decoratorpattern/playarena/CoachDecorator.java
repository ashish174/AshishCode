package designpattern.structural.decoratorpattern.playarena;

public class CoachDecorator extends BookingDecorator {
  
  CourtBooking courtBooking;

  public CoachDecorator(CourtBooking courtBooking) {
    this.courtBooking = courtBooking;
  }

  @Override
  public String getInfo() {
    return courtBooking.getInfo() + "Coach +";
  }

  @Override
  public double getCost() {
    return courtBooking.getCost() + 200;
  }
}
