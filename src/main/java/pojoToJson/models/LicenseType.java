package pojoToJson.models;

import java.util.HashMap;
import java.util.Map;

public enum LicenseType {
  DEVICE(0), USER(1), UNKNOWN(null);

  private Integer value;
  private static Map map = new HashMap<>();


  LicenseType(Integer i) {
    this.value = i;
  }

  public Integer getValue(){
    return value;
  }

  static{
    for(LicenseType licenseType : LicenseType.values()){
      map.put(licenseType.value, licenseType);
    }
  }

  public static LicenseType valueOf(int licenseType){
    return (LicenseType) map.get(licenseType);
  }
}
