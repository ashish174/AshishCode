package pojoToJson.models;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bundle implements Comparable<Bundle> {

	private Long bundleId;
	
	private String name;

	private String partNumber;

	private BundlePartType partType;
	
	private BundleCategory category;

	private BundleState state;
	
	private Long quantity;

	private Long consumed;
	
	private Long locked;

	private Long available;

	private boolean overage;

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

	public BundleCategory getCategory() { return category; }

	public void setCategory(BundleCategory category) { this.category = category; }

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public BundlePartType getPartType() {
		return partType;
	}

	public void setPartType(BundlePartType partType) {
		this.partType = partType;
	}

	public BundleState getState() { return state; }

	public void setState(BundleState state) { this.state = state; }

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

	public boolean isOverage() {
		return overage;
	}

	public void setOverage(boolean overage) {
		this.overage = overage;
	}

	@Override
	public int compareTo(Bundle o) {
		return this.getBundleId().compareTo(o.getBundleId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((consumed == null) ? 0 : consumed.hashCode());
		result = prime * result + ((locked == null) ? 0 : locked.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((partType == null) ? 0 : partType.hashCode());
		result = prime * result + ((partNumber == null) ? 0 : partNumber.hashCode());
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
		Bundle other = (Bundle) obj;
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
		if (partNumber == null) {
			if (other.partNumber != null)
				return false;
		} else if (!partNumber.equals(other.partNumber))
			return false;
		if (partType != other.partType)
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (state != other.state)
			return false;
		return true;
	}


}
