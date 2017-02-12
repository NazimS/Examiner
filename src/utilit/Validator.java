/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hp-Pc
 */
public class Validator {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isValidEmail(String email) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        try {
            return hasOneBigLetter(password) && hasOneLittleLetter(password) && hasOneDigit(password);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    private static boolean hasOneBigLetter(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i)) && Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasOneLittleLetter(String password) {

        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i)) && Character.isLowerCase(password.charAt(i))) {
                return true;

            }

        }

        return false;
    }

    private static boolean hasOneDigit(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;

            }

        }

        return false;
    }

}
