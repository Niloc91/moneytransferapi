package revolut.micronaut.account.model.request;

public final class TransferRequest {
    private String id;
    private String senderId;
    private String recieverId;
    private double amount;


    public TransferRequest() {
    }

    public TransferRequest(String id, String senderId, String recieverId, double amount) {
        this.id = id;
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.amount = amount;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
