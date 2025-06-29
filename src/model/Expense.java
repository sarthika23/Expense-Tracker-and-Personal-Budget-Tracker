package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;

public class Expense {
    private int amount;
    private LocalDateTime dateTime;
    private String description;
    private String category;
    private String paymentMethod;

    public Expense(int amount, String description, String category, String paymentMethod) {
        this.amount = amount;
        this.dateTime = LocalDateTime.now() ;
        this.description = description;
        this.category = category;
        this.paymentMethod = paymentMethod;
    }
    public Expense(int amount, String description, String category, String paymentMethod, LocalDateTime dateTime) {
        this.amount = amount;
        this.dateTime = dateTime;
        this.description = description;
        this.category = category;
        this.paymentMethod = paymentMethod;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public LocalDateTime getDate() {
        return dateTime;
    }
    public void setDate(LocalDateTime date) {
        this.dateTime = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "Expense{" +
                "amount=₹" + amount +
                ", date=" + dateTime.format(formatter) +
                ", description='" + description + '\'' +
                ", category=" + category +
                "Payment Method=" + paymentMethod +
                '}';
    }
}
