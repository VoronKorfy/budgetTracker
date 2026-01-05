package service;

import model.*;

import java.time.LocalDate;
import java.util.Scanner;
import java.lang.Math;
import java.util.function.Predicate;

public class BudgetTrackerService {
    public static final Scanner scanner = new Scanner(System.in);

    public static int todayDate() {
        LocalDate today = LocalDate.now();
        return today.getYear() * 10000
                + today.getMonthValue() * 100
                + today.getDayOfMonth();
    }
    // ---------------- INITIALIZATION ------------------ //

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

    // ---------------- OPERATIONS ------------------ //
    private static void addOperation(BudgetTracker tracker, float amount, String description, int date) {
        DailyRecord day = addDay(tracker, date);
        day.operations.add(new Operation(amount, description));
        System.out.printf("Added operation: %.2f dollars on %d%n", amount, date);
    }

    private static void addOperation(BudgetTracker tracker, float amount, String description) {
        addOperation(tracker, amount, description, todayDate());
    }

    public static void addIncome(BudgetTracker tracker) {
        System.out.print("Enter amount of income: ");
        float amount = scanner.nextFloat();

        if (amount < 0) {
            System.out.println("You can't add a negative amount! Turned into a positive.");
            amount = -amount;
        }

        System.out.print("Enter description: ");
        scanner.nextLine();
        String description = scanner.nextLine();

        System.out.print("Enter date (YYYYMMDD) or write today: ");
        String dayOfOperation = scanner.next();
        if (!dayOfOperation.equals("today")) {
            int dateOfOperation = Integer.parseInt(dayOfOperation);
            addOperation(tracker, amount, description, dateOfOperation);
        }
        else {
            addOperation(tracker, amount, description);
        }
    }


    public static void addExpense(BudgetTracker tracker) {
        System.out.print("Enter the amount you spent: ");
        float amount = scanner.nextFloat();
        if (amount > 0) {
            amount = -amount;
        }

        float currentNetBalance = getNetBalance(tracker);
        if (currentNetBalance < Math.abs(amount)) {
            System.out.printf("Wow, you spent a lot lately! You better start saving!%n Your current net balance is %f", currentNetBalance);
        }

        System.out.print("Enter description: ");
        scanner.nextLine();
        String description = scanner.nextLine();

        System.out.print("Enter date (YYYYMMDD) or write today: ");
        String dayOfOperation = scanner.next();
        if (!dayOfOperation.equals("today")) {
            int dateOfOperation = Integer.parseInt(dayOfOperation);
            addOperation(tracker, amount, description, dateOfOperation);
        }
        else {
            addOperation(tracker, amount, description);
        }
    }

    public static float getDayTotal(BudgetTracker tracker, int date) {
        DailyRecord day = getDayByDate(tracker, date);
        if  (day == null) {
            return 0;
        }
        float total = 0;
        for (Operation t : day.operations) {
            total += t.amount;
        }
        return total;
    }

    public static float getNetBalance(BudgetTracker tracker) {
        float total = 0;
        for (DailyRecord day : tracker.days) {
            total += getDayTotal(tracker, day.date);
        }
        return total;
    }

    public static float getIncome(BudgetTracker tracker) {
        float income = 0;
        for (DailyRecord day : tracker.days) {
            float dayTotal = getDayTotal(tracker, day.date);
            if (dayTotal > 0) {
                income += dayTotal;
            }
        }
        return income;
    }

    public static float getExpense(BudgetTracker tracker) {
        float expense = 0;
        for (DailyRecord day : tracker.days) {
            float dayTotal = getDayTotal(tracker, day.date);
            if (dayTotal < 0) {
                expense += dayTotal;
            }
        }
        return expense;
    }

    private static void showLastOperations(BudgetTracker tracker, Predicate<Float> filter, String emptyMessage) {
        int printed = 0;

        for (int d = tracker.days.size() - 1; d >= 0 && printed < 5; d--) {
            DailyRecord day = tracker.days.get(d);

            for (int t = day.operations.size() - 1; t >= 0 && printed < 5; t--) {
                Operation operation = day.operations.get(t);

                if (filter.test(operation.amount)) {
                    System.out.printf(
                            "%d | %.2f | %s%n",
                            day.date,
                            operation.amount,
                            operation.description
                    );
                    printed++;
                }
            }
        }

        if (printed == 0) {
            System.out.println(emptyMessage);
        }
    }


    public static void showOperationHistory(BudgetTracker tracker, int date) {
        DailyRecord day = getDayByDate(tracker, date);

        if (day == null || day.operations.isEmpty()) {
            System.out.println("There were no operations on the date " + date);
            return;
        }

        float total = getDayTotal(tracker, date);
        System.out.printf("The net income on that day is %.2f%n", total);
        System.out.println("Operations for " + date + ":");

        for (Operation operation : day.operations) {
            System.out.printf(
                    "%d | %.2f | %s%n",
                    day.date,
                    operation.amount,
                    operation.description
            );
        }
    }

    public static void showOperationHistory(BudgetTracker tracker) {
        System.out.printf("Your overall NET income is %.2f%n", getNetBalance(tracker));
        showLastOperations(
                tracker,
                _ -> true,
                "There were no operations yet!"
        );
    }

    public static void showIncomeHistory(BudgetTracker tracker) {
        System.out.printf("Your overall worth of income is %.2f%n", getIncome(tracker));
        showLastOperations(
                tracker,
                amount -> amount > 0,
                "No income operations yet"
        );
    }

    public static void showExpenseHistory(BudgetTracker tracker) {
        System.out.printf("Your overall spending is %.2f%n", getExpense(tracker));
        showLastOperations(
                tracker,
                amount -> amount < 0,
                "No spending operations yet"
        );
    }
}
