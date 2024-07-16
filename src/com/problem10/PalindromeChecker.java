package com.problem10;

import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        // Convert the string to lowercase and remove all non-alphanumeric characters
        s = s.toLowerCase().replaceAll("[^a-z0-9]", "");

        // Use two-pointer technique to check if the string is a palindrome
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a string to check if it is a palindrome:");
        String input = scanner.nextLine();
        
        if (isPalindrome(input)) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }
        
        scanner.close();
    }
}
