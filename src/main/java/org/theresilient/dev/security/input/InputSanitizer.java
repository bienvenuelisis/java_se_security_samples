package org.theresilient.dev.security.input;

public class InputSanitizer {
    public static String sanitize(String input) {
        return input.replaceAll("[^a-zA-Z0-9@.\\s]", "");
    }
}
