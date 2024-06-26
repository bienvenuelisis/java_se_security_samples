package org.theresilient.dev.security.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private static final String PHONE_NUMBER_REGEX = "^\\+229 \\d{2} \\d{3} \\d{3}$";

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        return name != null && name.length() > 1;
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidDate(String date, Optional<String> format) {
        String dateFormat = format.orElse("dd/MM/yyyy");

        try {
            final var __ = parseDate(date, dateFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static LocalDate parseDate(String date, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.parse(date, formatter);
    }
}
