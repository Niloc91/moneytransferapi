package revolut.micronaut.account.model.response;

public final class DepositResponse {
    private String id;
    private String accountId;
    private String depositTime;

    public DepositResponse(String id, String accountId, String depositTime) {
        this.id = id;
        this.accountId = accountId;
        this.depositTime = depositTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(String depositTime) {
        this.depositTime = depositTime;
    }
}
