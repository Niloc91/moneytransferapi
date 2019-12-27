package revolut.micronaut.account.model.request;

public final class BalanceRequest {
    private String accountId;

    public BalanceRequest(String accountId){
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
