package miscellenous;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class EnumVerify {
  Logger logger = LoggerFactory.getLogger(EnumVerify.class);


  public static void main(String[] args) {

    //animal.boo();
      System.out.println(LicenseType.UNKNOWN==null);

    /*Date CREATEDATE = new Date();
    System.out.println(CREATEDATE);


    System.out.println("Result"+"Ashis".equals(null));
    List<String> stringList = new ArrayList<>();
    List<Long> longList = new ArrayList<>();
    System.out.println(stringList.isEmpty());
    stringList.add("abc");
    stringList.add("bc");
    longList.add(1L);
    longList.add(2L);
    System.out.println("List: "+longList);
    System.out.println(ReportGenerationStrategy.valueOf(1).name());
    String licenseType = ReportGenerationStrategy.DEVICE.toString();
    System.out.println(ReportGenerationStrategy.valueOf("USER").name());
    System.out.println(ReportGenerationStrategy.USER.getValue()==0L);
    System.out.println("Licenser Type "+licenseType);
    System.out.println("Licenser Type1 "+ReportGenerationStrategy.DEVICE.name());
    System.out.println(ReportGenerationStrategy.USER);
    System.out.println("Device Type is "+ReportGenerationStrategy.DEVICE);
    System.out.println(UUID.randomUUID());
    System.out.println(UUID.randomUUID().toString().replace("-", ""));*/

  }
}
 enum LicenseType {
    DEVICE(0), USER(1), UNKNOWN(null);
    private Integer value;
    private static Map map = new HashMap<>();


    LicenseType(Integer i) {
        this.value = i;
    }

    public Integer getValue(){
        return value;
    }
}

 enum ReportGenerationStrategy {
  DEVICE(0), USER(1);

  private int value;
  private static Map map = new HashMap<>();


   ReportGenerationStrategy(int a) {
    this.value = a;
  }

  public int getValue() {
    return value;
  }

   static{
     for(ReportGenerationStrategy licenseState : ReportGenerationStrategy.values()){
       map.put(licenseState.value, licenseState);
     }
   }

   public static ReportGenerationStrategy valueOf(int licenseState){
     return (ReportGenerationStrategy) map.get(licenseState);
   }
}