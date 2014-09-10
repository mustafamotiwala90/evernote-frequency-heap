//Write a function that takes two parameters: (1) a String representing a text document and (2) an integer providing the number of items to return.
//Implement the function such that it returns a list of Strings ordered by word frequency, the most frequently occurring word first.
//Use your best judgement to decide how words are separated. Your solution should run in O(n) time where n is the number of characters in the document.
//Implement this function as you would for a production/commercial system. You may use any standard data structures.


/*
 * @author : Mustafa Motiwala
 * Main Count String class
 * Creates a HashMap out of Dictionary words
 * Creates a MaxHeap of the words and frequency
 * Inserts each word-frequency pair into the MaxHeap
 * For getting the K words in the Heap we call the Remove/ExtractMax() from the heap k number of times
 * dictionaryText - A String representing a text document
 * number - K element to be returned
 * */


import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;


class Solution {
	public static String dictionaryText = "HELLO HELLO HELLO HELLOHELLO HELLOHELLO HELLOHELLO HELLOHELLO HELLOHELLO HELLOHELLO HELLOHELLO HELLOHELLO HELLOHELLO HELLOHELLOHELLOHELLO HELLOHELLOHELLOHELLOHELLOHELLOHELLOHELLOHELLO HELLOHELLO HELLO HELLO";
	public static final int number = 3;

	public static void main(String[] args) {

		ArrayList<String> listWords = getWordsByFrequency();
	}


	static ArrayList<String> getWordsByFrequency() {
		ArrayList<String> returnedWords = new ArrayList<>();

		if (dictionaryText.length() <= 0)
			return null;

		String[] splittedText = dictionaryText.split("\\s+");

		TreeMap<String, Integer> stringWordCount = new TreeMap<>();

		for (String word : splittedText) {

			if (word.length() <= 0)
				continue;

			Integer count = stringWordCount.get(word);

			if (count == null) {
				stringWordCount.put(word, 1);
			} else {
				stringWordCount.put(word, count + 1);
			}
		}

		int[] frequency = new int[stringWordCount.size()];
		String[] frequentWords = new String[stringWordCount.size()];
		int count = 0;
		for (Entry<String, Integer> entry : stringWordCount.entrySet()) {

			frequency[count] = entry.getValue();
			frequentWords[count] = entry.getKey();
			count++;
		}

		if (number > frequency.length) {
			System.out
			.println("Error number of words required greater than the number of words in the heap");
			return null;
		}


		MaxHeap maxheap = new MaxHeap(frequency, frequentWords, 100);
		maxheap.createHeap();
		maxheap.maxHeap();
		maxheap.print();
		for (int j = 0; j < number; j++) {
			String temp = maxheap.extractMax();
			returnedWords.add(temp);
			System.out.println("WORD removed from heap is : " + temp);
		}

		return returnedWords;

	}

}