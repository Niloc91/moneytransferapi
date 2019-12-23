package revolut.micronaut.account.model.response;

public final class CreateResponseDto {
    private String accountId;
    private double initialBalance;
    private String timeCreated;

    public CreateResponseDto(String accountId, double initialBalance, String timeCreated) {
        this.accountId = accountId;
        this.initialBalance = initialBalance;
        this.timeCreated = timeCreated;
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

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public String toString() {
        return "CreateResponseDto{" +
                "accountId='" + accountId + '\'' +
                ", initialBalance=" + initialBalance +
                '}';
    }
}
