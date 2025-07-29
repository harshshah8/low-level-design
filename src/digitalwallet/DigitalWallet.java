package digitalwallet;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class DigitalWallet {
    private static DigitalWallet instance;
    private static final ReentrantLock instanceLock = new ReentrantLock();
    private static Map<String, User> users;
    private static Map<String, Account> accounts;
    private static Map<String, AbstractPaymentMethodStrategy> paymentMethods;

    private DigitalWallet () {
        users = new HashMap<>();
        accounts = new HashMap<>();
        paymentMethods = new HashMap<>();
    }

    public static DigitalWallet getInstance() {
        if(Objects.isNull(instance)) {
            instanceLock.lock();
        }
        try {
            if(Objects.isNull(instance)) {
                instance = new DigitalWallet();
            }
        } finally {
            instanceLock.unlock();
        }
        return instance;
    }

    public void createUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public void createAccount(Account account) {
        accounts.put(account.getId(), account);
        account.getUser().addAccount(account);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void addPaymentMethod(AbstractPaymentMethodStrategy paymentMethod) {
        paymentMethods.put(paymentMethod.getId(), paymentMethod);
    }

    public AbstractPaymentMethodStrategy getPaymentMethod(String id) {
        return paymentMethods.get(id);
    }

    public List<Transaction> getTransactionHistory(Account account) {
        return account.getListOfTransactions();
    }

    public void transferFunds(BigDecimal amount, Account sourceAccount, Account destinationAccount, Currency currency) {
        if(Objects.isNull(sourceAccount) || Objects.isNull(destinationAccount)) {
            throw new IllegalArgumentException("Invalid account");
        }

        BigDecimal sourceCurrencyAmount = amount;

        // Convert transfer amount to source account's currency
        if(!sourceAccount.getCurrency().equals(currency)) {
            sourceCurrencyAmount = CurrencyConverter.convert(amount, currency, sourceAccount.getCurrency());
        }

        BigDecimal destinationCurrencyAmount = sourceCurrencyAmount;

        // Convert from source account's currency to destination account's currency
        if(!destinationAccount.getCurrency().equals(sourceAccount.getCurrency())) {
            destinationCurrencyAmount = CurrencyConverter.convert(sourceCurrencyAmount,
                    sourceAccount.getCurrency(), destinationAccount.getCurrency()); // Fixed: correct source currency + semicolon
        }

        // Prevent deadlock by ordering locks consistently
        Account firstLock = sourceAccount.getId().compareTo(destinationAccount.getId()) < 0 ? sourceAccount : destinationAccount;
        Account secondLock = sourceAccount.getId().compareTo(destinationAccount.getId()) < 0 ? destinationAccount : sourceAccount;

        synchronized(firstLock) {
            synchronized (secondLock) {
                sourceAccount.withdraw(sourceCurrencyAmount);
                destinationAccount.deposite(destinationCurrencyAmount);
            }
        }

        String txnId = generateTransactionId();
        Transaction transaction = new Transaction(txnId, sourceAccount, destinationAccount, amount, currency);
        sourceAccount.addTransaction(transaction);
        destinationAccount.addTransaction(transaction);
    }

    private String generateTransactionId() {
        return "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
