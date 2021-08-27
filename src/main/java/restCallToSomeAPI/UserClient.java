package restCallToSomeAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import restCallToSomeAPI.models.User;

@Component
public class UserClient {
    @Autowired
    private RestTemplate restTemplate;
    //private static Logger LOGGER = LoggerFactory.getLogger(UserClient.class);
    private static String url = "http://localhost:8080/user";



    public static User getRandomUser(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(url, User.class);
        if(responseEntity.getStatusCodeValue()== HttpStatus.OK.value()){
            //LOGGER.info("User Fetched -  {}", responseEntity.getBody());
            return responseEntity.getBody();
        } else{
            //LOGGER.error("Response Code is {} . Some Exception occurs", responseEntity.getStatusCode());
        }
        return null;
    }

    public static void main(String[] args) {
        User randomUser = UserClient.getRandomUser();
    }
}
