package lldcodingRound.AshishKart;

import lldcodingRound.AshishKart.model.products.Cloth;
import lldcodingRound.AshishKart.model.products.Product;
import lldcodingRound.AshishKart.model.products.Toy;

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
