package com.problem5;

public class TrieNode {
	
	TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26]; // For lowercase letters a-z
        isEndOfWord = false;
    }
    
}
