package revolut.micronaut.account.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import revolut.micronaut.account.model.request.*;
import revolut.micronaut.account.repo.AccountRepo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountServiceTest {

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        Map<String, BigDecimal> data = new HashMap<>();
        data.put("3c93831c-29f4-4fd5-a99e-442482ffaeed", BigDecimal.valueOf(100.00));
        data.put("56af8344-1517-4cc3-8ddc-80b9f2d93d2c", BigDecimal.valueOf(150.00));
        data.put("0030db93-593a-48d7-b85a-57c988460147", BigDecimal.valueOf(250.00));
        data.put("0030db93-593a-48d7-b85a-57c988460148", BigDecimal.valueOf(350.00));
        data.put("0030db93-593a-48d7-b85a-57c988460149", BigDecimal.valueOf(550.00));
        this.accountService = new AccountService(new AccountRepo(data));
    }

    @AfterEach
    void tearDown() {
        this.accountService = new AccountService(new AccountRepo());
    }

    @Test
    void createAccount() {
        assertEquals("CreateResponseDto{accountId='0030db93-593a-48d7-b85a-57c988460149', initialBalance=100.0}",
                this.accountService.createAccount(
                        new CreateRequestDto("0030db93-593a-48d7-b85a-57c988460149", 100.00))
                        .toString()
        );
    }

    @Test
    void getBalance() {
        assertEquals("BalanceResponseDto{accountId='0030db93-593a-48d7-b85a-57c988460149', balance=550.0}",
                this.accountService.getBalance(
                        new BalanceRequestDto("0030db93-593a-48d7-b85a-57c988460149"))
                        .toString()
        );
    }

    @Test
    void deposit() {
        assertEquals("DepositResponseDto{accountId='0030db93-593a-48d7-b85a-57c988460149', depositAmount=100.0, balance=650.0}",
                this.accountService.deposit(
                        new DepositRequestDto("0030db93-593a-48d7-b85a-57c988460149", 100.00))
                        .toString()
        );
    }

    @Test
    void withdraw() {
        assertEquals("WithdrawResponseDto{accountId='0030db93-593a-48d7-b85a-57c988460149', withdrawAmount=100.0, balance=450.0}",
                this.accountService.withdraw(
                        new WithdrawRequestDto("0030db93-593a-48d7-b85a-57c988460149", 100.00))
                        .toString()
        );
    }

    @Test
    void transfer() {
        assertEquals("TransferResponseDto{senderId='0030db93-593a-48d7-b85a-57c988460149', recieverId='3c93831c-29f4-4fd5-a99e-442482ffaeed', amount=100.0}",
                this.accountService.transfer(
                        new TransferRequestDto("3c93831c-29f4-4fd5-a99e-442482ffaeed", "0030db93-593a-48d7-b85a-57c988460149", 100.00))
                        .toString()
        );
    }
}