package pojoToJson.models.device;

public class Data {
    public static String json = "{\n" +
        "    \"dashboardId\": \"HARDWARE_INVENTORY\",\n" +
        "    \"chartId\": \"BI_IA_MDM_HIOV_DEVICES_BY_OPERATING_SYSTEM_VERSION\",\n" +
        "    \"chartFilterInfo\": {\n" +
        "    \"identifiers\": [\n" +
        "                \"CATEGORY\",\n" +
        "        \t\t\"SUB_CATEGORY\",\n" +
        "        \t\t\"device_count\"\n" +
        "            ],\n" +
        "\t\"filterConditions\": [\n" +
        "                {\n" +
        "                    \"identifier\": \"ORGANIZATION_ID\",\n" +
        "                    \"operator\": \"EQUALS\",\n" +
        "                    \"value\": [\n" +
        "                    \t\t\"30334462\"\n" +
        "                    ],\n" +
        "                    \"comparisonType\": \"STRING\"\n" +
        "                }\n" +
        "            ]\n" +
        "    },\n" +
        "    \"gridFilterInfo\": {\n" +
        "        \"identifiers\": [\n" +
        "            \"Device_Name\",\n" +
        "            \"Username\",\n" +
        "            \"Platform\",\n" +
        "            \"Device_Type\",\n" +
        "            \"Manufacturer\",\n" +
        "            \"Model\",\n" +
        "            \"Operating_System_Version\",\n" +
        "            \"Total_Storage_(GB)\",\n" +
        "            \"Free_Storage_(GB)\",\n" +
        "            \"Managed_Status\",\n" +
        "            \"Last_Reported\",\n" +
        "            \"REMOVABLE_USER_UPN\",\n" +
        "            \"REMOVABLE_MAAS360_DEVICE_ID\",\n" +
        "            \"REMOVABLE_PHONE_NUMBER\",\n" +
        "            \"REMOVABLE_EMAIL_ADDRESS\",\n" +
        "            \"REMOVABLE_IMEI_MEID\",\n" +
        "            \"REMOVABLE_OWNER\",\n" +
        "            \"REMOVABLE_MANAGED_BY\",\n" +
        "            \"REMOVABLE_M360_AGENT_VERSION\",\n" +
        "            \"REMOVABLE_ACTIVESYNC_AGENT\",\n" +
        "            \"REMOVABLE_ACTIVATION_DATE\",\n" +
        "            \"REMOVABLE_WIFI_MAC_ADD\",\n" +
        "            \"REMOVABLE_USER_DOMAIN\"\n" +
        "        ],\n" +
        "        \"filterConditions\": [\n" +
        "            {\n" +
        "                    \"identifier\": \"ORGANIZATION_ID\",\n" +
        "                    \"operator\": \"EQUALS\",\n" +
        "                    \"value\": [\n" +
        "                    \t\"30334462\"\n" +
        "                    ],\n" +
        "                    \"comparisonType\": \"STRING\"\n" +
        "                }\n" +
        "        ]\n" +
        "    }\n" +
        "}";
}
