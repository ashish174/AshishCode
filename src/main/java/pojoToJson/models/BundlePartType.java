package pojoToJson.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BundlePartType {
	@JsonProperty("device")
	DEVICE,
	@JsonProperty("user")
	USER;
}
