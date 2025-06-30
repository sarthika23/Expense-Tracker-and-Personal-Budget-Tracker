package manager;

import model.Expense;
import model.UserData;

import java.time.LocalDateTime;
import java.util.*;

public class BudgetManager {
    private final List<Expense> expenses;
    private int bankBalance;
    public BudgetManager(UserData data){
        this.expenses = data.getExpenses();
        this.bankBalance = data.getBankBalance();
    }

    private Map<String, Integer> buildMap(List<Expense> expenses){
        Map<String, Integer> map = new HashMap<>();
        for(Expense exp :expenses){
            String category = exp.getCategory();
            int amount = exp.getAmount();
            map.put(category, map.getOrDefault(category, 0) + amount);  //get the value of key "category"

        }
        return map;
    }

    public void categorizeExpenses(){
        //put data inside Hashmap
        Map<String, Integer> map = buildMap(expenses);

        System.out.println("\nCategory-wise Spending:");
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + " = ₹" + entry.getValue());
        }
    }

    public void topSpendingCategories(){
        Map<String, Integer> map = buildMap(expenses);
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());  // max-heap by amount
        pq.addAll(map.entrySet());
        System.out.println("\nTop 3 Spending Categories:");
        int rank = 1;
        while (!pq.isEmpty() && rank <= 3) {
            Map.Entry<String, Integer> entry = pq.poll();
            System.out.println(rank++ + ". " + entry.getKey() + " — ₹" + entry.getValue());
        }
    }

    private Deque<Expense> enqueueExpenses(){
        Deque<Expense> queue = new ArrayDeque<>();
        for(Expense exp : expenses){
            queue.addLast(exp);
        }
        return queue;
    }

    public void filterExpenses(LocalDateTime cutoff){
        Deque<Expense> queue = enqueueExpenses();

        while(!queue.isEmpty()){
            Expense recentExpense = queue.removeFirst();
            if(recentExpense.getDate().isAfter(cutoff) || recentExpense.getDate().isEqual(cutoff)){
                System.out.println(recentExpense);

            }
        }

    }

    private String month(int monthNo){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "January");
        map.put(2, "February");
        map.put(3, "March");
        map.put(4, "April");
        map.put(5, "May");
        map.put(6, "June");
        map.put(7, "July");
        map.put(8, "August");
        map.put(9, "September");
        map.put(10, "October");
        map.put(11, "November");
        map.put(12, "December");

        return map.getOrDefault(monthNo, "Invalid Month");
    }

    public void totalMonthlySpending() {
        Map<String, Integer> monthlySpending = new TreeMap<>(); // sorted by date

        for (Expense exp : expenses) {
            LocalDateTime date = exp.getDate();
            String key = month(date.getMonthValue()) + "\'" + date.getYear() ; // june-2025

            monthlySpending.put(key, monthlySpending.getOrDefault(key, 0) + exp.getAmount());
        }

        System.out.println("\nMonthly Spending Summary:");
        for (Map.Entry<String, Integer> entry : monthlySpending.entrySet()) {
            System.out.println(entry.getKey() + " → ₹" + entry.getValue());
        }
    }

}
