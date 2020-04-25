package pojoToJson.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Devices implements Serializable {

    private static final long serialVersionUID = 8802130838868650683L;

    @JsonProperty("device")
    private List<Device> deviceList;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("pageNumber")
    private Integer pageNumber;
    @JsonProperty("pageSize")
    private Integer pageSize;

    public List<Device> getDeviceList() {
        return deviceList;
    }

    @JsonProperty("device")
    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("pageNumber")
    public Integer getPageNumber() {
        return pageNumber;
    }

    @JsonProperty("pageNumber")
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty("pageSize")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Devices{" +
            "deviceList=" + deviceList +
            ", count=" + count +
            ", pageNumber=" + pageNumber +
            ", pageSize=" + pageSize +
            '}';
    }
}
