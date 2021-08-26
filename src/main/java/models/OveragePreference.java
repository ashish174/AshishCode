package models;

import java.util.HashMap;
import java.util.Map;

public enum OveragePreference {
    DEFAULT(null),
    ALLOW(1),
    DENY(0);

    private Integer value;
    private static Map map = new HashMap<>();

    OveragePreference(Integer i) {
        this.value = i;
    }

    public Integer getValue(){
        return value;
    }

    static {
        for(OveragePreference preference : OveragePreference.values()){
            map.put(preference.value, preference);
        }
    }

    public static OveragePreference fromValue(Integer prefValue){
        return (OveragePreference) map.get(prefValue);
    }
}
