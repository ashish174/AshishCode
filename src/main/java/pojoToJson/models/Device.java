/**
 *
 */
package pojoToJson.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * This model forms the basis of device attribute used in reg portal
 * @author mangl173
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Device implements Serializable {

    @JsonProperty("maas360DeviceID")
    private String maas360DeviceID;
    @JsonProperty("deviceName")
    private String deviceName;
    @JsonProperty("username")
    private String username;

    public String getDeviceName() {
        return deviceName;
    }

    @JsonProperty("deviceName")
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getMaas360DeviceID() {
        return maas360DeviceID;
    }

    @JsonProperty("maas360DeviceID")
    public void setMaas360DeviceID(String maas360DeviceID) {
        this.maas360DeviceID = maas360DeviceID;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "BaseDeviceAttribute{" +
                "maas360DeviceID='" + maas360DeviceID + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
