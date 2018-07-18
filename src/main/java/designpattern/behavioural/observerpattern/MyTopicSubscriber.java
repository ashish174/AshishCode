package designpattern.behavioural.observerpattern;

public class MyTopicSubscriber implements Observer{
  private String name;
  private Subject topic;

  public MyTopicSubscriber(String name) {
    this.name = name;
  }

  @Override
  public void update() {
    String msg = topic.getUpdate(this);
    if(msg == null){
      System.out.println(name+" :: No New Message");
    }else {
      System.out.println(name+" :: Consuming Message : "+msg);
    }
  }

  @Override
  public void setSubject(Subject subject) {
    this.topic = subject;
  }
}
