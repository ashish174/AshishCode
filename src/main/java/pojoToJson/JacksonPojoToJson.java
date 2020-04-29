package pojoToJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pojoToJson.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JacksonPojoToJson {



  public static void main(String[] args) {
    // Create ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    String json;
    Student s1 = new Student(1, "Ashish");
    Student s2 = new Student(2, "Ashu");
    List<Student> studentList = Arrays.asList(s1, s2);

    Students studentObj = new Students();
    studentObj.setStudents(studentList);

    License license = new License();
    List<License> licenseList = Arrays.asList(license);
    Licenses licenses = new Licenses();
    licenses.setLicenses(licenseList);
    BundlePreference bundlePreference = new BundlePreference().setBundleId(1L)
        .setBundleName("EnterPrise")
        .setPreference(1)
        .setOrganizationId(12345L);

      BundlePreference bundlePreference2 = new BundlePreference().setBundleId(4L)
          .setBundleName("Deluxe")
          .setPreference(2)
          .setOrganizationId(12345L);

      BundlePreferences bundlePreferences = new BundlePreferences();
      bundlePreferences.setBundlePreferences(Arrays.asList(bundlePreference, bundlePreference2));

      Bundle b1 = new Bundle();
      b1.setBundleId(1L);
      b1.setState(BundleState.ACTIVE);
      b1.setName("Essentials");
      b1.setAvailable(2L);
      b1.setConsumed(8L);
      b1.setQuantity(10L);
      b1.setCategory(BundleCategory.BASE);
      b1.setPartNumber("D1P3GLL");
      b1.setPartType(BundlePartType.DEVICE);
      b1.setOverage(true);
      Bundles b = new Bundles();
      List<Bundle> bundles = new ArrayList<>();
      bundles.add(b1);
      b.setBundles(bundles);
      b.setCount(1);
      b.setReportType(BundlePartType.DEVICE.name());
      b.setRequestId("QWERTYGDRTYG56ED");
      b.setEntityIdentifier("12346567");
      b.setEntityType("ACCOUNT");
    {
      try {
        json = mapper.writeValueAsString(studentObj);
        //System.out.println(json);
        json = mapper.writeValueAsString(licenses);
        //System.out.println(json);
        json = mapper.writeValueAsString(bundlePreferences);
        //System.out.println(json);
        json = mapper.writeValueAsString(b);
        System.out.println(json);


      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    }
  }


}

