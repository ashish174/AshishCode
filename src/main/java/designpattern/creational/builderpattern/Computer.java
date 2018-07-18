package designpattern.creational.builderpattern;

/*
when the Object contains a lot of attributes. Problem - arguement types, order of attributes, optional attributes
If any attribute is not set, it may result in inconsistent state.
Builder pattern solves the issue with large number of optional parameters and inconsistent state
by providing a way to build the object step-by-step
and provide a method that will actually return the final Object.
Each Step build and return Builder Object
Finally a build() method in the Builder convert Builder Object into  Actual Computer Object needed by client program.
*/

public class Computer {
  //Mandatory parameters
  private String hdd;
  private String ram;
  //optional parameters
  private boolean isGraphicsCardEnabled;
  private boolean isBluetoothEnabled;

  public Computer(ComputerBuilder builder) {
    this.hdd = builder.hdd;
    this.ram = builder.ram;
    this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
    this.isBluetoothEnabled = builder.isBluetoothEnabled;
  }

  public String getHdd() {
    return hdd;
  }

  public String getRam() {
    return ram;
  }

  public boolean isGraphicsCardEnabled() {
    return isGraphicsCardEnabled;
  }

  public boolean isBluetoothEnabled() {
    return isBluetoothEnabled;
  }

  @Override
  public String toString() {
    return "Computer{" +
        "hdd='" + hdd + '\'' +
        ", ram='" + ram + '\'' +
        ", isGraphicsCardEnabled=" + isGraphicsCardEnabled +
        ", isBluetoothEnabled=" + isBluetoothEnabled +
        '}';
  }




  public static class ComputerBuilder {
    //Mandatory parameters (All mandatory arguement should be in Constructor)
    private String hdd;
    private String ram;
    //optional parameters (all optional parameter will be through setter whose return type is Builder Object)
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;

    public ComputerBuilder(String hdd, String ram) {
      this.hdd = hdd;
      this.ram = ram;
    }

    public ComputerBuilder setGraphicsCardEnabled(boolean graphicsCardEnabled) {
      isGraphicsCardEnabled = graphicsCardEnabled;
      return this;
    }

    public ComputerBuilder setBluetoothEnabled(boolean bluetoothEnabled) {
      isBluetoothEnabled = bluetoothEnabled;
      return this;
    }

    public Computer build(){
      return new Computer(this);
    }
  }

}
