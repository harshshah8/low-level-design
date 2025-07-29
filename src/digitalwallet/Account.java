package digitalwallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account {
    private String id;
    private User user;
    private BigDecimal balance;
    private String accountNumber;
    private Currency currency;
    private List<Transaction> transactions;
    private ReentrantReadWriteLock lock;

    public Account(String id, User user, String accountNumber, Currency currency) {
        this.id = id;
        this.user = user;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = BigDecimal.ZERO;
        this.transactions = new CopyOnWriteArrayList<>();
        this.lock = new ReentrantReadWriteLock();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getBalance() {
        lock.readLock().lock();
        try {
            return balance;
        } finally {
            lock.readLock().unlock();
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public List<Transaction> getListOfTransactions() {
        return new ArrayList<>(transactions);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void deposite(BigDecimal amount) {
        lock.writeLock().lock();
        try {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Deposit amount must be positive");
            }
            this.balance = this.balance.add(amount);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void withdraw(BigDecimal amount) {
        lock.writeLock().lock();
        try {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Withdraw amount must be positive");
            }
            if (this.balance.compareTo(amount) <= 0) {
                throw new IllegalArgumentException("Insufficient funds. Available funds " + this.balance + " Requested funds " + amount);
            }
            this.balance = this.balance.subtract(amount);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
