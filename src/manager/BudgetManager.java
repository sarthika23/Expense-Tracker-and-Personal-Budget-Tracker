package manager;

import model.Expense;

import java.time.LocalDateTime;
import java.util.*;

public class BudgetManager {
    private final List<Expense> expenses;
    public BudgetManager(List<Expense> expenseList){
        this.expenses = expenseList;
    }

    public void categorizeExpenses(){
        //put data inside Hashmap
        Map<String, Integer> map = new HashMap<>();
        for(Expense exp :expenses){
            String category = exp.getCategory();
            int amount = exp.getAmount();
            map.put(category, map.getOrDefault(category, 0) + amount);  //get the value of key "category"

        }
        System.out.println("\nCategory-wise Spending:");
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + " = ₹" + entry.getValue());
        }
    }
    private Deque<Expense> enqueueExpenses(){
        Deque<Expense> queue = new ArrayDeque<>();
        for(Expense exp : expenses){
            queue.addFirst(exp);
        }
        return queue;
    }
    public void filterExpenses(LocalDateTime cutoff){
        Deque<Expense> queue = enqueueExpenses();
        Expense recentExpense = queue.removeFirst();
        while(recentExpense.getDate().isAfter(cutoff) || recentExpense.getDate().isEqual(cutoff)){
            System.out.println(recentExpense);
            recentExpense = queue.removeFirst();
        }
    }
}
