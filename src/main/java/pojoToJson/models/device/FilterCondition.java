
package pojoToJson.models.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "identifier",
    "operator",
    "value",
    "comparisonType"
})
public class FilterCondition {

    @JsonProperty("identifier")
    private String identifier;
    @JsonProperty("operator")
    private String operator;
    @JsonProperty("value")
    private List<String> value = null;
    @JsonProperty("comparisonType")
    private String comparisonType;

    @JsonProperty("identifier")
    public String getIdentifier() {
        return identifier;
    }

    @JsonProperty("identifier")
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @JsonProperty("operator")
    public String getOperator() {
        return operator;
    }

    @JsonProperty("operator")
    public void setOperator(String operator) {
        this.operator = operator;
    }

    @JsonProperty("value")
    public List<String> getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(List<String> value) {
        this.value = value;
    }

    @JsonProperty("comparisonType")
    public String getComparisonType() {
        return comparisonType;
    }

    @JsonProperty("comparisonType")
    public void setComparisonType(String comparisonType) {
        this.comparisonType = comparisonType;
    }

}
