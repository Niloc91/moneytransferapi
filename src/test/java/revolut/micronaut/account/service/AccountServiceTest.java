package revolut.micronaut.account.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
    private AccountRepo accountRepo;

    @BeforeEach
    void setUp() {
        Map<String, BigDecimal> data = new HashMap<>();
        data.put("3c93831c-29f4-4fd5-a99e-442482ffaeed", BigDecimal.valueOf(100.00));
        data.put("56af8344-1517-4cc3-8ddc-80b9f2d93d2c", BigDecimal.valueOf(150.00));
        data.put("0030db93-593a-48d7-b85a-57c988460147", BigDecimal.valueOf(250.00));
        data.put("0030db93-593a-48d7-b85a-57c988460148", BigDecimal.valueOf(350.00));
        data.put("0030db93-593a-48d7-b85a-57c988460149", BigDecimal.valueOf(550.00));

        this.accountRepo = new AccountRepo(data);
        this.accountService = new AccountService(this.accountRepo);
    }

    @AfterEach
    void tearDown() {
        this.accountService = new AccountService(new AccountRepo());
    }

    @Test
    void createAccountWithInitialBalance() {
        this.accountService.createAccount(new CreateRequest("0030db93-593a-48d7-b85a-57c988460149", 100.00));
        assertEquals(BigDecimal.valueOf(100.00),this.accountRepo.getAccountBalance("0030db93-593a-48d7-b85a-57c988460149"));
    }

    @Test
    void getBalance() {
        assertEquals(550.00,this.accountService.getBalance(
                new BalanceRequest("0030db93-593a-48d7-b85a-57c988460149"))
                .getBalance());
    }

    @Test
    void deposit() {
        this.accountService.deposit(new DepositRequest("5539adb4-df56-4865-b002-969062a00a8a","0030db93-593a-48d7-b85a-57c988460149", 100.00));
        assertEquals(BigDecimal.valueOf(650.00),this.accountRepo.getAccountBalance("0030db93-593a-48d7-b85a-57c988460149"));
    }

    @Test
    void depositMultipleWithSameId() {
        this.accountService.deposit(new DepositRequest("5539adb4-df56-4865-b002-969062a00a8a","0030db93-593a-48d7-b85a-57c988460149", 100.00));
        this.accountService.deposit(new DepositRequest("5539adb4-df56-4865-b002-969062a00a8a","0030db93-593a-48d7-b85a-57c988460149", 100.00));
        this.accountService.deposit(new DepositRequest("5539adb4-df56-4865-b002-969062a00a8a","0030db93-593a-48d7-b85a-57c988460149", 100.00));
        this.accountService.deposit(new DepositRequest("5539adb4-df56-4865-b002-969062a00a8a","0030db93-593a-48d7-b85a-57c988460149", 100.00));
        assertEquals(BigDecimal.valueOf(650.00),this.accountRepo.getAccountBalance("0030db93-593a-48d7-b85a-57c988460149"));
    }

    @Test
    void withdraw() {
        this.accountService.withdraw(new WithdrawRequest("868f7515-c5c3-468a-9535-b3026c519843","0030db93-593a-48d7-b85a-57c988460149", 100.00));
        assertEquals(BigDecimal.valueOf(450.00),this.accountRepo.getAccountBalance("0030db93-593a-48d7-b85a-57c988460149"));
    }

    @Test
    void transfer() {
        this.accountService.transfer(new TransferRequest("dc9d4e83-51d3-4040-8d8d-ff06e0bf26cd","3c93831c-29f4-4fd5-a99e-442482ffaeed", "0030db93-593a-48d7-b85a-57c988460149", 100.00));

        assertEquals(BigDecimal.valueOf(0.00),this.accountRepo.getAccountBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed"));
        assertEquals(BigDecimal.valueOf(650.00),this.accountRepo.getAccountBalance("0030db93-593a-48d7-b85a-57c988460149"));
    }

    @Test
    void transferSameAccountIdShouldFail() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            this.accountService.transfer(
                    new TransferRequest(
                            "73e528a3-bd33-4083-b271-c7ff3a4385de",
                            "3c93831c-29f4-4fd5-a99e-442482ffaeed",
                            "3c93831c-29f4-4fd5-a99e-442482ffaeed",
                            100.00));
        });
    }
}