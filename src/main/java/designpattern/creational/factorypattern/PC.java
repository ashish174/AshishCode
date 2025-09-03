package designpattern.creational.factorypattern;

public class PC extends Computer{
  private String ram;
  private String hdd;
  private String cpu;

  public PC(String ram, String hdd, String cpu) {
    this.ram = ram;
    this.hdd = hdd;
    this.cpu = cpu;
  }

  @Override
  public String getRAM() {
    return "PC: "+this.ram;
  }

  @Override
  public String getHDD() {
    return "PC: "+this.hdd;
  }

  @Override
  public String getCPU() {
    return "PC: "+this.cpu;
  }

  @Override
  public String toString() {
    return "PC{" +
            "ram='" + ram + '\'' +
            ", hdd='" + hdd + '\'' +
            ", cpu='" + cpu + '\'' +
            '}';
  }
}
