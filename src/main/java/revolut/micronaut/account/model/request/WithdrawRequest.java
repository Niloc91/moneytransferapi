package revolut.micronaut.account.model.request;


public final class WithdrawRequest {
    private String id;
    private String accountId;
    private double amount;


    public WithdrawRequest(){

    }

    public WithdrawRequest(String id, String accountId, double amount) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
