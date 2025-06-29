package manager;
import model.Expense;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private final List<Expense> expenses;

    public ExpenseManager(List<Expense> expenseList) {
        this.expenses = expenseList;
    }

    public void addExpense(Expense expense){
        if(expense != null) {
            expenses.add(expense);
            FileManager.saveExpenses(expenses, "CASHFLOW.json");
            System.out.println("Expense added: " + expense);
        } else {
            System.out.println("Cannot add null expense.");
        }
    }

    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    public void removeExpense(int index) {
        Expense ex = expenses.get(index - 1);
        if(expenses.remove(ex)) {
            FileManager.saveExpenses(expenses, "CASHFLOW.json");
//            System.out.println("Expense removed: " + ex);
        } else {
            System.out.println("Expense not found: " + ex);
        }
    }

    public void clearAllExpenses() {
        if(expenses.isEmpty()){
            System.out.println("No Data Found!");
            return;
        }
        expenses.clear();
        FileManager.saveExpenses(expenses, "CASHFLOW.json");
        System.out.println("All expenses cleared.");
    }

    public void editExpense(int index, Expense updatedExpense) {
        if (index - 1 >= 0 && index - 1 < expenses.size()) {
            expenses.set(index - 1, updatedExpense);
            System.out.println("Expense updated successfully.");
            FileManager.saveExpenses(expenses, "CASHFLOW.json");
        } else {
            System.out.println("Invalid index! No changes made.");
        }
    }
    public boolean isEmpty(){
        return expenses.isEmpty();
    }

}
