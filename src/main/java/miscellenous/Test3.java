package miscellenous;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Test3 {

    enum ConfigJobType {
        RUN_PLAYBOOK,
        CHECK_PLAYBOOK,
        INVENTORY_SYNC;
    }

    private void changeEnum(ConfigJobType configJobType) {
        configJobType = ConfigJobType.RUN_PLAYBOOK;
        System.out.println(configJobType);
        System.out.println(configJobType);
        configJobType = ConfigJobType.INVENTORY_SYNC;

    }
    public static void main(String[] args) throws ParseException {
        ConfigJobType configJobType = ConfigJobType.CHECK_PLAYBOOK;
        System.out.println(configJobType);


    }
}
