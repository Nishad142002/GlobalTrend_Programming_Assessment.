package com.problem2;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        try {
            // Attempting to modify the list while iterating using enhanced for loop
            for (String item : list) {
                if (item.equals("two")) {
                    list.remove(item); 
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException caught!");
        }

        // Correct way to remove elements using Iterator
        System.out.println("Original list: " + list);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.equals("three")) {
                iterator.remove();
            }
        }
        System.out.println("Modified list: " + list);
    }
}
