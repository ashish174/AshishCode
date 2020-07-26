package restCallToSomeAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import restCallToSomeAPI.models.User;

public class UserClient {
    private static Logger LOGGER = LoggerFactory.getLogger(UserClient.class);
    private static String url = "http://localhost:8080/user";

    public User getRandomUser(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(url, User.class);
        if(responseEntity.getStatusCodeValue()== HttpStatus.OK.value()){
            LOGGER.info("User Fetched -  {}", responseEntity.getBody());
            return responseEntity.getBody();
        } else{
            LOGGER.error("Response Code is {} . Some Exception occurs", responseEntity.getStatusCode());
        }
        return null;
    }
}
