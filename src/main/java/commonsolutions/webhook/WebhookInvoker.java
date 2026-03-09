package commonsolutions.webhook;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebhookInvoker implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(WebhookInvoker.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Allow the server to finish starting up (small delay for demo only)
        Thread.sleep(1500);

        WebhookPayload payload = new WebhookPayload("app.started", "The application has just booted.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Signature", "demo-signature");

        HttpEntity<WebhookPayload> entity = new HttpEntity<>(payload, headers);

        String url = "http://localhost:8080/webhook";
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        log.info("Invoker received response: {} {}", response.getStatusCode(), response.getBody());
    }
}*/
