package revolut.micronaut.account.model.response;


public final class BalanceResponse {
    private String accountId;
    private String timeUtc;
    private double balance;

    public BalanceResponse(String accountId,
                           String timeUtc,
                           double balance) {
        this.accountId = accountId;
        this.timeUtc = timeUtc;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTimeUtc() {
        return timeUtc;
    }

    public void setTimeUtc(String timeUtc) {
        this.timeUtc = timeUtc;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BalanceResponse{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                '}';
    }
}
