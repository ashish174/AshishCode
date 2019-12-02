package pojoToJson.models;
///bundlePreference
public class BundlePreference {
    Long organizationId;
    Long bundleId;
    String bundleName;
    Integer preference;

    public Long getOrganizationId() {
        return organizationId;
    }

    public BundlePreference setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public Long getBundleId() {
        return bundleId;
    }

    public BundlePreference setBundleId(Long bundleId) {
        this.bundleId = bundleId;
        return this;
    }

    public String getBundleName() {
        return bundleName;
    }

    public BundlePreference setBundleName(String bundleName) {
        this.bundleName = bundleName;
        return this;
    }

    public Integer getPreference() {
        return preference;
    }

    public BundlePreference setPreference(Integer preference) {
        this.preference = preference;
        return this;
    }
}
