import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import manager.BudgetManager;
import manager.ExpenseManager;
import model.Expense;
import model.Expenses;

public class Main {
    private static void expenseAdd(ExpenseManager expenseManager){
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many expenses do you want to add? ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for expense #" + (i + 1));

            System.out.print("Amount: ");
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
        if(expenseManager.isEmpty()){
            System.out.println("No Date Found!");
            return;
        }
        System.out.println("\nAll Expenses: ");
        int i = 1;
        for (Expense exp : expenseManager.getExpenses()) {
            System.out.println( i++ + " " +exp);
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

    private static void categorizeExpenses(ExpenseManager expenseManager){


    }

    public static void main(String[] args) {
        Expenses expenseList = new Expenses();
        ExpenseManager expenseManager = new ExpenseManager(expenseList.getList());
        BudgetManager budgetManager = new BudgetManager(expenseList.getList());
        Scanner sc = new Scanner(System.in);

        //all the operations menu
        System.out.println("Select the operation number you want to perform: \n");
        System.out.println(" 0. End Program");   //done
        System.out.println(" 1. Show bank balance");
        System.out.println(" 2. Add an expense");    //done
        System.out.println(" 3. Show all expenses"); //done
        System.out.println(" 4. Delete an expense"); //done
        System.out.println(" 5. Clear all expenses"); //done
        System.out.println(" 6. Edit an expense");   //done
        System.out.println(" 7. Show categorized expenses");
        System.out.println(" 8. Show recent expenses");
        System.out.println(" 9. Show top spending categories");
        System.out.println("10. Show total monthly spending");
        System.out.println("11. Show pending payments");



        while(true){
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            //making a switch case for all the operations
            switch(choice){
                case 1:
                {
                    //Show bank balance

                    break;
                }
                case 2:
                {
                    //Add expense
                    //This will decrease the bank balance
                    //for modularity and readability create a function
                    expenseAdd(expenseManager); //telling the expense manager to add an expense
                    break;
                }
                case 3:
                {
                    //Display all expenses
                    displayExpenses(expenseManager);
                    break;
                }
                case 4:
                {
                    //Deleting an expense
                    deleteExpense(expenseManager);
                    break;
                }
                case 5:
                {
                    //Clearing all expenses
                    expenseManager.clearAllExpenses();
                    break;
                }
                case 6:
                {
                    //Updating an expense
                    updateExpense(expenseManager);
                    break;
                }

                //BUDGET MANAGEMENT
                case 7:
                {
                    //show categorized spending
                    categorizeExpenses(expenseManager);
                    break;
                }
                case 8:
                {
                    break;
                }
                case 9:
                {
                    break;
                }
                case 10:
                {
                    break;
                }
                case 11:
                {
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
