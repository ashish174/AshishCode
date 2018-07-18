package designpattern.behavioural.visitorpattern;

public class Book implements ItemElement{
  private int price;
  private String isbnnumber;

  public Book(int price, String isbnnumber) {
    this.price = price;
    this.isbnnumber = isbnnumber;
  }

  public int getPrice() {
    return price;
  }

  public String getIsbnnumber() {
    return isbnnumber;
  }

  @Override
  public int accept(ShoppingCartVisitor visitor) {
    return visitor.visit(this);
  }
}
