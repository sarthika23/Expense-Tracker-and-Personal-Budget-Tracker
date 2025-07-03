import java.time.LocalDateTime;
import java.util.*;

import manager.BudgetManager;
import manager.ExpenseManager;
import manager.FileManager;
import model.Expense;
import model.UserData;

public class Main {
    private static void expenseAdd(ExpenseManager expenseManager){
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many expenses do you want to add? -> ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for expense #" + (i + 1));

            System.out.print("Amount: ₹");
            int amount = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Description: ");
            String desc = scanner.nextLine();

            System.out.print("Category: ");
            String category = scanner.nextLine();

            System.out.print("Payment Method: ");
            String payment = scanner.nextLine();

            Expense expense = new Expense(amount, desc, category, payment);
            expenseManager.addExpense(expense);
        }
    }

    private static void displayExpenses(ExpenseManager expenseManager){
        List<Expense> expenses = expenseManager.getExpenses();

        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.printf("\n%-4s %-10s %-15s %-12s %-20s %-20s\n", "No.", "Amount", "Category", "Method", "Description", "Date");
        System.out.println("---------------------------------------------------------------------------------------------");

        int i = 1;
        for (Expense exp : expenses) {
            System.out.printf("%-4d ₹%-9d %-15s %-12s %-20s %-20s\n",
                    i++,
                    exp.getAmount(),
                    exp.getCategory(),
                    exp.getPaymentMethod(),
                    exp.getDescription(),
                    exp.getDate().toString());
        }
    }


    private static void deleteExpense(ExpenseManager expenseManager){
        Scanner sc = new Scanner(System.in);
        displayExpenses(expenseManager);
        System.out.print("Select and enter the expense index you wish to delete: ");
        int index = sc.nextInt();
        expenseManager.removeExpense(index);
    }

    private static void updateExpense(ExpenseManager expenseManager){
        Scanner sc = new Scanner(System.in);
        displayExpenses(expenseManager);
        System.out.print("Select and enter the expense index you wish to update: ");
        int index = sc.nextInt();
        System.out.println("Enter the updated data");
        System.out.print("Amount: ");
        int amount = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Description: ");
        String desc = sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Payment Method: ");
        String payment = sc.nextLine();

        Expense updatedExpense = new Expense(amount, desc, category, payment);
        expenseManager.editExpense(index, updatedExpense);

    }

    private static void filterExpensesCutoff(BudgetManager budgetManager){
        System.out.println("View Expenses from:");
        System.out.println("1. Last N days");
        System.out.println("2. Last N months");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter you choice to filter out data: ");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
            {
                System.out.println("Enter the number the days: ");
                LocalDateTime now = LocalDateTime.now();
                int days = sc.nextInt();
                LocalDateTime cutoff = now.minusDays(days);
                budgetManager.filterExpenses(cutoff);
                break;
            }
            case 2:
            {
                System.out.println("Enter the number the months: ");
                LocalDateTime now = LocalDateTime.now();
                int months = sc.nextInt();
                LocalDateTime cutoff = now.minusMonths(months);
                budgetManager.filterExpenses(cutoff);
                break;
            }
            default :{
                System.out.println("Invalid Choice!");
            }
        }
    }

    private static void addIncome(UserData data){
        System.out.print("Enter the income: ₹");
        Scanner sc = new Scanner(System.in);
        int income = sc.nextInt();
        data.setBankBalance(data.getBankBalance() + income);
        FileManager.saveUserData(data, "CASHFLOW.json");
        System.out.println("New Bank Balance is ₹" + data.getBankBalance());
    }

    public static void main(String[] args) {

        UserData loadedUserData = FileManager.loadUserData("CASHFLOW.json");
        ExpenseManager expenseManager = new ExpenseManager(loadedUserData);
        BudgetManager budgetManager = new BudgetManager(loadedUserData);
        Scanner sc = new Scanner(System.in);

        //all the operations menu
        System.out.println("Select the operation number you want to perform: \n");
        System.out.println(" 0. End Program");   //done
        System.out.println(" 1. Show bank balance"); //done
        System.out.println(" 2. Add Income");   //done
        System.out.println(" 3. Add an expense");    //done
        System.out.println(" 4. Show all expenses"); //done
        System.out.println(" 5. Delete an expense"); //done
        System.out.println(" 6. Clear all expenses"); //done
        System.out.println(" 7. Edit an expense");   //done
        System.out.println(" 8. Show recent expenses"); //done
        System.out.println(" 9. Show categorized expenses"); //done
        System.out.println("10. Show top spending categories"); //done
        System.out.println("11. Show total monthly spending"); //done
        System.out.println("12. Show pending payments");



        while(true){
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            //making a switch case for all the operations
            switch(choice){
                case 1:
                {
                    //Show bank balance
                    System.out.println("Your Bank Balance is " + loadedUserData.getBankBalance());
                    break;
                }
                case 2:
                {
                    //add income
                    addIncome(loadedUserData);
                    break;
                }
                case 3:
                {
                    //Add expense
                    //This will decrease the bank balance
                    //for modularity and readability create a function
                    expenseAdd(expenseManager); //telling the expense manager to add an expense
                    break;
                }
                case 4:
                {
                    //Display all expenses
                    displayExpenses(expenseManager);
                    break;
                }
                case 5:
                {
                    //Deleting an expense
                    deleteExpense(expenseManager);
                    break;
                }
                case 6:
                {
                    //Clearing all expenses
                    expenseManager.clearAllExpenses();
                    break;
                }
                case 7:
                {
                    //Updating an expense
                    updateExpense(expenseManager);
                    break;
                }

                //BUDGET MANAGEMENT
                case 8:
                {
                    //show recent n expenses
                    filterExpensesCutoff(budgetManager);
                    break;
                }
                case 9:
                {
                    //show categorized spending
                    budgetManager.categorizeExpenses();
                    break;
                }
                case 10:
                {
                    //Show top 3 spending categories
                    budgetManager.topSpendingCategories();
                    break;
                }
                case 11:
                {
                    //Show total monthly spending
                    budgetManager.totalMonthlySpending();
                    break;
                }
                case 0:
                {
                    sc.close();
                    System.exit(0);

                }
                //end of cases
            }
        }

        //end of program
    }
}
