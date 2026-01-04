package model;

import java.util.ArrayList;

public class DailyRecord {
    public int date; // YYYYMMDD
    public ArrayList<Transaction> transactions = new ArrayList<>();

    public DailyRecord(int date) {
        this.date = date;
    }
}
