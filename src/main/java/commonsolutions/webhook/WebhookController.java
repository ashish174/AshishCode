package commonsolutions.webhook;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

    @PostMapping
    public ResponseEntity<String> receive(@RequestBody WebhookPayload payload,
                                          @RequestHeader(value = "X-Signature", required = false) String signature) {
        log.info("Received webhook payload: {}", payload);
        if (signature != null) {
            log.info("Signature header present: {}", signature);
            // TODO: verify signature here if you need authenticity
        }
        return ResponseEntity.ok("Webhook processed successfully");
    }
}*/
