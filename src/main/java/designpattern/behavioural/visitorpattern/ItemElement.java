package designpattern.behavioural.visitorpattern;

public interface ItemElement {
  public int accept(ShoppingCartVisitor visitor);
}
