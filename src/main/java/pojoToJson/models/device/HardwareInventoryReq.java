package pojoToJson.models.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dashboardId",
    "chartId",
    "chartFilterInfo",
    "gridFilterInfo"
})
public class HardwareInventoryReq {

    @JsonProperty("dashboardId")
    private String dashboardId;
    @JsonProperty("chartId")
    private String chartId;
    @JsonProperty("chartFilterInfo")
    private ChartFilterInfo chartFilterInfo;
    @JsonProperty("gridFilterInfo")
    private GridFilterInfo gridFilterInfo;

    @JsonProperty("dashboardId")
    public String getDashboardId() {
        return dashboardId;
    }

    @JsonProperty("dashboardId")
    public void setDashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
    }

    @JsonProperty("chartId")
    public String getChartId() {
        return chartId;
    }

    @JsonProperty("chartId")
    public void setChartId(String chartId) {
        this.chartId = chartId;
    }

    @JsonProperty("chartFilterInfo")
    public ChartFilterInfo getChartFilterInfo() {
        return chartFilterInfo;
    }

    @JsonProperty("chartFilterInfo")
    public void setChartFilterInfo(ChartFilterInfo chartFilterInfo) {
        this.chartFilterInfo = chartFilterInfo;
    }

    @JsonProperty("gridFilterInfo")
    public GridFilterInfo getGridFilterInfo() {
        return gridFilterInfo;
    }

    @JsonProperty("gridFilterInfo")
    public void setGridFilterInfo(GridFilterInfo gridFilterInfo) {
        this.gridFilterInfo = gridFilterInfo;
    }

}
