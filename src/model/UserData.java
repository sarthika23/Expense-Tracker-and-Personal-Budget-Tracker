package model;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private int bankBalance;
    private List<Expense> expenses;

    public UserData() {
        this.bankBalance = 0;
        this.expenses = new ArrayList<>();
    }

    // Getters and setters
    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
