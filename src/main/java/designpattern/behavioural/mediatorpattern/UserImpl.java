package designpattern.behavioural.mediatorpattern;

public class UserImpl extends User{
  public UserImpl(String name, ChatMediator mediator) {
    super(name, mediator);
  }

  @Override
  public void send(String msg) {
    System.out.println(this.name+" : Sending Message : "+msg);
    mediator.sendMessage(msg, this);
  }

  @Override
  public void receive(String msg) {
    System.out.println(this.name+" : Received Message : "+msg);
  }
}
