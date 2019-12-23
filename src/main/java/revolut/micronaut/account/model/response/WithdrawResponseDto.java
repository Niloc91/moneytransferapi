package revolut.micronaut.account.model.response;

public final class WithdrawResponseDto {
    private String accountId;
    private String withdrawTime;
    private double withdrawAmount;
    private double balance;

    public WithdrawResponseDto(String accountId, String withdrawTime, double withdrawAmount, double balance) {
        this.accountId = accountId;
        this.withdrawTime = withdrawTime;
        this.withdrawAmount = withdrawAmount;
        this.balance = balance;
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

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WithdrawResponseDto{" +
                "accountId='" + accountId + '\'' +
                ", withdrawAmount=" + withdrawAmount +
                ", balance=" + balance +
                '}';
    }
}
