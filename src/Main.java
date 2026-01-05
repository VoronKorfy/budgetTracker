import model.*;
import service.*;
import static service.BudgetTrackerService.*;

void main() {
    System.out.println("Welcome to the Budget Tracker Service! Here you can track all your expenses and incomes and see the stats!");
    BudgetTracker tracker = new BudgetTracker();
    LocalDate today = LocalDate.now();
    int todayDate = today.getYear()*10000+today.getMonthValue()*100+today.getDayOfMonth();
    DailyRecord firstDay = addDay(tracker, todayDate);

    System.out.println("1. Display all transactions on a date");
    System.out.println("2. Display the income history");
    System.out.println("3. Display the expenses history");
    System.out.println("4. Add an income");
    System.out.println("5. Add a payment");
    System.out.println("0. Exit ");
    System.out.println();

    while (true) {
        System.out.print("Enter your choice: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        MenuOption option = MenuOption.fromInt(choice);

        if (option == null) {
            System.out.println("Invalid choice");
            continue;
        }

        switch (option) {
            case DISPLAY_BY_DATE -> System.out.println("First");
            case SHOW_INCOME_HISTORY -> System.out.println("Second");
            case SHOW_EXPENSE_HISTORY -> System.out.println("Third");
            case ADD_INCOME -> addIncome(tracker);
            case ADD_EXPENSE -> addExpense(tracker);
            case EXIT -> System.exit(0);
        }
    }

}
