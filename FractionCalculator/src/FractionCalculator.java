/*
Code created by Margot Wagner 3/24/20
Module 2 (Building Your Own Objects) final project
Fraction Calculator
Part of edX Object Oriented Programming in Java by Microsoft
 */

import java.util.Scanner;

public class FractionCalculator {
    // class allowing user to enter in fractions and operations, calculating and displaying the result
    public static void main(String[] args) {
        intro();
        Scanner input = new Scanner(System.in);
        String operation = getOperation(input);

        while (!operation.equals("Q") && !operation.equals("q")) {
            Fraction frac1 = getFraction(input);
            Fraction frac2 = getFraction(input);

            int num1 = frac1.getNumerator();
            int den1 = frac1.getDenominator();
            int num2 = frac2.getNumerator();
            int den2 = frac2.getDenominator();

            // perform operation (+, -, /, *, =)
            if (operation.equals("=")) {
                boolean result = frac1.equals(frac2);
                System.out.println(num1 + "/" + den1 + " " + operation + " " + num2 + "/" + den2 + " is " + result);
            }
            else {
                Fraction result = new Fraction();

                if (operation.equals("+")) {
                    result = frac1.add(frac2);
                }
                else if (operation.equals("-")) {
                    result = frac1.subtract(frac2);
                }
                else if (operation.equals("/")) {
                    result = frac1.divide(frac2);
                }
                else if (operation.equals("*")) {
                    result = frac1.multiply(frac2);
                }

                result.toLowestTerms();

                int resNum = result.getNumerator();
                int resDen = result.getDenominator();

                if (resDen == 1) {
                    System.out.println(num1 + "/" + den1 + " " + operation + " " + num2 + "/" + den2 + " = " + resNum);
                }
                else {
                    System.out.println(num1 + "/" + den1 + " " + operation + " " + num2 + "/" + den2 + " = " +
                            resNum + "/" + resDen);
                }
            }



            System.out.println("---------------------------------------------------------------------------");
            operation = getOperation(input);
        }

    }

    public static String getOperation(Scanner input) {
        // asks the user to enter in a valid math operation. If the user enters anything else, it reprompts until there
        // is valid input.
        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
        String operation = input.nextLine();
        while ((!operation.equals("+")) && (!operation.equals("-")) && (!operation.equals("/")) && (!operation.equals("*"))
                && (!operation.equals("=")) && (!operation.equals("Q")) && (!operation.equals("q"))) {
            System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
            operation = input.nextLine();
        }

        return operation;
    }

    public static boolean validFraction(String input) {
        // returns true is the parameter is in the form "a/b" where a is any int and b is any positive int.
        boolean isValid = false;

        // remove "-" at beginning
        if (input.charAt(0) == '-') {
            input = input.substring(1);
        }

        // another "-" is false
        if (input.contains("-")) {
            isValid = false;
        }

        // contains / or more than one //
        else if (input.contains("/")) {

            // if there are multiple "/", return false
            if (input.indexOf("/") != input.lastIndexOf("/")) {
                isValid = false;
            }

            // if the "/" is at the beginning or end, return false
            else if ((input.indexOf("/") == input.length() - 1) || (input.startsWith("/"))){
                isValid = false;
            }

            // split up num and denom
            else {
                String[] arrNumDenSplit = input.split("/");

                String num = arrNumDenSplit[0];
                String den = arrNumDenSplit[1];

                // If denom is zero, return false
                if (den.startsWith("0")) {
                    isValid = false;
                }

                // If num and denom are not numbers, return false
                else if (!isNumber(num) || !isNumber(den)) {
                    isValid = false;
                }

                // Otherwise, return true
                else { isValid = true; }

            }
        }

        // if no /
        else {
            // if NaN, return false
            if (!isNumber(input)) {
                isValid = false;
            }

            else { isValid = true; }
        }

        return isValid;

    }

    public static Fraction getFraction(Scanner input) {
        // prompts the user for a String that is a validFraction. If they enter anything that is not, it reprompts.
        // returns a Fraction object.
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String strFrac = input.nextLine();
        boolean isValid = validFraction(strFrac);
        while (!isValid) {
            System.out.print("Invalid fraction. Please enter a fraction (a/b) or integer (a), where a and b are" +
                    " integers and b is not zero: ");
            strFrac = input.nextLine();
            isValid = validFraction(strFrac);
        }


        if (strFrac.contains("/")) {
            String[] arrNumDenSplit = strFrac.split("/");

            String numStr = arrNumDenSplit[0];
            String denStr = arrNumDenSplit[1];

            int num = Integer.parseInt(numStr);
            int den = Integer.parseInt(denStr);

            Fraction frac = new Fraction(num, den);

            return frac;

        } else {
            int num = Integer.parseInt(strFrac);
            Fraction frac = new Fraction(num);
            return frac;
        }
    }

    public static boolean isNumber(String input) {
        // takes a string as input and returns true if every character in the string is a number 0-9 and false otherwise
        if (input.matches("\\d+")) {
            return true;
        } else { return false; }
    }

    public static void intro() {
        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
        System.out.println("---------------------------------------------------------------------------");
    }


}
