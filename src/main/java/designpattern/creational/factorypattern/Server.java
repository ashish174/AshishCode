package designpattern.creational.factorypattern;

public class Server extends Computer{
  private String ram;
  private String hdd;
  private String cpu;

  public Server(String ram, String hdd, String cpu) {
    this.ram = ram;
    this.hdd = hdd;
    this.cpu = cpu;
  }

  @Override
  public String getRAM() {
    return "Server: "+this.ram;
  }

  @Override
  public String getHDD() {
    return "Server: "+this.hdd;
  }

  @Override
  public String getCPU() {
    return "Server: "+this.cpu;
  }

  @Override
  public String toString() {
    return "Server{" +
            "ram='" + ram + '\'' +
            ", hdd='" + hdd + '\'' +
            ", cpu='" + cpu + '\'' +
            '}';
  }
}
