/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilit;

/**
 *
 * @author Hp-Pc
 */
public class Validator {


    public static boolean isValidPassword(String password) {
        try {
            return hasOneBigLetter(password) && hasOneLittleLetter(password) && hasOneDigit(password);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    public static boolean hasOneBigLetter(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i)) && Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasOneLittleLetter(String password) {

        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i)) && Character.isLowerCase(password.charAt(i))) {
                return true;

            }

        }

        return false;
    }

    public static boolean hasOneDigit(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;

            }

        }

        return false;
    }

}
