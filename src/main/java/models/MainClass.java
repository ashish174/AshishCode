package models;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        OveragePreference overagePreference = OveragePreference.DEFAULT;
        Integer setting = null;
        Subscription subscription = new Subscription();
        subscription.setBundleId(1L);
        subscription.setId(123L);
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(subscription);
        Subscriptions subscriptions = new Subscriptions();
        subscriptions.setSubscriptions(subscriptionList);
        subscriptions.setCount(1);
        subscriptions.setRequestId("qeqeqe");
        System.out.println("Subscriptions : \n"+subscriptions);

        System.out.println("Subscription : \n"+subscription);
        /*OveragePreference overagePreference1 = OveragePreference.fromValue(setting);
        System.out.println("OveragePreference "+overagePreference.name());


        List<Long> longList = new ArrayList<>();
        longList.add(1l);
        longList.add(2l);
        System.out.println(longList);*/

    }
}
