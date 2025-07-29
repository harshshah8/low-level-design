package digitalwallet;

import java.math.BigDecimal;
import java.util.List;

public class DigitalWalletDemo {
    public static void main(String[] args) {
        DigitalWallet digitalWallet = DigitalWallet.getInstance();

        User harsh = new User("user1", "Harsh Shah", "harsh@email.com", "password123");
        User anu = new User("user2", "Anubhuti kala", "anu@email.com", "password456");

        digitalWallet.createUser(harsh);
        digitalWallet.createUser(anu);

        Account harshInr = new Account("account1", harsh, "123456789", Currency.INR);
        Account harshUsd = new Account("account2", harsh, "234567891", Currency.USD);
        Account anuInr = new Account("account3", anu, "345678912", Currency.INR);

        digitalWallet.createAccount(harshInr);
        digitalWallet.createAccount(harshUsd);
        digitalWallet.createAccount(anuInr);

        CreditCard creditCard = new CreditCard("cc1", harsh, "234", "111111111", "12/25");
        BankAccount bankAccount = new BankAccount("ba1", anu, "234234234");

        digitalWallet.addPaymentMethod(creditCard);
        digitalWallet.addPaymentMethod(bankAccount);

        harshInr.deposite(BigDecimal.valueOf(10000));
        harshUsd.deposite(BigDecimal.valueOf(1000));
        anuInr.deposite(BigDecimal.valueOf(20000));

        digitalWallet.transferFunds(BigDecimal.valueOf(5000), harshInr, anuInr, Currency.INR);
        digitalWallet.transferFunds(BigDecimal.valueOf(5000), harshUsd, anuInr, Currency.INR);

        List<Transaction> transactionsHistoryA1 = digitalWallet.getTransactionHistory(harshInr);
        List<Transaction> transactionsHistoryA2 = digitalWallet.getTransactionHistory(harshUsd);
        List<Transaction> transactionsHistoryA3 = digitalWallet.getTransactionHistory(anuInr);

        System.out.println("Transaction History for Account 1:");
        for (Transaction transaction : transactionsHistoryA1) {
            System.out.println("Transaction ID: " + transaction.getId());
            System.out.println("Amount: " + transaction.getAmount() + " " + transaction.getCurrency());
            System.out.println("Timestamp: " + transaction.getCreatedAt());
            System.out.println();
        }

        System.out.println("Transaction History for Account 2:");
        for (Transaction transaction : transactionsHistoryA2) {
            System.out.println("Transaction ID: " + transaction.getId());
            System.out.println("Amount: " + transaction.getAmount() + " " + transaction.getCurrency());
            System.out.println("Timestamp: " + transaction.getCreatedAt());
            System.out.println();
        }

        System.out.println("Transaction History for Account 3:");
        for (Transaction transaction : transactionsHistoryA3) {
            System.out.println("Transaction ID: " + transaction.getId());
            System.out.println("Amount: " + transaction.getAmount() + " " + transaction.getCurrency());
            System.out.println("Timestamp: " + transaction.getCreatedAt());
            System.out.println();
        }

    }
}
