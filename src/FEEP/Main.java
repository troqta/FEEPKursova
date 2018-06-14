package FEEP;

import java.util.*;
import java.util.regex.Pattern;

public class Main {

    static final List<Character> signs = Arrays.asList('+', '-', '*', '/');

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Enter the expression you wish to evaluate. If you want to exit type exit");
            input = in.nextLine();

            if (input.equals("exit")) {
                return;
            }

            System.out.println(eval(input.toCharArray()) ? "Valid" : "Invalid");

            //System.out.println(Pattern.matches("[a-zA-Z0-9\\+\\-\\*/\\(\\)]*", input) ? "Valid" : "Invalid");
        }
    }

    private static boolean eval(char[] chars) {
        if (chars[0] != '(' && !Character.isLetter(chars[0]) || chars.length < 3 || chars[0] == ')') {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        int openingBrackets = 0;
        int closingBrackets = 0;
        if (chars[0] == '(') {
            openingBrackets++;
        }
        stack.push(chars[0]);


        for (int i = 1; i < chars.length; i++) {
            if (Character.isDigit(stack.peek())) {
                return false;
            }
            if (chars[i] == '(') {
                if (Character.isLetter(stack.peek())) {
                    return false;
                }
                openingBrackets++;
                stack.push(chars[i]);
                continue;
            }
            if (Character.isLetter(stack.peek())) {
                if (!signs.contains(chars[i]) && chars[i] != ')') {
                    return false;
                }
                if (chars[i] == ')') {
                    closingBrackets++;
                }
                stack.push(chars[i]);
            } else if (signs.contains(stack.peek())) {
                if (!Character.isLetter(chars[i]) && chars[i] != '(') {
                    return false;
                }
                if (chars[i] == '(') {
                    openingBrackets++;
                }
                stack.push(chars[i]);

            } else if (stack.peek() == '(') {
                if (signs.contains(chars[i]) || chars[0] == ')') {
                    return false;
                }
                stack.push(chars[i]);
            } else if (stack.peek() == ')') {
                if (!signs.contains(chars[i]) && chars[i] != ')') {
                    return false;
                }
                stack.push(chars[i]);
            }


        }
        if (stack.peek() != ')' && !Character.isLetter(stack.peek())) {
            return false;
        }
        return openingBrackets == closingBrackets;
    }
}
