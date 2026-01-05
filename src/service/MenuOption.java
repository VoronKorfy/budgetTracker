package service;

public enum MenuOption {
    DISPLAY_BY_DATE(1),
    SHOW_INCOME_HISTORY(2),
    SHOW_EXPENSE_HISTORY(3),
    ADD_INCOME(4),
    ADD_EXPENSE(5),
    EXIT(0);

    private final int code;

    MenuOption(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MenuOption fromInt(int value) {
        for (MenuOption option : values()) {
            if (option.code == value) {
                return option;
            }
        }
        return null;
    }
}
