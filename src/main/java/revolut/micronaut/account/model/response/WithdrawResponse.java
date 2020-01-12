package revolut.micronaut.account.model.response;

public final class WithdrawResponse {
    private String id;
    private String accountId;
    private String withdrawTime;

    public WithdrawResponse(String id, String accountId, String withdrawTime) {
        this.id = id;
        this.accountId = accountId;
        this.withdrawTime = withdrawTime;
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

    public String getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(String withdrawTime) {
        this.withdrawTime = withdrawTime;
    }
}
