package ui;

import dao.ExpenseDAO;
import model.Expense;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ExpenseTrackerUI extends JFrame {
    private JTextField categoryField, amountField, dateField, noteField;
    private JTable expenseTable;
    private DefaultTableModel tableModel;
    private ExpenseDAO dao;

    public ExpenseTrackerUI() {
        dao = new ExpenseDAO();
        dao.createTable();  // Ensure table exists

        setTitle("Expense Tracker");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Note:"));
        noteField = new JTextField();
        inputPanel.add(noteField);

        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(e -> addExpense());
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Category", "Amount", "Date", "Note"}, 0);
        expenseTable = new JTable(tableModel);
        refreshTable();

        add(new JScrollPane(expenseTable), BorderLayout.CENTER);
    }

    private void addExpense() {
        String category = categoryField.getText();
        String amountText = amountField.getText();
        String date = dateField.getText();
        String note = noteField.getText();

        if (category.isEmpty() || amountText.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            Expense expense = new Expense(category, amount, date, note);
            dao.insertExpense(expense);
            refreshTable();
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Amount must be a valid number.");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);  // Clear existing rows
        List<Expense> expenses = dao.getAllExpenses();
        for (Expense exp : expenses) {
            tableModel.addRow(new Object[]{
                exp.getId(), exp.getCategory(), exp.getAmount(), exp.getDate(), exp.getNote()
            });
        }
    }

    private void clearFields() {
        categoryField.setText("");
        amountField.setText("");
        dateField.setText("");
        noteField.setText("");
    }
}
