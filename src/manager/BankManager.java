package manager;

public class BankManager {
    private int balance;

    public BankManager(int initialBalance) {
        this.balance = initialBalance;
    }

    public void addIncome(int amount) {
        balance += amount;
    }

    public void deductExpense(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int newBalance) {
        this.balance = newBalance;
    }
}
