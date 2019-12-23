package revolut.micronaut.account.model.request;

public final class BalanceRequestDto {
    private String accountId;

    public BalanceRequestDto(String accountId){
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
