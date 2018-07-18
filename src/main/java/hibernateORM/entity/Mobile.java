package hibernateORM.entity;

public class Mobile {
  int mid;
  String brand;
  int price;

  public Mobile() {
  }

  public Mobile(int mid, String brand, int price) {
    this.mid = mid;
    this.brand = brand;
    this.price = price;
  }

  public int getMid() {
    return mid;
  }

  public void setMid(int mid) {
    this.mid = mid;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Mobile{" +
        "mid=" + mid +
        ", brand='" + brand + '\'' +
        ", price=" + price +
        '}';
  }
}
