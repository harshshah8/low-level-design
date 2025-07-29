package digitalwallet;

import java.math.BigDecimal;

public class CreditCard extends AbstractPaymentMethodStrategy{
    private String cvv;
    private String cardNumber;
    private String expirationDate;

    public CreditCard(String id, User user, String cvv, String cardNumber, String expirationDate) {
        super(id, user);
        this.cvv = cvv;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean processPayment(BigDecimal amount, Currency currency) {
        return amount.compareTo(BigDecimal.valueOf(10000)) <= 0;
    }

    public String getCvv() {
        return cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return  expirationDate;
    }
}
