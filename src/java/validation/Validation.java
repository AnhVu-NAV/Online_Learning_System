/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;
import java.util.Scanner;
/**
 *
 * @author 84941
 */
public class Validation {
    Scanner sc = new Scanner(System.in);

    public String checkInputString() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.err.println("Enter again");
            } else {
                return result;
            }
        }

    }

    public boolean checkInputYN() {
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y of n/N");
            System.err.println("Enter again");

        }
    }

    public int checkInputLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;

            } catch (NumberFormatException e) {
                System.err.println("Please enter number within range[" + min + "," + max + "]");
                System.err.println("Enter again");
            }
        }
    }

    public int checkInputPositiveInt() {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < 0) {
                    System.err.println("Input must be positive");
                    System.err.println("Enter again");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Input must be integer");
                System.err.println("Enter again");
            }
        }
    }



    public boolean checkDegitString(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (((s.charAt(i) < '0') || (s.charAt(i) > '9'))) {
                return false;
            }
        }
        return true;
    }

    public String checkInputPhone() {
        while (true) {
            String result = checkInputString();
            if (!checkDegitString(result)) {
                System.err.println("All characters must be number from 0 to 9");
                System.err.println("Enter again");
                continue;
            }
            if (result.length() < 10) {
                System.err.println("Phone number must contain at least 10 characters");
                System.err.println("Enter again");
                continue;
            }
            return result;
        }
    }

    public String checkInputEmail() {
        String email_valid = "^[A-Za-z0-9.+-_%]+@[A-Za-z.-]+\\.[A-Za-z]{2,4}$";
        /*
            ^: Anchors the pattern to the beginning of the string. This ensures that the pattern matches from the start of the string
            [A-Za-z0-9+_.-]: This character class matches any alphanumeric character (A-Z, a-z, 0-9) and the special characters +, _, ., and -
            +: The + symbol indicates that the previous character class can occur one or more times
            @: This symbol matches the literal "@" character in the email.
            \\.: This matches the literal dot (.) character. The backslash is used to escape the dot because dot has a special meaning in regular expressions.
        ([A-Za-z]{2,4}): This part matches the "top-level domain" part of the email.
        It matches two to four alphabetic characters (A-Z, a-z), which represent common top-level domain extensions (e.g., .com, .edu).
            The {2,4} quantifier specifies that the previous element should occur between 2 and 4 times.
            $: Anchors the pattern to the end of the string. This ensures that the pattern matches up to the end of the string.
         */
        while (true) {
            String result = checkInputString();
            if (result.matches(email_valid)) {
                return result;
            } else {
                System.err.println("Email with format <account name>@<domain>");
                System.err.println("Enter again");
            }
        }

    }


}
