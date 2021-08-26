package pojoToJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pojoToJson.models.Bundle;
import pojoToJson.models.DevicesWrapper;
import pojoToJson.models.device.Data;
import pojoToJson.models.device.HardwareInventoryReq;

import java.io.IOException;

public class JacksonJsonToPojo {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json;
        //json = getJson();
        //DevicesWrapper devicesWrapper = mapper.readValue(json, DevicesWrapper.class);
        //System.out.println("DevicesWrapper:- \n"+devicesWrapper);
        //HardwareInventoryReq hardwareInventoryReq = mapper.readValue(Data.json, HardwareInventoryReq.class);
        //System.out.println("hardwareInventoryReq : "+hardwareInventoryReq);
        json = getBundleJson();
        Bundle bundle = mapper.readValue(json, Bundle.class);
        System.out.println(bundle.getPartType().name());
    }

    public static String getJson(){
        String json = "{\n" +
            "    \"devices\": {\n" +
            "        \"device\": [\n" +
            "            {\n" +
            "                \"maas360DeviceID\": \"Androidf32e71a6721a886c\",\n" +
            "                \"deviceName\": \"ashish189-ANE-AL00\",\n" +
            "                \"customAssetNumber\": \"\",\n" +
            "                \"ownership\": \"Corporate Owned\",\n" +
            "                \"deviceOwner\": \"\",\n" +
            "                \"username\": \"ashish189\",\n" +
            "                \"emailAddress\": \"ashish189@yopmail.com\",\n" +
            "                \"platformName\": \"Android\",\n" +
            "                \"sourceID\": 1,\n" +
            "                \"deviceType\": \"Smartphone\",\n" +
            "                \"manufacturer\": \"\",\n" +
            "                \"model\": \"\",\n" +
            "                \"osName\": \"\",\n" +
            "                \"osServicePack\": \"\",\n" +
            "                \"imeiEsn\": \"\",\n" +
            "                \"installedDate\": \"2020-02-04T10:06:57\",\n" +
            "                \"lastReported\": \"2020-02-06T02:56:53\",\n" +
            "                \"installedDateInEpochms\": 1580810817436,\n" +
            "                \"lastReportedInEpochms\": 1580957813315,\n" +
            "                \"deviceStatus\": \"Active\",\n" +
            "                \"maas360ManagedStatus\": \"Enrolled\",\n" +
            "                \"udid\": \"\",\n" +
            "                \"wifiMacAddress\": \"\",\n" +
            "                \"mailboxDeviceId\": \"\",\n" +
            "                \"mailboxLastReported\": \"\",\n" +
            "                \"mailboxLastReportedInEpochms\": \"\",\n" +
            "                \"mailboxManaged\": \"\",\n" +
            "                \"isSupervisedDevice\": false,\n" +
            "                \"testDevice\": false,\n" +
            "                \"unifiedTravelerDeviceId\": \"Androidf32e71a6721a886c\",\n" +
            "                \"phoneNumber\": \"\",\n" +
            "                \"osVersion\": \"\",\n" +
            "                \"enrollmentMode\": \"ENROLLMENT\",\n" +
            "                \"userDomain\": \"ibm.com\",\n" +
            "                \"firstRegisteredInEpochms\": 1580810817436,\n" +
            "                \"lastRegisteredInEpochms\": 1580810817436,\n" +
            "                \"lastMdmRegisteredInEpochms\": 1580886729872,\n" +
            "                \"policyComplianceState\": \"\",\n" +
            "                \"appComplianceState\": \"\",\n" +
            "                \"ruleComplianceState\": \"\",\n" +
            "                \"selectiveWipeStatus\": \"N/a\",\n" +
            "                \"jailbreakStatus\": \"Not Available\",\n" +
            "                \"encryptionStatus\": \"\",\n" +
            "                \"passcodeCompliance\": \"\",\n" +
            "                \"mdmMailboxDeviceId\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"maas360DeviceID\": \"Androidf64bd20f491ab567\",\n" +
            "                \"deviceName\": \"ashish189-ONEPLUS A6010\",\n" +
            "                \"customAssetNumber\": \"\",\n" +
            "                \"ownership\": \"Not Defined\",\n" +
            "                \"deviceOwner\": \"\",\n" +
            "                \"username\": \"ashish189\",\n" +
            "                \"emailAddress\": \"ashish189@yopmail.com\",\n" +
            "                \"platformName\": \"Android\",\n" +
            "                \"sourceID\": 1,\n" +
            "                \"deviceType\": \"Smartphone\",\n" +
            "                \"manufacturer\": \"\",\n" +
            "                \"model\": \"\",\n" +
            "                \"osName\": \"\",\n" +
            "                \"osServicePack\": \"\",\n" +
            "                \"imeiEsn\": \"\",\n" +
            "                \"installedDate\": \"2020-02-05T09:43:44\",\n" +
            "                \"lastReported\": \"2020-02-05T09:46:00\",\n" +
            "                \"installedDateInEpochms\": 1580895824838,\n" +
            "                \"lastReportedInEpochms\": 1580895960954,\n" +
            "                \"deviceStatus\": \"Active\",\n" +
            "                \"maas360ManagedStatus\": \"Enrolled\",\n" +
            "                \"udid\": \"\",\n" +
            "                \"wifiMacAddress\": \"\",\n" +
            "                \"mailboxDeviceId\": \"\",\n" +
            "                \"mailboxLastReported\": \"\",\n" +
            "                \"mailboxLastReportedInEpochms\": \"\",\n" +
            "                \"mailboxManaged\": \"\",\n" +
            "                \"isSupervisedDevice\": false,\n" +
            "                \"testDevice\": false,\n" +
            "                \"unifiedTravelerDeviceId\": \"Androidf64bd20f491ab567\",\n" +
            "                \"phoneNumber\": \"\",\n" +
            "                \"osVersion\": \"\",\n" +
            "                \"enrollmentMode\": \"ENROLLMENT\",\n" +
            "                \"userDomain\": \"ibm.com\",\n" +
            "                \"firstRegisteredInEpochms\": 1580895824838,\n" +
            "                \"lastRegisteredInEpochms\": 1580895824838,\n" +
            "                \"lastMdmRegisteredInEpochms\": 1580895825122,\n" +
            "                \"policyComplianceState\": \"\",\n" +
            "                \"appComplianceState\": \"\",\n" +
            "                \"ruleComplianceState\": \"\",\n" +
            "                \"selectiveWipeStatus\": \"N/a\",\n" +
            "                \"jailbreakStatus\": \"Not Available\",\n" +
            "                \"encryptionStatus\": \"\",\n" +
            "                \"passcodeCompliance\": \"\",\n" +
            "                \"mdmMailboxDeviceId\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"maas360DeviceID\": \"Android7203a751e384afe5\",\n" +
            "                \"deviceName\": \"ashish180-INE-LX2\",\n" +
            "                \"customAssetNumber\": \"\",\n" +
            "                \"ownership\": \"Corporate Owned\",\n" +
            "                \"deviceOwner\": \"\",\n" +
            "                \"username\": \"ashish180\",\n" +
            "                \"emailAddress\": \"ashish180@yopmail.com\",\n" +
            "                \"platformName\": \"Android\",\n" +
            "                \"sourceID\": 1,\n" +
            "                \"deviceType\": \"Smartphone\",\n" +
            "                \"manufacturer\": \"\",\n" +
            "                \"model\": \"\",\n" +
            "                \"osName\": \"\",\n" +
            "                \"osServicePack\": \"\",\n" +
            "                \"imeiEsn\": \"\",\n" +
            "                \"installedDate\": \"2020-01-31T06:51:52\",\n" +
            "                \"lastReported\": \"2020-02-04T09:46:21\",\n" +
            "                \"installedDateInEpochms\": 1580453512590,\n" +
            "                \"lastReportedInEpochms\": 1580809581803,\n" +
            "                \"deviceStatus\": \"Active\",\n" +
            "                \"maas360ManagedStatus\": \"User Removed Control\",\n" +
            "                \"udid\": \"\",\n" +
            "                \"wifiMacAddress\": \"\",\n" +
            "                \"mailboxDeviceId\": \"\",\n" +
            "                \"mailboxLastReported\": \"\",\n" +
            "                \"mailboxLastReportedInEpochms\": \"\",\n" +
            "                \"mailboxManaged\": \"\",\n" +
            "                \"isSupervisedDevice\": false,\n" +
            "                \"testDevice\": false,\n" +
            "                \"unifiedTravelerDeviceId\": \"Android7203a751e384afe5\",\n" +
            "                \"phoneNumber\": \"\",\n" +
            "                \"osVersion\": \"\",\n" +
            "                \"enrollmentMode\": \"ENROLLMENT\",\n" +
            "                \"userDomain\": \"ibm.com\",\n" +
            "                \"firstRegisteredInEpochms\": 1580453512590,\n" +
            "                \"lastRegisteredInEpochms\": 1580453512590,\n" +
            "                \"lastMdmRegisteredInEpochms\": 1580453512856,\n" +
            "                \"policyComplianceState\": \"\",\n" +
            "                \"appComplianceState\": \"\",\n" +
            "                \"ruleComplianceState\": \"\",\n" +
            "                \"selectiveWipeStatus\": \"N/a\",\n" +
            "                \"jailbreakStatus\": \"Not Available\",\n" +
            "                \"encryptionStatus\": \"\",\n" +
            "                \"passcodeCompliance\": \"\",\n" +
            "                \"mdmMailboxDeviceId\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"maas360DeviceID\": \"Android7ce85da8390f7ed5\",\n" +
            "                \"deviceName\": \"Android7ce85da8390f7ed5-Redmi Note 5 Pro\",\n" +
            "                \"customAssetNumber\": \"\",\n" +
            "                \"ownership\": \"Not Defined\",\n" +
            "                \"deviceOwner\": \"\",\n" +
            "                \"username\": \"ashish176\",\n" +
            "                \"emailAddress\": \"ashish176@yopmail.com\",\n" +
            "                \"platformName\": \"Android\",\n" +
            "                \"sourceID\": 1,\n" +
            "                \"deviceType\": \"Smartphone\",\n" +
            "                \"manufacturer\": \"\",\n" +
            "                \"model\": \"\",\n" +
            "                \"osName\": \"\",\n" +
            "                \"osServicePack\": \"\",\n" +
            "                \"imeiEsn\": \"\",\n" +
            "                \"installedDate\": \"2020-01-30T12:15:29\",\n" +
            "                \"lastReported\": \"2020-02-02T21:37:56\",\n" +
            "                \"installedDateInEpochms\": 1580386529070,\n" +
            "                \"lastReportedInEpochms\": 1580679476141,\n" +
            "                \"deviceStatus\": \"Active\",\n" +
            "                \"maas360ManagedStatus\": \"User Removed Control\",\n" +
            "                \"udid\": \"\",\n" +
            "                \"wifiMacAddress\": \"\",\n" +
            "                \"mailboxDeviceId\": \"\",\n" +
            "                \"mailboxLastReported\": \"\",\n" +
            "                \"mailboxLastReportedInEpochms\": \"\",\n" +
            "                \"mailboxManaged\": \"\",\n" +
            "                \"isSupervisedDevice\": false,\n" +
            "                \"testDevice\": false,\n" +
            "                \"unifiedTravelerDeviceId\": \"Android7ce85da8390f7ed5\",\n" +
            "                \"phoneNumber\": \"\",\n" +
            "                \"osVersion\": \"\",\n" +
            "                \"enrollmentMode\": \"ENROLLMENT\",\n" +
            "                \"userDomain\": \"ibm.com\",\n" +
            "                \"firstRegisteredInEpochms\": 1580386529070,\n" +
            "                \"lastRegisteredInEpochms\": 1580386529070,\n" +
            "                \"lastMdmRegisteredInEpochms\": 1580386538600,\n" +
            "                \"policyComplianceState\": \"\",\n" +
            "                \"appComplianceState\": \"\",\n" +
            "                \"ruleComplianceState\": \"\",\n" +
            "                \"selectiveWipeStatus\": \"N/a\",\n" +
            "                \"jailbreakStatus\": \"Not Available\",\n" +
            "                \"encryptionStatus\": \"\",\n" +
            "                \"passcodeCompliance\": \"\",\n" +
            "                \"mdmMailboxDeviceId\": \"\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"count\": 4,\n" +
            "        \"pageNumber\": 1,\n" +
            "        \"pageSize\": 4\n" +
            "    }\n" +
            "}";
        return json;
    }

    public static String getBundleJson(){
        String json = "{\n" +
            "  \"bundleId\" : 1,\n" +
            "  \"name\" : \"Essentials\",\n" +
            "  \"partNumber\" : \"D1P3GLL\",\n" +
            "  \"partType\" : \"device\",\n" +
            "  \"category\" : \"BASE\",\n" +
            "  \"state\" : \"ACTIVE\",\n" +
            "  \"quantity\" : 10,\n" +
            "  \"consumed\" : 8,\n" +
            "  \"available\" : 2,\n" +
            "  \"overage\" : true\n" +
            "}";
        return json;
    }

}
