package models;

import java.util.HashMap;
import java.util.Map;

public enum SubscriptionCategory {
	TRIAL(0), BASE(1), ADD_ON(2), SPECIAL_ADD_ON(3);

	private int value;
	private static Map map = new HashMap<>();

	SubscriptionCategory(int i) {
		this.value = i;
	}

	public int getValue(){
		return value;
	}

	static{
		for(SubscriptionCategory subscriptionCategory : SubscriptionCategory.values()){
			map.put(subscriptionCategory.value, subscriptionCategory);
		}
	}

	public static SubscriptionCategory valueOf(int subscriptionCategory){
		return (SubscriptionCategory) map.get(subscriptionCategory);
	}
}
