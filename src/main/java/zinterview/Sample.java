package zinterview;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        //String location = "https://release-condition-service-api.us-ashburn-1.oci.oc-test.com/70e73364-dd86-4f34-bd50-af8ca924655b";
        String location = "93dc6d1d-f4b7-45ee-8b61-ed6ca891587f";
        String[] split = location.split("/");
        System.out.println("ID : "+split[split.length-1]);
        Sample sample = new Sample();
        sample.foo();

    }

    public void foo(){
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println(method);
    }




}
