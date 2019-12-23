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

    public CreateResponseDto createAccount(CreateRequestDto createRequestDto) {
        accountRepo.createAccount(createRequestDto.getAccountId(), createRequestDto.getInitialBalance());

        return new CreateResponseDto(
                createRequestDto.getAccountId(),
                createRequestDto.getInitialBalance(),
                Instant.now().toString()
        );
    }

    public BalanceResponseDto getBalance(BalanceRequestDto balanceRequestDto) {
        final String accountId = balanceRequestDto.getAccountId();

        return new BalanceResponseDto(
                accountId,
                Instant.now().toString(),
                accountRepo.getAccountBalance(accountId).doubleValue()
        );
    }

    public DepositResponseDto deposit(DepositRequestDto depositRequestDto) {
        final String accountId = depositRequestDto.getAccountId();
        final double balance = accountRepo.addBalance(accountId, depositRequestDto.getAmount());

        return new DepositResponseDto(
                accountId,
                Instant.now().toString(),
                depositRequestDto.getAmount(),
                balance
        );
    }

    public WithdrawResponseDto withdraw(WithdrawRequestDto withdrawRequestDto) {
        final String accountId = withdrawRequestDto.getAccountId();
        final double balance = accountRepo.subtractBalance(accountId, withdrawRequestDto.getAmount());

        return new WithdrawResponseDto(
                accountId,
                Instant.now().toString(),
                withdrawRequestDto.getAmount(),
                balance
        );
    }

    public TransferResponseDto transfer(TransferRequestDto transferRequestDto) {
        final String receiverId = transferRequestDto.getRecieverId();
        final String senderId = transferRequestDto.getSenderId();
        final double amount = transferRequestDto.getAmount();

        accountRepo.subtractBalance(senderId, amount);
        accountRepo.addBalance(receiverId, amount);

        return new TransferResponseDto(
                receiverId,
                senderId,
                amount,
                Instant.now().toString()
        );
    }
}
