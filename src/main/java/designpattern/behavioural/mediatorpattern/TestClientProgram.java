package designpattern.behavioural.mediatorpattern;


import designpattern.behavioural.templatemethodpattern.GlassHouse;
import designpattern.behavioural.templatemethodpattern.HouseTemplate;
import designpattern.behavioural.templatemethodpattern.WoodenHouse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class TestClientProgram {
  private static Logger logger = LogManager.getLogger(TestClientProgram.class);
  public static void main(String[] args) {
    ChatMediator mediator = new ChatMediatorImpl();
    User user1 = new UserImpl("Ashish", mediator);
    User user2 = new UserImpl("Anshu", mediator);
    User user3 = new UserImpl("Raju", mediator);
    User user4 = new UserImpl("Mukesh", mediator);

    mediator.addUser(user1);
    mediator.addUser(user2);
    mediator.addUser(user3);
    mediator.addUser(user4);

    user1.send("Hii All");
  }

}
