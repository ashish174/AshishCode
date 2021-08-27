package oopsdesign.flipkart.models.notification;

public class EmailNotification extends Notification{
    private long email;

    @Override
    public void send() {
        super.send();
        sendEmail();
    }

    public void sendEmail(){

    }
}
