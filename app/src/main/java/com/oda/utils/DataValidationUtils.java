package com.oda.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class DataValidationUtils {

    public static boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String phrase) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9]{8,24}"
        );
        return !TextUtils.isEmpty(phrase) && PASSWORD_PATTERN.matcher(phrase).matches();
    }
}
