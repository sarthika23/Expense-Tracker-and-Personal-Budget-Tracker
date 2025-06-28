package manager;

import model.Expense;

import java.util.List;

public class BudgetManager {
    private final List<Expense> expenses;
    public BudgetManager(List<Expense> expenseList){
        this.expenses = expenseList;
    }


}
