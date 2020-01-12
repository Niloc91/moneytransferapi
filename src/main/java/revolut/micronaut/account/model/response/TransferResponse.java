package revolut.micronaut.account.model.response;

public final class TransferResponse {
    private String id;
    private String transferTime;

    public TransferResponse(String id, String transferTime) {
        this.id = id;
        this.transferTime = transferTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }
}
