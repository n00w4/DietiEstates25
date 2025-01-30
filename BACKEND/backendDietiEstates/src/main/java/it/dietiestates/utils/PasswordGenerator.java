package it.dietiestates.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "@$!%*?&";
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
    private static final SecureRandom random = new SecureRandom();
    private static final int PASSWORD_LENGTH = 12; // Minimo 8, ma meglio 12 per sicurezza

    private PasswordGenerator() {}

    public static String generatePassword() {
        List<Character> passwordChars = new ArrayList<>();

        // Garantisce almeno un carattere di ogni tipo
        passwordChars.add(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        passwordChars.add(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        passwordChars.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        passwordChars.add(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        // Completa il resto dei caratteri casualmente
        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            passwordChars.add(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        // Mischia la password per evitare pattern prevedibili
        Collections.shuffle(passwordChars);

        // Converte la lista in stringa
        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }
}

