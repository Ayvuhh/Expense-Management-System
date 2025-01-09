package model;

public enum Currency {
    STERLING("Sterling"),
    DOLLAR("US Dollar"),
    EURO("Euro"),
    YUAN("Chinese Yuan"),
    YEN("Japanese Yen");

    private final String displayName;
    Currency(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static Currency get(String displayName) {
        for (Currency currency : values()) {
            if (currency.displayName.equals(displayName))
                return currency;
        }
        return null;
    }
}
