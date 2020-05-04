package oopsdesign.flipkart.models.payment;

public class PayByCard extends Payment{
    private long cardNumber;
    private long cvv;
    private String username;

    @Override
    public void pay() {
        super.pay();
        payByCard(this.cardNumber, this.cvv, this.username);
    }

    void payByCard(long cardNum, long cvv, String userName){

    }
}
