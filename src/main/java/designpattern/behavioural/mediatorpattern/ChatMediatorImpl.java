package designpattern.behavioural.mediatorpattern;

import java.util.ArrayList;
import java.util.List;

public class ChatMediatorImpl implements ChatMediator{
  private List<User> users;

  public ChatMediatorImpl() {
    this.users = new ArrayList<>();
  }

  @Override
  public void sendMessage(String msg, User user) {
    for(User receipentUser : users){
      if(!receipentUser.equals(user)){
        receipentUser.receive(msg);
      }
    }
  }

  @Override
  public void addUser(User user) {
    users.add(user);
  }
}
