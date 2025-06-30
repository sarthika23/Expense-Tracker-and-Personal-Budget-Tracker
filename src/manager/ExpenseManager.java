package manager;
import model.Expense;
import model.UserData;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private final List<Expense> expenses;
    private int bankBalance;

    public ExpenseManager(UserData data) {
        this.expenses = data.getExpenses();
        this.bankBalance = data.getBankBalance();
    }

    public void addExpense(Expense expense){
        if(expense != null) {
            expenses.addFirst(expense);
            bankBalance -= expense.getAmount();
            if(bankBalance < 0){
                System.out.println("Please update your bank balance!");
                return;
            }

            UserData data = new UserData();
            data.setExpenses(expenses);
            data.setBankBalance(bankBalance);

            FileManager.saveUserData(data, "CASHFLOW.json");
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
            bankBalance += ex.getAmount();

            UserData data = new UserData();
            data.setExpenses(expenses);
            data.setBankBalance(bankBalance);

            FileManager.saveUserData(data, "CASHFLOW.json");
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
        UserData data = new UserData();
        data.setExpenses(expenses);
        data.setBankBalance(bankBalance);

        FileManager.saveUserData(data, "CASHFLOW.json");
        System.out.println("All expenses cleared.");
    }

    public void editExpense(int index, Expense updatedExpense) {
        if (index - 1 >= 0 && index - 1 < expenses.size()) {
            removeExpense(index);
            expenses.addFirst(updatedExpense);
            System.out.println("Expense updated successfully.");
        } else {
            System.out.println("Invalid index! No changes made.");
        }
    }

    public boolean isEmpty(){
        return expenses.isEmpty();
    }

}
