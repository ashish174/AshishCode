package miscellenous;

import javax.persistence.criteria.CriteriaBuilder;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Test1 {
    public static void main(String[] args) throws ParseException {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        List<Integer> d = new ArrayList<>();

        //d.add(3);

        a.add(1);
        a.add(2);

        b.add(3);
        b.add(4);

        c.addAll(a);
        c.addAll(b);

        Set<Integer> set1 = new HashSet<>();
        set1.add(2);
        set1.add(1);


        Set<Integer> set2 = new HashSet<>();
        set2.add(9);

        Set<Integer> set3 = Sets.intersection(set2, set1);
        Set<Integer> set4 = Sets.difference(set2, set1);


        System.out.println(set3);
        System.out.println(set4);
        System.out.println("set3 size "+set3.size());
        for(Integer i : set3){
            System.out.println("hiii");
        }

        List<Student> studentList = new ArrayList<>();
        Collections.sort(studentList, ((o1, o2) -> o1.getId()-o2.getId()));
        System.out.println("Student list "+studentList);

       /*
        System.out.println("set1 : " +set1);
        System.out.println("set2 : " +set2);
        System.out.println(set3);
        System.out.println(Sets.difference(set2, set1).size()==0);*/

        Student student1 = new Student(1);
        Student student2 = new Student(2);
        Student student3 = new Student(3);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        System.out.println(studentList);
        System.out.println(studentList);
        Bundle bundle1 = new Bundle(1, "B1", Arrays.asList(1L,2L,3L));
        Bundle bundle2 = new Bundle(2, "B2", Arrays.asList(1L,3L,4L));
        List<Bundle> bundles = new ArrayList<>();
        bundles.add(bundle1);
        bundles.add(bundle2);
        Test1 test1 = new Test1();
        test1.verifyBundleSelectionForDuplicateServiceSet(bundles);

        Integer a5 = new Integer(1);
        boolean b5 = a5 == 1;
        System.out.println("b5 "+b5);

        Integer a6 = new Integer(20);
        String withLeadingZeros = String.format("%8s", Integer.toBinaryString(a6)).replace(' ', '0');
        String b6 = StringUtils.reverse(withLeadingZeros);
        System.out.println("Bit Representation of "+a6+ " : "+withLeadingZeros);
        System.out.println(b6);

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        addToList(intList);
        System.out.println(intList);

        Boolean b8 = null;
        Boolean c8 = BooleanUtils.isFalse(null);
        System.out.println("b8 : " +b8);
        System.out.println("c8 : "+c8);


        List<Integer> integerList1 = Arrays.asList(1,2,3,4,5);
        Integer[] intArr = (Integer[]) integerList1.toArray();
        for(Integer i : intArr){
            System.out.println("intArr : "+i);
        }


        List<Integer> intList1 = new ArrayList<>();
        Set<Integer> integerSet = intList1.stream().map(integer -> integer * 3).collect(Collectors.toSet());
        System.out.println("intList1 : " +intList1);
        System.out.println("integerSet : " +integerSet.size());

        Set<Integer> integerSet2 = new HashSet<>();
        integerSet2.add(1);
        integerSet2.add(2);

        Set<Integer> intersectionSet = Sets.intersection(integerSet2, integerSet);
        Set<Integer> diffSet2 = Sets.difference(integerSet2, integerSet);
        Set<Integer> diffSet = Sets.difference(integerSet, integerSet2);

        System.out.println(intersectionSet.size() +" : "+diffSet2+" : "+diffSet.size());



    }

    static void addToList(List<Integer> integerList){
        integerList.add(5);
    }

    public void verifyBundleSelectionForDuplicateServiceSet(List<Bundle> customerBundles) {
        Map<Long, Integer> serviceCountInSelection = new HashMap<>();
        for(Bundle bundle : customerBundles) {
            List<Long> bundleServices = bundle.getBundleServices();
            bundleServices.stream().forEach(serviceId -> {
                if(serviceCountInSelection.containsKey(serviceId)){
                    serviceCountInSelection.put(serviceId, serviceCountInSelection.get(serviceId)+1);
                } else {
                    serviceCountInSelection.put(serviceId, 1);
                }
            });
        }
        for(Bundle bundle : customerBundles) {
            boolean isbundleContainDuplicateServices = checkIfBundleServicesComingAtLeastTwice(bundle, serviceCountInSelection);
            if (isbundleContainDuplicateServices) {
                System.out.println(bundle.id);
            }
        }
    }

    private boolean checkIfBundleServicesComingAtLeastTwice(Bundle bundle, Map<Long, Integer> serviceCountInSelection) {
        boolean isbundleContainDuplicateServices = true;
        for (Long serviceId : bundle.getBundleServices()) {
            if(serviceCountInSelection.get(serviceId)<2){
                isbundleContainDuplicateServices = false;
            }
        }
        return isbundleContainDuplicateServices;
    }
}
