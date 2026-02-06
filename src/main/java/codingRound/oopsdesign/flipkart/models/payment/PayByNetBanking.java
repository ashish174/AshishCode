package codingRound.oopsdesign.flipkart.models.payment;

public class PayByNetBanking extends Payment{
    private String username;
    private String pswd;

    @Override
    public void pay() {
        super.pay();
        payByNetBanking(this.username, this.pswd);
    }

    private void payByNetBanking(String username, String pswd) {
    }


}
