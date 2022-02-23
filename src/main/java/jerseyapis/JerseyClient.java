package jerseyapis;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClient {

    private static String url;

    public static void doGet() {
        try {
            url =
                    "http://localhost:19000/20180917/stacks/ocid1.ormstack.dev.dev.aaaaaaaaqvrjqxku3z6we35gliymqw3ox6vjgwjgt7zswvsrjiokqy5tocga/tfConfig";
            Client client = Client.create();
            WebResource webResource = client
                    .resource(url);
            ClientResponse response = webResource.accept("application/zip")
                    .get(ClientResponse.class);
            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n");
            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doGet();
    }
}


