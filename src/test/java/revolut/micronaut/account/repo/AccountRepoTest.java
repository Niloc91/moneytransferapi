package revolut.micronaut.account.repo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepoTest {

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
    }

    @AfterEach
    void tearDown() {
        this.accountRepo = new AccountRepo();
    }

    @Test
    void createAccount() {
        assertEquals(100.00, this.accountRepo.createAccount("a8160273-d592-4273-ba59-593be90b3ffe", 100.00));
    }

    @Test
    void createAccountShouldFailNonPositiveInitial() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.accountRepo.createAccount("a8160273-d592-4273-ba59-593be90b3ffe", -100.00);
        });
    }

    @Test
    void addBalance() {
        assertEquals(200.00, this.accountRepo.addBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 100.00));
        assertEquals(300.00, this.accountRepo.addBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 100.00));
        assertEquals(400.00, this.accountRepo.addBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 100.00));
    }

    @Test
    void addBalanceShouldFailNonPositiveAmount() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
                    this.accountRepo.addBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", -100.00);
                }
        );
    }


    @Test
    void subtractBalance() {
        assertEquals(70.00, this.accountRepo.subtractBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 30.00));
        assertEquals(40.00, this.accountRepo.subtractBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 30.00));
    }

    @Test
    void subtractBalanceShouldFailInsufficientFunds() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            assertEquals(400.00, this.accountRepo.subtractBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 400.00));
            }
        );
    }

    @Test
    void subtractBalanceShouldFailNonPositive() {
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            this.accountRepo.subtractBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", -100.00);
        });
    }

    @Test
    void getAccountBalance() {
        assertEquals(BigDecimal.valueOf(100.00), this.accountRepo.getAccountBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed"));
    }

    @Test
    void getAccountBalanceShouldFailNonExistentId() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            this.accountRepo.getAccountBalance("randomstring");
            }
        );
    }

    @Test
    void validAccountId(){
        assertTrue(this.accountRepo.validAccountId("3c93831c-29f4-4fd5-a99e-442482ffaeed"));
    }

    @Test
    void validAccountIdShouldFailNonExistentAccountId() {
        assertFalse(this.accountRepo.validAccountId("9c93831c-29f4-4fd5-a99e-442482ffaeed"));
    }

    @Test
    void validUUID() {
        assertTrue(this.accountRepo.validUUID("3c93831c-29f4-4fd5-a99e-442482ffaeed"));
        assertFalse(this.accountRepo.validUUID("3c93831c"));
    }
}