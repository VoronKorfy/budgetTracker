package model;

public class Transaction {
    public int studentId;
    public float amount;
    public String description;

    public Transaction(int studentId, float amount, String description) {
        this.studentId = studentId;
        this.amount = amount;
        this.description = description;
    }
}


