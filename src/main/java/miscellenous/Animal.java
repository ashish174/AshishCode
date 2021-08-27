package miscellenous;

import com.google.common.collect.Sets;
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

    /*List<Integer> integerList  = new ArrayList<>();

    if(integerList.contains(2)){
      System.out.println("mdjfd");
    }

    PostFixEvaluation.class.getPackage();
    System.out.println(PostFixEvaluation.class.getPackage());*/

    Set<Long> f = new HashSet<>();
    f.add(1L);
    f.add(2L);
    f.add(3L);
    f.add(4L);


    Set<Long> l = new HashSet<>();
    l.add(1L);
    l.add(2L);
    Set<Long> diffSet = Sets.difference(f, l);
    Set<Long> diffSet1 = new HashSet<>(diffSet);
    List<Long> diffList = new ArrayList(diffSet);
    System.out.println("DiffSet1 "+diffList);
    for(Long x : diffList){
      System.out.println("Removing "+x);
      diffSet1.remove(x);
    }

    /*for(Long x : diffList){
      System.out.println("Removing "+x);
      diffList.remove(x);
    }*/

    /*int size = diffList.size();
    for(int i=0; i<size; i++){
      System.out.println("Removing "+diffList.get(0));
      diffList.remove(0);
    }*/

    int i=0;
/*
    while(i<diffList.size()){
      System.out.println("Removing "+diffList.get(i));
      diffList.remove(i);
      i++;
    }*/






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

