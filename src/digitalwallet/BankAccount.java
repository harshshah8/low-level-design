package digitalwallet;

import java.math.BigDecimal;

public class BankAccount extends AbstractPaymentMethodStrategy {
    private String accountNumber;

    public BankAccount(String id, User user, String accountNumber) {
        super(id, user);
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean processPayment(BigDecimal amount, Currency currency) {
        return amount.compareTo(BigDecimal.valueOf(50000)) <= 0;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
}
