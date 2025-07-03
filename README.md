# 💸 Expense & Personal Budget Tracker (Java CLI)

A **CLI-based Expense and Budget Tracker** built in Java to help hostel-living students track their daily expenses, analyze spending, and manage their bank balance — all using core **DSA concepts** and **JSON-based persistence**.

---

## 📌 Features

### ✅ Expense Management

* Add, edit, delete, and clear expenses
* View all expenses with details
* Expenses stored using `ArrayList`

### ✅ Budget Management

* Track and update **bank balance** in real-time
* Categorize expenses (using `HashMap`)
* Show total monthly spending (`TreeMap`)
* View top spending categories (`PriorityQueue`)
* Filter recent expenses (by date, with `Deque`)
* All manipulations affect the balance automatically

### ✅ File Handling (Persistence)

* Data saved in structured **JSON format**
* Bank balance and expenses are restored on next use
* Uses `Gson` for serialization with `LocalDateTime` support

### ✅ DSA Concepts Used

| Data Structure  | Use Case                                     |
| --------------- | -------------------------------------------- |
| `ArrayList`     | Storing and displaying expenses              |
| `HashMap`       | Categorizing and summing category-wise spend |
| `TreeMap`       | Monthly spending (sorted by month)           |
| `PriorityQueue` | Top N spending categories                    |
| `Deque`         | Recent N expenses (using `ArrayDeque`)       |
| `Set`           | Unique categories/payment methods            |

---

## 📂 JSON Structure Example

```json
{
  "bankBalance": 2450,
  "expenses": [
    {
      "amount": 120,
      "description": "Groceries",
      "category": "Food",
      "paymentMethod": "UPI",
      "dateTime": "2025-06-30 18:20"
    }
  ]
}
```

---

## 🎯 Future Improvements

These features are **planned for future versions**:

* 🛒 Wishlist tracking (non-expensed items)
* 💸 Reimbursement tracking (pending returns from friends)
* 📈 Daily spending summary generator
* 🖥️ GUI using JavaFX or React
* 📆 Recurring expenses like subscriptions
* ☁️ Cloud sync for multi-device usage
* 🧠 Smart budget suggestions and auto alerts

---

## 🔧 How to Run

1. Clone the repo or copy code locally
2. Add `gson-2.x.x.jar` to your classpath
3. Run `Main.java`
4. Use the numbered CLI menu to perform actions
