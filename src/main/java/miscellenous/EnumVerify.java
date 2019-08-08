package miscellenous;

import java.util.*;

public class EnumVerify {
  public static void main(String[] args) {
    Date CREATEDATE = new Date();
    System.out.println(CREATEDATE);

    List<String> stringList = new ArrayList<>();
    System.out.println(stringList.isEmpty());
    System.out.println(ReportGenerationStrategy.valueOf(1).name());
    String licenseType = ReportGenerationStrategy.DEVICE.toString();
    System.out.println(ReportGenerationStrategy.valueOf("USER").name());
    System.out.println(ReportGenerationStrategy.USER.getValue()==0L);
    System.out.println("Licenser Type "+licenseType);
    System.out.println("Licenser Type1 "+ReportGenerationStrategy.DEVICE.name());
    System.out.println(ReportGenerationStrategy.USER);
    System.out.println("Device Type is "+ReportGenerationStrategy.DEVICE);
    System.out.println(UUID.randomUUID());
    System.out.println(UUID.randomUUID().toString().replace("-", ""));

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