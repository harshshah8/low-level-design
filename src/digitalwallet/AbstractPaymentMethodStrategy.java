package digitalwallet;

import java.math.BigDecimal;

public abstract class AbstractPaymentMethodStrategy {
    private String id;
    private User user;

    public AbstractPaymentMethodStrategy(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public abstract boolean processPayment(BigDecimal amount, Currency currency);

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
