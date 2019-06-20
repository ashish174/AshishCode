package AshishKart;

import AshishKart.model.products.Cloth;
import AshishKart.model.products.Product;
import AshishKart.model.products.Toy;

public class KartMain {
  public static void main(String[] args) {
    Product levisJeans = new Cloth.ClothBuilder()
        .setName("Jeans").setDescription("size 34 jeans").setPrice(1300)
        .getInstance();
    Product barbiedoll = new Toy.ToyBuilder()
        .setName("BArbie Doll").setDescription("Chinese Doll").setPrice(500)
        .getInstance();


  }
}
