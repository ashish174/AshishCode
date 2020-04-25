package pojoToJson.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DevicesWrapper {
    @JsonProperty("devices")
    private Devices devices;

    public Devices getDevices() {
        return devices;
    }

    @JsonProperty("devices")
    public void setDevices(Devices devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "DevicesWrapper{" +
                "devices=" + devices +
                '}';
    }
}
