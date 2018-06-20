package FEEP;

import java.util.*;

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

            System.out.println(isValidExpression2(input) ? "Valid" : "Invalid");

            //System.out.println(Pattern.matches("[a-zA-Z0-9\\+\\-\\*/\\(\\)]*", input) ? "Valid" : "Invalid");
        }
    }



    public static boolean isAnOperator(char c) {
        switch (c) {
            case '*':
            case '/':
            case '+':
            case '-':
            case '%':
                return true;
            default:
                return false;
        }
    }
    public static boolean isALetter(char c){
        return Character.isLetter(c);
    }

    public static boolean isValidExpression2(String expression) {
        // TEST 1
        if (isAnOperator(expression.charAt(0)) || isAnOperator(expression.charAt(expression.length() - 1))) {
            return false;
        }

        int openParenthCount = 0;
        boolean lastWasOp = false;
        boolean lastWasOpen = false;

        for (char c : expression.toCharArray()) {
            if(c == ' ') continue;
            if (c == '(') {
                openParenthCount++;
                lastWasOpen = true;
                continue;
            } else if (c == ')') {
                if (openParenthCount <= 0 || lastWasOp) {
                    return false;
                }
                openParenthCount--;
            }else if (isAnOperator(c)){
                if (lastWasOp || lastWasOpen) return false;
                lastWasOp = true;
                continue;
            }else if(!isALetter(c)){
                return false;
            }
            lastWasOp = false;
            lastWasOpen = false;
        }
        if(openParenthCount != 0) return false;
        if(lastWasOp || lastWasOpen) return false;
        return true;
    }


}
