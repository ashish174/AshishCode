package oopsdesign.ludo.models;

import java.util.HashMap;
import java.util.Map;

public enum Colour {
    YELLOW(0),
    GREEN(1),
    RED(2),
    BLUE(3);

    int val;
    public static Map map = new HashMap<>();

    Colour(int i) {
        this.val = i;
    }

    int getVal() {
        return this.val;
    }

    public static Colour fromValue(int i){
        return (Colour) map.get(i);
    }

    static {
        for (Colour colour : Colour.values()) {
            map.put(colour.getVal(), colour);
        }

    }


}
