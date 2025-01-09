package manager;

import model.Category;
import model.Expense;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpensesManager {
    private List<Expense> expenses;


    public ExpensesManager() {
        // initially no expense
        expenses = new ArrayList<>();
    }

    // add an expense to the existing list of expenses
    public void addExpense(Expense expense) {

        if (expense.amount() == 0) {
            JOptionPane.showMessageDialog(null,"Amount must be greater than zero.", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
        } else {
            expenses.add(expense);
        }
    }

    // Edit an expense.
    public void replaceExpense(int index, Expense newExpense) {
        if (newExpense.amount() <= 0) {
            JOptionPane.showMessageDialog(null, "Amount must be greater than zero", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
        } else {
            if (index >= 0 && index < expenses.size()) {
                expenses.set(index, newExpense);
            }
        }

    }
    //Delete an expense
    public void deleteExpense(Expense expense) {
        expenses.remove(expense);
    }
    // Clear all expenses
    public void clearExpenses() {
        expenses.clear();
    }


    // return the entire list of expenses added so far
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }


    // get the expense based on category for CategoryFilterPanel
    public List<Expense> getExpensesByCategory(Category category) {
        return expenses.stream()
                .filter(expense -> expense.category().equals(category))
                .collect(Collectors.toList());
    }


}
