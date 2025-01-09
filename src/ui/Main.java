package ui;

import dialog.SavedExpensesEditDialog;
import manager.ExpensesManager;
import model.Category;
import model.Currency;
import model.Expense;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        JFrame frame;
        frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the object of expenseManager
        ExpensesManager expensesManager = new ExpensesManager();

        // Create instances of relevant panels
        NewExpensesPanel newExpensesPanel = new NewExpensesPanel();
        SavedExpensesPanel savedExpensesPanel = new SavedExpensesPanel();
        CategoryFilterPanel categoryFilterPanel = new CategoryFilterPanel(expensesManager, savedExpensesPanel);

        // Add panels to the frame and specify their relative position in the frame
        frame.add(newExpensesPanel, BorderLayout.NORTH);
        frame.add(savedExpensesPanel, BorderLayout.CENTER);
        frame.add(categoryFilterPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);




        // Listener for clicking savebutton
        newExpensesPanel.getSaveButton().addActionListener(e ->  {
            double amount = newExpensesPanel.getAmount();
            String currency = newExpensesPanel.getExpenseCurrency();
            String category = newExpensesPanel.getExpenseCategory();
            LocalDate date = newExpensesPanel.getDate();

            Expense newExpense = new Expense(amount, Currency.get(currency), Category.valueOf(category.toUpperCase()), date);
            expensesManager.addExpense(newExpense);
            savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
        });

        SavedExpensesEditDialog editDialog = new SavedExpensesEditDialog(frame);

        // Edit Button ActionListener
        newExpensesPanel.getEditButton().addActionListener(e -> {
            int selectedRow = savedExpensesPanel.getSavedSelectedExpenseIndex();
            if (selectedRow != -1) {
                // get the index
                Expense selectedExpense = expensesManager.getAllExpenses().get(selectedRow);
                if (selectedExpense.amount() <= 0) {
                    JOptionPane.showMessageDialog(frame, "Amount must be greater than zero", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                } else {

                    boolean saved = editDialog.showDialog(selectedExpense);
                    if (saved) {
                        Expense editedExpense = editDialog.getEditedExpense();
                        expensesManager.replaceExpense(selectedRow, editedExpense);
                        savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
                    }
                }
            }
        });




        // Delete Button ActionListener
        newExpensesPanel.getDeleteButton().addActionListener(e -> {
            int selectedRow = savedExpensesPanel.getSavedSelectedExpenseIndex();
            if (selectedRow != -1) {
                // get the selected expense and remove it
                List<Expense> expenses = expensesManager.getAllExpenses();
                Expense expenseToDelete = expenses.get(selectedRow);
                expensesManager.deleteExpense(expenseToDelete);
                //update the table
                savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
            }
        });


        // Clear Button ActionListener
        newExpensesPanel.getClearButton().addActionListener(e -> {
            expensesManager.clearExpenses();
            savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
        });


        // Filter Button ActionListener

        categoryFilterPanel.getFilterButton().addActionListener(e -> categoryFilterPanel.applyFilter());

        // Restore Button ActionListener

        categoryFilterPanel.getRestoreButton().addActionListener(e -> categoryFilterPanel.restoreFilter());

        //Sum Button ActionListener
        categoryFilterPanel.getSumButton().addActionListener(e -> categoryFilterPanel.sumFilter());
    }
}
