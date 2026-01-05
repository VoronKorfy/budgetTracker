package service;

import model.*;
import java.util.Scanner;

public class BudgetTrackerService {

    public static DailyRecord getDayByDate(BudgetTracker tracker, int date) {
        for (DailyRecord day : tracker.days) {
            if (day.date == date) {
                return day;
            }
        }
        return null;
    }

    public static DailyRecord addDay(BudgetTracker tracker, int date) {
        if (getDayByDate(tracker, date) != null) {
            return getDayByDate(tracker, date);
        }
        DailyRecord day = new DailyRecord(date);
        tracker.days.addFirst(day);
        return day;
    }

    public static void addTransaction(BudgetTracker tracker,
                               float amount,
                               String description) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter date (YYYYMMDD): ");
        int date = scanner.nextInt();

        DailyRecord day = addDay(tracker, date);
        day.transactions.add(new Transaction(amount, description));
        System.out.printf("Added transaction: %.2f dollars on %d\n", amount, date);
    }


    public static void addIncome(BudgetTracker tracker) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter amount of income: ");
        float amount = scanner.nextFloat();

        if (amount < 0) {
            System.out.println("You can't add a negative amount! Turned into a positive.");
            amount = -amount;
        }

        System.out.print("Enter description: ");
        scanner.nextLine();
        String description = scanner.nextLine();

        addTransaction(tracker, amount, description);
    }


    public static void addExpense(BudgetTracker tracker) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter amount of expense: ");
        float amount = scanner.nextFloat();
        float currentBalance = getBalance(tracker);
        if (currentBalance < amount) {
            System.out.printf("Wow, your expenses are big lately! You better start saving!%n", currentBalance);
        }
        if (amount > 0) {
            amount = -amount;
        }



        System.out.print("Enter description: ");
        scanner.nextLine();
        String description = scanner.nextLine();

        addTransaction(tracker, amount, description);
    }

    public static float getDayTotal(BudgetTracker tracker, int date) {
        DailyRecord day = getDayByDate(tracker, date);
        if (day == null) {
            return 0;
        }

        float total = 0;
        for (Transaction t : day.transactions) {
            total += t.amount;
        }
        return total;
    }

    public static float getBalance(BudgetTracker tracker) {
        float total = 0;
        for (DailyRecord day : tracker.days) {
            total += getDayTotal(tracker, day.date);
        }
        return total;
    }

    public static void showLastTransactions(BudgetTracker tracker, int limit){}

    public static void showDayTransactions(BudgetTracker tracker, int date){}

}
