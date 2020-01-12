package revolut.micronaut.account.service;


import revolut.micronaut.account.model.request.*;
import revolut.micronaut.account.model.response.*;
import revolut.micronaut.account.repo.AccountRepo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class AccountService {

    AccountRepo accountRepo;
    Map<String,String> operationLog;

    @Inject
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
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

    public CreateResponse createAccount(CreateRequest createRequest) {
        accountRepo.createAccount(createRequest.getAccountId(), createRequest.getInitialBalance());

        return new CreateResponse(
                createRequest.getAccountId(),
                createRequest.getInitialBalance(),
                Instant.now().toString()
        );
    }

    public BalanceResponse getBalance(BalanceRequest balanceRequest) {
        final String accountId = balanceRequest.getAccountId();

        return new BalanceResponse(
                accountId,
                Instant.now().toString(),
                accountRepo.getAccountBalance(accountId).doubleValue()
        );
    }

    public DepositResponse deposit(DepositRequest depositRequest) {
        final String accountId = depositRequest.getAccountId();

        if(hasOperation(depositRequest.getId())){
            Operation operation = getOperation(depositRequest.getId());
            return new DepositResponse(
                    operation.getId(),
                    accountId,
                    operation.getTimestamp()
            );
        }

        accountRepo.addBalance(accountId, depositRequest.getAmount());
        Operation createdOperation = addOperation(depositRequest.getId(),Instant.now().toString());

        return new DepositResponse(
                depositRequest.getId(),
                accountId,
                createdOperation.getTimestamp()
        );
    }

    public WithdrawResponse withdraw(WithdrawRequest withdrawRequest) {
        final String accountId = withdrawRequest.getAccountId();

        if(hasOperation(withdrawRequest.getId())){
            Operation operation = getOperation(withdrawRequest.getId());
            return new WithdrawResponse(
                    operation.getId(),
                    accountId,
                    operation.getTimestamp()
            );
        }

        accountRepo.subtractBalance(accountId, withdrawRequest.getAmount());
        Operation createdOperation = addOperation(withdrawRequest.getId(),Instant.now().toString());

        return new WithdrawResponse(
                createdOperation.getId(),
                accountId,
                createdOperation.getTimestamp()
        );
    }

    public TransferResponse transfer(TransferRequest transferRequest) {
        final String receiverId = transferRequest.getRecieverId();
        final String senderId = transferRequest.getSenderId();

        if(receiverId.compareTo(senderId) == 0){
            throw new IllegalArgumentException("Sender id can not be equal to receiver id");
        }

        if(hasOperation(transferRequest.getId())){
            Operation operation = getOperation(transferRequest.getId());
            return new TransferResponse(
                    operation.getId(),
                    operation.getTimestamp()
            );
        }

        final double amount = transferRequest.getAmount();
        accountRepo.subtractBalance(senderId, amount);
        accountRepo.addBalance(receiverId, amount);
        Operation createdOperation = addOperation(transferRequest.getId(),Instant.now().toString());

        return new TransferResponse(
            createdOperation.getId(),
                createdOperation.getTimestamp()
        );
    }
}
