package digitalwallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private Map<String, Account> accounts;

    public User(String id, String name, String email, String password) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
        this.accounts = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
    }

    public void removeAccount(String accountId) {
        accounts.remove(accountId);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }
}
