package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subscription implements Comparable<Subscription> {

	private Long id;

	private Long bundleId;
	
	private String name;
	
	private SubscriptionCategory category;

	private String provisioningEmail;

	private Long expirationDate;

	private Long effectiveDate;

	private SubscriptionState state;
	
	private Long quantity;

	private Long consumed;
	
	private Long locked;

	private Long available;
	
	private Long usagePreference;

	private String chargeAgreementNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBundleId() {
		return bundleId;
	}

	public void setBundleId(Long bundleId) {
		this.bundleId = bundleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SubscriptionCategory getCategory() { return category; }

	public void setCategory(SubscriptionCategory category) { this.category = category; }

	public String getProvisioningEmail() {
		return provisioningEmail;
	}

	public void setProvisioningEmail(String provisioningEmail) {
		this.provisioningEmail = provisioningEmail;
	}

	public Long getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Long expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Long getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Long effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public SubscriptionState getState() { return state; }

	public void setState(SubscriptionState state) { this.state = state; }

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getConsumed() {
		return consumed;
	}

	public void setConsumed(Long consumed) {
		this.consumed = consumed;
	}

	public Long getLocked() {
		return locked;
	}

	public void setLocked(Long locked) {
		this.locked = locked;
	}

	public Long getAvailable() {
		return available;
	}

	public void setAvailable(Long available) {
		this.available = available;
	}

	public Long getUsagePreference() {
		return usagePreference;
	}

	public void setUsagePreference(Long usagePreference) {
		this.usagePreference = usagePreference;
	}

	public String getChargeAgreementNum() {
		return chargeAgreementNum;
	}

	public void setChargeAgreementNum(String chargeAgreementNum) {
		this.chargeAgreementNum = chargeAgreementNum;
	}

	@Override
	public int compareTo(Subscription o) {
		return this.getId().compareTo(o.getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((consumed == null) ? 0 : consumed.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((locked == null) ? 0 : locked.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((usagePreference == null) ? 0 : usagePreference.hashCode());
		result = prime * result + ((provisioningEmail == null) ? 0 : provisioningEmail.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		if (available == null) {
			if (other.available != null)
				return false;
		} else if (!available.equals(other.available))
			return false;
		if (category != other.category)
			return false;
		if (consumed == null) {
			if (other.consumed != null)
				return false;
		} else if (!consumed.equals(other.consumed))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (locked == null) {
			if (other.locked != null)
				return false;
		} else if (!locked.equals(other.locked))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (usagePreference == null) {
			if (other.usagePreference != null)
				return false;
		} else if (!usagePreference.equals(other.usagePreference))
			return false;
		if (provisioningEmail == null) {
			if (other.provisioningEmail != null)
				return false;
		} else if (!provisioningEmail.equals(other.provisioningEmail))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
        return state == other.state;
    }

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
