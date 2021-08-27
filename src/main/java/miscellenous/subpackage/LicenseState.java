package miscellenous.subpackage;

import java.util.HashMap;
import java.util.Map;
public enum LicenseState {
    LOCK(0), ASSIGNED(1), UNASSIGNED(2), EXPIRED(3), REMOVED(4), ALL(5);
    private int value;
    private static Map map = new HashMap<>();
    LicenseState(int i) {
        this.value = i;
    }
    public int getValue() {
        return value;
    }
    static {
        for (LicenseState licenseState : LicenseState.values()) {
            map.put(licenseState.value, licenseState);
        }
    }
    public static LicenseState valueOf(int licenseState) {
        return (LicenseState) map.get(licenseState);
    }
    public static void main(String[] args) {
        System.out.println(LicenseState.ASSIGNED.toString());
    }


}