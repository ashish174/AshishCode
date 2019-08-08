package pojoToJson;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Licenses {
	//private long count;
	//private String entityIdentifier;
	//private String entityType;
	//private String reportType;
	private List<License> licenses;
	/*
	*//**
	 * @return the count
	 *//*
	public long getCount() {
		return count;
	}
	*//**
	 * @param count the count to set
	 *//*
	public void setCount(long count) {
		this.count = count;
	}
	*//**
	 * @return the entityIdentifier
	 *//*
	public String getEntityIdentifier() {
		return entityIdentifier;
	}
	*//**
	 * @param entityIdentifier the entityIdentifier to set
	 *//*
	public void setEntityIdentifier(String entityIdentifier) {
		this.entityIdentifier = entityIdentifier;
	}
	*//**
	 * @return the entityType
	 *//*
	public String getEntityType() {
		return entityType;
	}
	*//**
	 * @param entityType the entityType to set
	 *//*
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	*//**
	 * @return the reportType
	 *//*
	public String getReportType() {
		return reportType;
	}
	*//**
	 * @param reportType the reportType to set
	 *//*
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	*/
	/**
	 * @return the licenses
	 */
	public List<License> getLicenses() {
		return licenses;
	}
	/**
	 * @param licenses the licenses to set
	 */
	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
	}
}
