package pojoToJson.models;

import java.util.HashMap;
import java.util.Map;

public enum BundleCategory {
	BASE(1), ADD_ON(2), SPECIAL_ADD_ON(3);

	private int value;
	private static Map map = new HashMap<>();

	BundleCategory(int i) {
		this.value = i;
	}

	public int getValue(){
		return value;
	}

	static{
		for(BundleCategory subscriptionCategory : BundleCategory.values()){
			map.put(subscriptionCategory.value, subscriptionCategory);
		}
	}

	public static BundleCategory valueOf(int subscriptionCategory){
		return (BundleCategory) map.get(subscriptionCategory);
	}
}
