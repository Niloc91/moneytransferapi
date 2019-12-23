package revolut.micronaut.account.model.response;

public final class DepositResponseDto {

    private String accountId;
    private String depositTime;
    private double depositAmount;
    private double balance;

    public DepositResponseDto(String accountId, String depositTime, double depositAmount, double balance) {
        this.accountId = accountId;
        this.depositTime = depositTime;
        this.depositAmount = depositAmount;
        this.balance = balance;
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

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "DepositResponseDto{" +
                "accountId='" + accountId + '\'' +
                ", depositAmount=" + depositAmount +
                ", balance=" + balance +
                '}';
    }
}
