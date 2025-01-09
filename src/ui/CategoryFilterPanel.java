package ui;

import manager.ExpensesManager;
import model.Category;
import model.Expense;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CategoryFilterPanel extends JPanel {
    private ExpensesManager expensesManager;
    private SavedExpensesPanel savedExpensesPanel;
    private JButton filterButton;
    private JComboBox<Category> filterComboBox;


    private JButton restoreButton;
    private JButton sumButton;
    private JLabel totalLable;


    public CategoryFilterPanel(ExpensesManager expensesManager, SavedExpensesPanel savedExpensesPanel) {
        this.expensesManager = expensesManager;
        this.savedExpensesPanel = savedExpensesPanel;

        JComboBox<Category> filterComboBox;
        JButton filterButton;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        filterComboBox = new JComboBox<>(Category.values());
        add(filterComboBox);

        filterButton = new JButton("Filter");
        add(filterButton);

        restoreButton = new JButton("Restore");
        add(restoreButton);

        sumButton = new JButton("Sum");
        add(sumButton);

        totalLable = new JLabel("Total: ");
        add(totalLable);

        sumButton.addActionListener(e -> {
            double total = sumFilter();
            totalLable.setText("Total: " + String.format("%.2f", total));
        });
    }

    // Methods to get the selected category

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getRestoreButton() {
        return restoreButton;
    }

    public JButton getSumButton() {
        return sumButton;
    }



    public void applyFilter() {
        Category selectedCategory = (Category) filterComboBox.getSelectedItem();
        // Retrieve filtered expenses and update the table
        savedExpensesPanel.updateTable(expensesManager.getExpensesByCategory(selectedCategory));
    }

    public void restoreFilter(){

        List<Expense> previouslySavedExpenses = savedExpensesPanel.getpreviouslySavedExpenses();
        if (previouslySavedExpenses != null) {
            savedExpensesPanel.updateTable(previouslySavedExpenses);
        }
    }

    public double sumFilter(){
        List<Expense> expenses = expensesManager.getAllExpenses();
        double total = 0;

        for (Expense expense : expenses) {
            if (expense.currency() != expenses.get(0).currency()) {

                JOptionPane.showMessageDialog(null, "Cannot sum expenses with different currencies.", "Error", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            total += expense.amount();
        }
        return total;
    }


}
