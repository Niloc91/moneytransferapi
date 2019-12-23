package revolut.micronaut.account.repo;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Singleton
public class AccountRepo {
    private final Map<String, BigDecimal> accountData;

    public AccountRepo() {
        this.accountData = new HashMap<>();
    }

    public AccountRepo(Map<String, BigDecimal> accountData) {
        this.accountData = accountData;
    }

    public double createAccount(String accountId, double amount) throws IllegalArgumentException {
        if (!isPositiveAmount(amount)) {
            throw new IllegalArgumentException("Amount is not positive");
        }

        if (!validUUID(accountId)) {
            throw new IllegalArgumentException("String is not valid UUID");
        }

        this.accountData.put(accountId, BigDecimal.valueOf(amount));
        return this.getAccountBalance(accountId).doubleValue();
    }

    public double addBalance(String accountId, double amount) {
        if (!isPositiveAmount(amount)) {
            throw new IllegalArgumentException("Amount is not positive");
        }

        final BigDecimal initialBalance = this.getAccountBalance(accountId);
        this.accountData.put(accountId, initialBalance.add(BigDecimal.valueOf(amount)));
        return getAccountBalance(accountId).doubleValue();
    }

    public double subtractBalance(String accountId, double amount) throws IllegalArgumentException {
        if (!isPositiveAmount(amount)) {
            throw new IllegalArgumentException("Amount is not positive");
        }

        final BigDecimal initialBalance = this.getAccountBalance(accountId);
        final BigDecimal balance = initialBalance.subtract(BigDecimal.valueOf(amount));
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(String.format("Account %s has insufficient funds", accountId));
        }

        this.accountData.put(accountId, balance);
        return this.getAccountBalance(accountId).doubleValue();
    }

    public BigDecimal getAccountBalance(String accountId) {
        if (!validAccountId(accountId)) {
            throw new IllegalArgumentException(String.format("ID %s does not exist", accountId));
        }

        return this.accountData.get(accountId);
    }

    public boolean validAccountId(String accountId) {
        if (validUUID(accountId)) {
            return this.accountData.containsKey(accountId);
        } else {
            return false;
        }
    }

    public boolean validUUID(String accountId) {
        try {
            UUID.fromString(accountId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isPositiveAmount(double amount) {
        return amount >= 0;
    }
}
