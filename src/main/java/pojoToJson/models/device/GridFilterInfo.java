
package pojoToJson.models.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "identifiers",
    "filterConditions"
})
public class GridFilterInfo {

    @JsonProperty("identifiers")
    private List<String> identifiers = null;
    @JsonProperty("filterConditions")
    private List<FilterCondition> filterConditions = null;

    @JsonProperty("identifiers")
    public List<String> getIdentifiers() {
        return identifiers;
    }

    @JsonProperty("identifiers")
    public void setIdentifiers(List<String> identifiers) {
        this.identifiers = identifiers;
    }

    @JsonProperty("filterConditions")
    public List<FilterCondition> getFilterConditions() {
        return filterConditions;
    }

    @JsonProperty("filterConditions")
    public void setFilterConditions(List<FilterCondition> filterConditions) {
        this.filterConditions = filterConditions;
    }

}
