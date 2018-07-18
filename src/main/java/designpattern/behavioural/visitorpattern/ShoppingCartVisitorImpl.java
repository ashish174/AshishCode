package designpattern.behavioural.visitorpattern;

public class ShoppingCartVisitorImpl implements ShoppingCartVisitor{
  @Override
  public int visit(Book book) {
    int cost=0;
    //apply 5$ discount if book price is greater than 50
    cost = book.getPrice();
    if(book.getPrice()>50){
      cost = cost-5;
    }
    System.out.println("Book ISBN "+book.getIsbnnumber()+" getCost = "+cost);
    return cost;
  }

  @Override
  public int visit(Fruit fruit) {
    int cost = fruit.getPricePerKg()*fruit.getWeight();
    System.out.println(fruit.getName()+" getCost = "+cost);
    return cost;
  }
}
