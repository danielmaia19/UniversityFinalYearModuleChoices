package model;

import java.util.Arrays;

/**
 * Enums used to represent the run plan for Modules that are associated to either,
 * Term 1, Term 2 or Year Long modules.
 *
 * @author P1718603X
 */
public enum Delivery {

    // Enums
    TERM_1("term 1"),
    TERM_2("term 2"),
    YEAR_LONG("year long");

    // Fields
    private String term;

    // Constructor

    /**
     * The string value passed to the constructor sets the term value.
     *
     * @param value to set the string term value.
     */
    Delivery(String value) {
        this.term = value;
    }

    /**
     * Checks the string value from converting a string to the enum.
     *
     * @param value to be passed to convert to a enum.
     * @return the enum version for the string passed.
     * @throws IllegalArgumentException is sent when a incorrect Delivery enum value is provided in the file.
     */
    public static Delivery convertFromString(String value) throws IllegalArgumentException {
        return Arrays.stream(Delivery.values()).filter(text -> text.toString().equals(value)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("There was a incorrect value for delivery " + "\"" + value + "\""));
    }

    /**
     * A string representation of the enum value.
     *
     * @return the string value of the enum.
     */
    @Override
    public String toString() {
        return term;
    }
}
