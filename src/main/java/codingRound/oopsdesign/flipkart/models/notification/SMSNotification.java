package codingRound.oopsdesign.flipkart.models.notification;

public class SMSNotification extends Notification{
    private long mobile;

    @Override
    public void send() {
        super.send();
        sendSMS();
    }

    private void sendSMS() {
    }
}
