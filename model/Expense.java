package model;

public class Expense {
    private int id;
    private String category;
    private double amount;
    private String date;
    private String note;

    // Constructor without ID (for inserting new records)
    public Expense(String category, double amount, String date, String note) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    // Constructor with ID (for retrieving existing records)
    public Expense(int id, String category, double amount, String date, String note) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    // Getters
    public int getId() { return id; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public String getNote() { return note; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setCategory(String category) { this.category = category; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDate(String date) { this.date = date; }
    public void setNote(String note) { this.note = note; }
}
