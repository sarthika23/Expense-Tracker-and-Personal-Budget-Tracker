package model;

import java.util.ArrayList;
import java.util.List;

public class Expenses {
    private final List<Expense> expenseList;

    public Expenses() {
        this.expenseList = new ArrayList<>();
    }

    public List<Expense> getList() {
        return expenseList;
    }
}
