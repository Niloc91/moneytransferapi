package revolut.micronaut.account.model.request;


public final class CreateRequest {

    private String accountId;
    private double initialBalance;

    public CreateRequest(){

    }

    public CreateRequest(String accountId, double initialBalance){
        this.accountId = accountId;
        this.initialBalance = initialBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }
}
