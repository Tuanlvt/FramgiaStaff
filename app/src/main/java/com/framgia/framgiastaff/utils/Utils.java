package com.framgia.framgiastaff.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by levutantuan on 7/2/17.
 */

public class Utils {

    public static class DateTimeUntils {

        public static String convertDateToString(Date date) {
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat(Constant.FORMAT_DATE, Locale.getDefault());
            return simpleDateFormat.format(date);
        }
    }

    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\"
                        + ".[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
