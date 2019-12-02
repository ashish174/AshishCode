package miscellenous;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@XmlRootElement
public class Animal {

  public Animal() {
  }

  public Animal(String name, String age) {
    this.name = name;
    this.age = age;
  }

  private String name;
  private String age;

  Logger logger = LoggerFactory.getLogger(Animal.class);

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public static void main(String[] args) {
    /*Animal animal = new Animal("ashi", "xyz");
    //String authTemplateXML = createAuthTemplateXML(animal);
    long timeinms = 9999999999999L;
    Date date = new Date(timeinms);
    System.out.println(date);
    //SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm zzz");
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm z");
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    System.out.println(sdf.format(new Date(timeinms)));

    List<Integer> intList = Arrays.asList(1,2,3);
    List<Integer> collect = intList.stream()
        .filter(p -> p == 4)
        .filter(p -> p == 2)
        .collect(Collectors.toList());
    System.out.println(collect==null);
    System.out.println("--"+collect);*/

    String name = "Ashish";
    String name1 = "Ashish";
    System.out.println(name==name1);



  }



  private final static String createAuthTemplateXML(Animal animal)
  {
    String xmlString = null;
    try {
      JAXBContext context = JAXBContext.newInstance(Animal.class);
      Marshaller m = context.createMarshaller();
      StringWriter sw = new StringWriter();
      m.marshal(animal, sw);
      xmlString = sw.toString();
    }
     catch (JAXBException e) {
      e.printStackTrace();
    }
    return xmlString;
  }
}

