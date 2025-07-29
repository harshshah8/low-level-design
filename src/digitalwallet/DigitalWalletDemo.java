package digitalwallet;

import java.math.BigDecimal;

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

        digitalWallet.transferFunds(BigDecimal.valueOf(5000), harshUsd, anuInr, Currency.INR);

        BigDecimal balance = harshUsd.getBalance().setScale(2);
        System.out.println(balance);
        BigDecimal balance1 = anuInr.getBalance().setScale(2);
        System.out.println(balance1);

    }
}
