package digitalwallet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private static final Map<Currency, BigDecimal> exchangeRate = new HashMap<>();

    static {
        exchangeRate.put(Currency.INR, BigDecimal.ONE);
        exchangeRate.put(Currency.USD, new BigDecimal("0.01156"));
        exchangeRate.put(Currency.EUR, new BigDecimal("0.00984"));
    }

    public static BigDecimal convert(BigDecimal amount, Currency sourceCurrency, Currency targetCurrency) {
        if (sourceCurrency.equals(targetCurrency)) {
            return amount;
        }

        BigDecimal sourceRate = exchangeRate.get(sourceCurrency);
        BigDecimal targetRate = exchangeRate.get(targetCurrency);

        return amount.divide(sourceRate, 10, RoundingMode.HALF_UP).multiply(targetRate);
    }
}
