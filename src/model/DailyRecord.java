package model;

public class DailyRecord {
    private int blockID;
    private int date;
    private Transaction transactions;
    private DailyRecord next;

    public DailyRecord(int blockID, int date) {
        this.blockID = blockID;
        this.date = date;
        this.transactions = null;
        this.next = null;
    }

    public int getDailyRecordID() {
        return blockID;
    }
    public int getDate() {
        return date;
    }
    public Transaction getTransactions() {
        return transactions;
    }
    public void setTransactions(Transaction transactions) {
        this.transactions = transactions;
    }
    public DailyRecord getNext() {
        return next;
    }
    public void setNext(DailyRecord next) {
        this.next = next;
    }
}
