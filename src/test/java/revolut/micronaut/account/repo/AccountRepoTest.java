package revolut.micronaut.account.repo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

        try {
            this.accountRepo.createAccount("a8160273-d592-4273-ba59-593be90b3ffe", -100.00);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Amount is not positive", e.getMessage());
        }
    }

    @Test
    void addBalance() {
        assertEquals(200.00, this.accountRepo.addBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 100.00));
        assertEquals(300.00, this.accountRepo.addBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 100.00));
        assertEquals(400.00, this.accountRepo.addBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 100.00));

        try {
            this.accountRepo.addBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", -100.00);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Amount is not positive", e.getMessage());
        }
    }

    @Test
    void subtractBalance() {
        assertEquals(70.00, this.accountRepo.subtractBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 30.00));
        assertEquals(40.00, this.accountRepo.subtractBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 30.00));

        try {
            assertEquals(400.00, this.accountRepo.subtractBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", 100.00));
        } catch (IllegalArgumentException e) {
            assertEquals("Account 3c93831c-29f4-4fd5-a99e-442482ffaeed has insufficient funds", e.getMessage());
        }

        try {
            this.accountRepo.subtractBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed", -100.00);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Amount is not positive", e.getMessage());
        }
    }

    @Test
    void getAccountBalance() {

        assertEquals(BigDecimal.valueOf(100.00), this.accountRepo.getAccountBalance("3c93831c-29f4-4fd5-a99e-442482ffaeed"));

        try {
            this.accountRepo.getAccountBalance("randomstring");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("ID randomstring does not exist", e.getMessage());
        }
    }
}