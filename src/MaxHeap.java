/*
 * @author : Mustafa Motiwala
 * MaxHeap class
 * Creates a MaxHeap of the words and frequency
 * Inserts each word-frequency pair into the MaxHeap
 * createHeap() -> calls the insert function which inserts each word and corresponding frequency in the respective arrays
 * parent(int pos) - returns the parent of the particular heap node as part of the Heap property
 * leftChild(int pos) - returns the leftChild of the particular heap node
 * rightChild(int pos) - returns the rightChild of the particular heap node
 * swap() -> swaps 2 elements from the word and the respective frequency array
 * maxHeapify() -> takes in a position and checks whether the node needs to be replaced with its child,if yes then it does recursively until all the nodes in the heap are balanced
 * maxHeap() -> initial call to heapify all the elements in the array
 * extractMax() -> a version of the extractMax() method of the Max-heap
 * */

public class MaxHeap {

	private String[] HeapWord;
	private int[] HeapFrequency;
	private int[] numHeap;
	private String[] wordHeap;
	private int maxSize;
	private int size;
	private static final int FRONT = 1;

	public MaxHeap(int[] frequency, String[] frequentWords, int arraySize) {
		this.HeapFrequency = frequency;
		this.HeapWord = frequentWords;
		this.maxSize = arraySize;
		this.size = 0;
		numHeap = new int[this.maxSize + 1];
		wordHeap = new String[this.maxSize + 1];
		numHeap[0] = Integer.MAX_VALUE;
		wordHeap[0] = "/0";
	}

	public void createHeap() {
		for (int i = 0; i < HeapFrequency.length; i++) {
			insert(HeapFrequency[i], HeapWord[i]);
		}
	}

	public int parent(int pos) {
		return pos / 2;
	}

	public int leftChild(int pos) {
		return (2 * pos);
	}

	public int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	private void swap(int fpos, int spos) {
		int tmp;
		String temp;

		tmp = numHeap[fpos];
		temp = wordHeap[fpos];

		numHeap[fpos] = numHeap[spos];
		numHeap[spos] = tmp;

		wordHeap[fpos] = wordHeap[spos];
		wordHeap[spos] = temp;

	}

	private void insert(int element, String word) {

		numHeap[++size] = element;
		wordHeap[size] = word;
		int current = size;

		while (numHeap[current] > numHeap[parent(current)]) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public void print() {
		for (int i = 1; i <= size / 2; i++) {
			System.out.print(" PARENT : " + numHeap[i] + " LEFT CHILD : "
					+ numHeap[2 * i] + " RIGHT CHILD :" + numHeap[2 * i + 1]);
			System.out.println();
		}
	}

	private void maxHeapify(int pos) {
		if (numHeap[pos] < numHeap[leftChild(pos)]
				|| numHeap[pos] < numHeap[rightChild(pos)]) {
			if (numHeap[leftChild(pos)] > numHeap[rightChild(pos)]) {
				swap(pos, leftChild(pos));
				maxHeapify(leftChild(pos));
			} else {
				swap(pos, rightChild(pos));
				maxHeapify(rightChild(pos));
			}
		}

	}

	public void maxHeap() {
		for (int pos = (size / 2); pos >= 1; pos--) {
			maxHeapify(pos);
		}
	}

	public String extractMax() {
		String word = wordHeap[FRONT];
		numHeap[FRONT] = numHeap[size--];
		wordHeap[FRONT] = wordHeap[size];
		maxHeapify(FRONT);
		return word;
	}

}
