package service;

import model.BudgetTracker;
import model.DailyRecord;
import model.Transaction;
import java.util.Scanner;

public class BudgetTrackerService {

    public DailyRecord getDayByDate(BudgetTracker tracker, int date) {
        for (DailyRecord day : tracker.days) {
            if (day.date == date) {
                return day;
            }
        }
        return null;
    }

    public DailyRecord addDay(BudgetTracker tracker, int date) {
        if (getDayByDate(tracker, date) != null) {
            return null;
        }
        DailyRecord day = new DailyRecord(date);
        tracker.days.addFirst(day);
        return day;

    }

    public void addTransaction(BudgetTracker tracker,
                               float amount,
                               String description) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter date (YYYYMMDD): ");
        int date = scanner.nextInt();

        DailyRecord day = addDay(tracker, date);
        day.transactions.add(new Transaction(amount, description));
    }


    void income(BudgetTracker tracker,
                float amount,
                String description){

    }

    boolean expense(BudgetTracker tracker,
                    float amount,
                    String description){
        return false;
    }
    float getBalance(BudgetTracker tracker){
        return 0;
    }

    float getDayTotal(BudgetTracker tracker, int date){
        return 0;
    }
    void showLastTransactions(BudgetTracker tracker, int limit){}

    void showDayTransactions(BudgetTracker tracker, int date){}

}
