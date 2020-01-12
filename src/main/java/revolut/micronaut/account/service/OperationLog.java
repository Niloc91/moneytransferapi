package revolut.micronaut.account.service;

import java.util.HashMap;
import java.util.Map;

public class OperationLog {

    Map<String,String> operationLog;

    public OperationLog() {
        this.operationLog = new HashMap<>();
    }

    public Operation addOperation(String id, String timestamp){
        operationLog.put(id,timestamp);
        return new Operation(id, timestamp);
    }

    public boolean hasOperation(String id){
        return operationLog.containsKey(id);
    }

    public Operation getOperation(String id){
        return new Operation(id,operationLog.get(id));
    }
}
