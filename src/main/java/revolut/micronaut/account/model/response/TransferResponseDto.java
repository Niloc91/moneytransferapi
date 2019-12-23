package revolut.micronaut.account.model.response;

public final class TransferResponseDto {
    private String senderId;
    private String recieverId;
    private double amount;
    private String transferTime;

    public TransferResponseDto() {
    }

    public TransferResponseDto(String senderId, String recieverId, double amount, String transferTime) {
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.amount = amount;
        this.transferTime = transferTime;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public String toString() {
        return "TransferResponseDto{" +
                "senderId='" + senderId + '\'' +
                ", recieverId='" + recieverId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
