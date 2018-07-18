package designpattern.structural.adapterpattern;

/*
It’s used so that two unrelated interfaces can work together.
The object that joins these unrelated interface is called an Adapter.
Ex: Mobile Charger is a Adapter Object which connect Normal Socket Object(240V) To Mobile Charging Socket(3V) Object.
Ex: java.util.Arrays#asList()
Ex: java.io.InputStreamReader(InputStream) (returns a Reader)
Ex: java.io.OutputStreamWriter(OutputStream) (returns a Writer)
*/

/*
First build an adapter that can produce 3 volts, 12 volts and default 120 volts.
So first of all we will create an adapter interface with these methods.
2 Approach to implement the Adapter
-Class Adapter - uses inheritance
-Object Adapter - uses composition
*/


public interface SocketAdapter {
  public Volt get120Volt();
  public Volt get12Volt();
  public Volt get3Volt();

}
