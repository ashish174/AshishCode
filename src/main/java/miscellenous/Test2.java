package miscellenous;

import com.google.common.collect.Sets;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args) throws ParseException {
        //OveragePreference overagePreference = OveragePreference.fromValue(null);
        Long value1 = 1L;
        String st = null;




       List myNameList = new ArrayList();
       myNameList.add("1");
       myNameList.add("2");
       myNameList.add("3");
       myNameList.add("4");
       myNameList.add("5");
       myNameList.add("6");
       myNameList.add("7");
       myNameList.add("8");
       myNameList.add("9");
       myNameList.add("10");
       myNameList.add("11");

        List list1 = myNameList.subList(0, 3);
        List list2 = myNameList.subList(3, 6);

        String a = "ashish";
        char[] chars = a.toCharArray();
        System.out.println("a : "+a);
        System.out.println("chars : ");
        for (int i =0 ; i < chars.length;  i++){
            System.out.print(chars[i]);
        }

    }
}
