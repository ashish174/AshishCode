package miscellenous;

import java.util.List;

public class Bundle {
    int id;
    String name;
    List<Long> bundleServices;

    public Bundle(int id, String name, List<Long> bundleServices) {
        this.id = id;
        this.name = name;
        this.bundleServices = bundleServices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getBundleServices() {
        return bundleServices;
    }

    public void setBundleServices(List<Long> bundleServices) {
        this.bundleServices = bundleServices;
    }
}
