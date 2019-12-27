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

    @Inject
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
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
        final double balance = accountRepo.addBalance(accountId, depositRequest.getAmount());

        return new DepositResponse(
                accountId,
                Instant.now().toString(),
                depositRequest.getAmount(),
                balance
        );
    }

    public WithdrawResponse withdraw(WithdrawRequest withdrawRequest) {
        final String accountId = withdrawRequest.getAccountId();
        final double balance = accountRepo.subtractBalance(accountId, withdrawRequest.getAmount());

        return new WithdrawResponse(
                accountId,
                Instant.now().toString(),
                withdrawRequest.getAmount(),
                balance
        );
    }

    public TransferResponse transfer(TransferRequest transferRequest) {
        final String receiverId = transferRequest.getRecieverId();
        final String senderId = transferRequest.getSenderId();
        final double amount = transferRequest.getAmount();

        accountRepo.subtractBalance(senderId, amount);
        accountRepo.addBalance(receiverId, amount);

        return new TransferResponse(
                receiverId,
                senderId,
                amount,
                Instant.now().toString()
        );
    }
}
