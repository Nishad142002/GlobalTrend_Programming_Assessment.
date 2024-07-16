package com.problem6;

import java.util.Stack;

public class ValidParentheses {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String test1 = "()";
        String test2 = "()[]{}";
        String test3 = "(]";
        String test4 = "([)]";
        String test5 = "{[]}";

        System.out.println(test1 + " is valid: " + isValid(test1));
        System.out.println(test2 + " is valid: " + isValid(test2));
        System.out.println(test3 + " is valid: " + isValid(test3));
        System.out.println(test4 + " is valid: " + isValid(test4));
        System.out.println(test5 + " is valid: " + isValid(test5));
    }
}
