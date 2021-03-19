package ru.bounegru.app;

import java.util.Collections;

public class StringUnpacking {

    private static int globalPointer = 0;

    public static String unpacking(String str) {
        String onlyBrackets = str.replaceAll("[^(\\[&\\])]", "");

        //TODO modify the check of the string for validity, it still
        // grasps some strings (clarify which lines are considered valid)
        // if brackets is empty ("123[]"), is it considered correct?
        if (!checkForBalancedBrackets(onlyBrackets) ||
                !isStringCorrect(str)) {
            return "String isn't correct";
        }
        String result = solve(str);
        globalPointer = 0;
        return result;


    }

    private static String solve(String str) {
        StringBuilder partOfString = new StringBuilder();
        StringBuilder digits = new StringBuilder("");
        // we go through each char
        for (; globalPointer < str.length(); globalPointer++) {
            char current = str.charAt(globalPointer);
            if (Character.isDigit(current)) {
                digits.append(current);
            } else if (Character.isAlphabetic(current)) {
                partOfString.append(current);
            } else if (current == '[') {
                globalPointer++;
                int repeats = 1;
                if (!String.valueOf(digits).equals("")) {
                    repeats = Integer.parseInt(digits.toString());
                }
                partOfString.append(String.join("", Collections.nCopies(repeats, solve(str))));
                digits.setLength(0);
            } else if (current == ']') {
                return String.valueOf(partOfString);
            }
        }

        return String.valueOf(partOfString);
    }

    private static boolean checkForBalancedBrackets(String allParenthesis) {

        int leftCount = (allParenthesis.replace("[", "")).length();
        // check if count of the parenthesis are even
        // and count of left parenthesis are equals to right parenthesis
        if (!(allParenthesis.length() % 2 == 0 &&
                leftCount == allParenthesis.length() / 2)) {
            return false;
        }

        // if current parenthesis is '[' then increment count
        // otherwise decrement
        int count = 0;
        for (int i = 0; i < allParenthesis.length(); i++) {
            if (count < 0) {
                return false;
            }
            char current = allParenthesis.charAt(i);
            count = current == '[' ? count + 1 : count - 1;
        }
        return count == 0;
    }

    private static boolean isStringCorrect(String str) {
        if (str.replaceAll("[\\d\\w\\[\\]]", "").length() != 0) {
            return false;
        }

        char current = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            char nextChar = str.charAt(i);

            // all possible wrong combinations
            if ((Character.isAlphabetic(current) && nextChar == '[') ||
                    (Character.isDigit(current) && (nextChar == ']' || Character.isAlphabetic(nextChar))) ||
                    (current == '[' && (nextChar == ']' || nextChar == '[')) ||
                    (current == ']' && (nextChar == '['))) {
                return false;
            }
            current = nextChar;
        }
        return true;
    }
}
