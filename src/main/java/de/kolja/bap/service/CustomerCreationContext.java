package de.kolja.bap.service;

public class CustomerCreationContext {

    private static String firstName;
    private static String lastName;

    public static void setFirstName(String value) {
        firstName = value;
    }

    public static void setLastName(String value) {
        lastName = value;
    }

    public static String getFullName() {
        if (firstName == null || lastName == null) {
            return "unnamed_";
        }
        return firstName + "_" + lastName;
    }

    public static void clear() {
        firstName = null;
        lastName = null;
    }
}