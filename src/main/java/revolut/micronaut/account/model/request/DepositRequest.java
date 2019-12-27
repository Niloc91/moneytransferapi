package revolut.micronaut.account.model.request;

public final class DepositRequest {
    private String accountId;
    private double amount;

    public DepositRequest(){

    }

    public DepositRequest(String accountId, double amount) {
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
}
