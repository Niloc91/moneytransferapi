package revolut.micronaut.account.service;


import revolut.micronaut.account.model.request.*;
import revolut.micronaut.account.model.response.*;
import revolut.micronaut.account.repo.AccountRepo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;

@Singleton
public class AccountService {

    AccountRepo accountRepo;
    OperationLog operationLog;

    @Inject
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
        this.operationLog = new OperationLog();
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

        if(operationLog.hasOperation(depositRequest.getId())){
            Operation operation = operationLog.getOperation(depositRequest.getId());
            return new DepositResponse(
                    operation.getId(),
                    accountId,
                    operation.getTimestamp()
            );
        }

        accountRepo.addBalance(accountId, depositRequest.getAmount());
        Operation createdOperation = operationLog.addOperation(depositRequest.getId(),Instant.now().toString());

        return new DepositResponse(
                depositRequest.getId(),
                accountId,
                createdOperation.getTimestamp()
        );
    }

    public WithdrawResponse withdraw(WithdrawRequest withdrawRequest) {
        final String accountId = withdrawRequest.getAccountId();

        if(operationLog.hasOperation(withdrawRequest.getId())){
            Operation operation = operationLog.getOperation(withdrawRequest.getId());
            return new WithdrawResponse(
                    operation.getId(),
                    accountId,
                    operation.getTimestamp()
            );
        }

        accountRepo.subtractBalance(accountId, withdrawRequest.getAmount());
        Operation createdOperation = operationLog.addOperation(withdrawRequest.getId(),Instant.now().toString());

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

        if(operationLog.hasOperation(transferRequest.getId())){
            Operation operation = operationLog.getOperation(transferRequest.getId());
            return new TransferResponse(
                    operation.getId(),
                    operation.getTimestamp()
            );
        }

        final double amount = transferRequest.getAmount();
        accountRepo.subtractBalance(senderId, amount);
        accountRepo.addBalance(receiverId, amount);
        Operation createdOperation = operationLog.addOperation(transferRequest.getId(),Instant.now().toString());

        return new TransferResponse(
            createdOperation.getId(),
                createdOperation.getTimestamp()
        );
    }
}
