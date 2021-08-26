package pojoToJson.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"billingId", "isLicenseManagementEnabled", "licenseType", "disableOverage", "usageRestrictionMode"})
public class LicenseManagementConfig {
    @JsonProperty
    private String billingId;

    @JsonProperty
    private boolean isLicenseManagementEnabled;

    @JsonProperty
    private LicenseType licenseType;

    @JsonProperty
    private UsageRestrictionMode usageRestrictionMode;

    public boolean isDisableOverage() {
        return disableOverage;
    }

    public void setDisableOverage(boolean disableOverage) {
        this.disableOverage = disableOverage;
    }

    private boolean disableOverage;

    public LicenseManagementConfig() {
    }

    public LicenseManagementConfig(String billingId,
                                   boolean isLicenseManagementEnabled,
                                   LicenseType licenseType,
                                   boolean disableRestrictionsForUnknownLicenseType) {
        this.billingId = billingId;
        this.isLicenseManagementEnabled = isLicenseManagementEnabled;
        this.licenseType = licenseType;
        this.setUsageRestrictionMode(isLicenseManagementEnabled, licenseType, disableRestrictionsForUnknownLicenseType);
    }

    private void setUsageRestrictionMode(boolean isUsageRestrictionEnabled, LicenseType licenseType,
                                         boolean disableRestrictionsForUnknownLicenseType) {
        if (!isUsageRestrictionEnabled) {
            this.usageRestrictionMode = UsageRestrictionMode.NO_RESTRICTIONS;
        } else {
            if (licenseType == LicenseType.UNKNOWN) {
                if (disableRestrictionsForUnknownLicenseType) {
                    this.usageRestrictionMode = UsageRestrictionMode.NO_RESTRICTIONS;
                } else {
                    this.usageRestrictionMode = UsageRestrictionMode.FULLY_RESTRICTED;
                }
            } else {
                this.usageRestrictionMode = UsageRestrictionMode.BY_AVAILABILITY;
            }
        }
    }

    public String getBillingId() {
        return billingId;
    }

    @JsonProperty("isLicenseManagementEnabled")
    public boolean isLicenseManagementEnabled() {
        return isLicenseManagementEnabled;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public UsageRestrictionMode getUsageRestrictionMode() {
        return usageRestrictionMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LicenseManagementConfig that = (LicenseManagementConfig) o;

        if (isLicenseManagementEnabled != that.isLicenseManagementEnabled) return false;
        if (!billingId.equals(that.billingId)) return false;
        if (licenseType != that.licenseType) return false;
        return usageRestrictionMode == that.usageRestrictionMode;
    }

    @Override
    public int hashCode() {
        int result = billingId.hashCode();
        result = 31 * result + (isLicenseManagementEnabled ? 1 : 0);
        result = 31 * result + licenseType.hashCode();
        result = 31 * result + usageRestrictionMode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LicenseManagementConfig{" +
                "billingId='" + billingId + '\'' +
                ", isUsageRestrictionEnabled=" + isLicenseManagementEnabled +
                ", licenseType=" + licenseType +
                ", usageRestrictionMode=" + usageRestrictionMode +
                '}';
    }
}
