package digitalwallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private Account sourceAccount;
    private Account destinationAccount;
    private BigDecimal amount;
    private Currency currency;
    private LocalDateTime createdAt;

    public Transaction(String id, Account sourceAccount, Account destinationAccount, BigDecimal amount,
                       Currency currency) {
        this.id = id;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return this.id;
    }

    public Account getSourceAccount() {
        return this.sourceAccount;
    }

    public Account getDestinationAccount() {
        return this.destinationAccount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

}
