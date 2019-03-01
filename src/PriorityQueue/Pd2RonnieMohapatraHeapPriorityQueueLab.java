package PriorityQueue;

//Name: Ronnie Mohapatra   
//Date: 2/27/2019
//What I learned: a). How to organize items into a min heap
//				  b). How to reheap up and down a list
//				  c). How to add and remove items into and from a heap
//How I feel about this lab: I feel that I learned a very complex topic and wrote very efficeint code on it. I think it is very interesting how this is a good data structure for a PriorityQueue

//I am wondering (the what-if moment): What if we wanted to created a max heap instead of a min heap?
//Credits:
import java.util.*;
public class Pd2RonnieMohapatraHeapPriorityQueueLab <E extends Comparable>
{
	private static final int DEFAULT_CAPACITY = 1024;
	private Comparable items[];  // use a 1-D array instead of ArrayList
	private int numItems;  // number of elements in items

	public Pd2RonnieMohapatraHeapPriorityQueueLab()
	{
		items = new Comparable[DEFAULT_CAPACITY];
		numItems = 0;
	}

	public Pd2RonnieMohapatraHeapPriorityQueueLab (int initialCapacity)
	{
		items = new Comparable[initialCapacity + 1];
		numItems = 0;
	}
	
	public boolean isEmpty()
	{
		// your code goes here    
		return numItems == 0;  
	}
	
	public E peek()
	{
		return (E)items[1];  
	}
	
	public E remove()
	{
		Comparable temp = items[1]; //save the node to be deleted
		//make the root the right-most leaf and make the right-most leaf null
		items[1] = items[numItems];
		items[numItems--] = null;
		reheapDown(); //heap down to restore heap order
		return (E)temp;  
	}
	
	public boolean add(E obj)
	{
		if(numItems == items.length - 1)
			doubleCapacity(); //double capacity if not enough slots
		items[++numItems] = obj; //increase numItems then append obj to array
		reheapUp(); //heap up to restore heap order
		return true;
	}
	
	private void reheapDown()
	{
		int parentIndex = 1;
		while(2 * parentIndex <= numItems && !isLessThanChildren(parentIndex)) //loop if current has a child and is less that both of its children
		{
			if(2 * parentIndex + 1 > numItems) //if right child doesn't exist, swap current to left child and set current to left child
			{
				swap(parentIndex, 2 * parentIndex);
				parentIndex *= 2;
			} else { //else determine smaller child and swap current with smaller child then set current to smaller child index
				int smallerChildIndex = 2 * parentIndex + (items[2 * parentIndex].compareTo(items[2 * parentIndex + 1]) < 0 ? 0 : 1);
				swap(parentIndex, smallerChildIndex);
				parentIndex = smallerChildIndex;
			}
		}
	}
	
	private boolean isLessThanChildren(int parentIndex)
	{
		return items[parentIndex].compareTo(items[2 * parentIndex]) < 0 && items[parentIndex].compareTo(items[2 * parentIndex + 1]) < 0;
	}

	private void reheapUp()
	{
		int child = numItems;
		while(child / 2 >= 1 && items[child].compareTo(items[child / 2]) < 0) //loop until the parent is the root or the current child is greater than its parent
		{
			swap(child, child/2); //swap child with its parent
			child /= 2; //set current to parent's index
		}
	}
	
	private void doubleCapacity()
	{
		Comparable[] temp = new Comparable[2 * items.length];
		for(int i = 0; i < items.length; i++)
			temp[i] = items[i];
		items = temp;
	}
	
	private void swap(int a, int b)
	{
		Comparable temp = items[a];
		items[a] = items[b];
		items[b] = temp;
	}
	
	public String toString()
	{
		String result = "";
		for(int i = 1; i <= numItems; i++)
			result += items[i] + " ";
		return result;
	}
	public static void main(String[] args)
	{
		System.out.println("Using Default Constructor");
		Pd2RonnieMohapatraHeapPriorityQueueLab<Integer> heap = new Pd2RonnieMohapatraHeapPriorityQueueLab<Integer>();
		heap.add(1);
		heap.add(7);
		heap.add(14);
		heap.add(9);
		heap.add(3);
		heap.add(5);
		heap.add(7);
		heap.add(2);
		heap.add(0);
		System.out.println(heap);
		System.out.println("Adding 6");
		heap.add(6);
		System.out.println(heap);
		System.out.println("Item to be removed: " + heap.remove());
		System.out.println(heap);
		System.out.println("Peek at root: " + heap.peek());

		System.out.println("\nUsing Non Default Constructor");
		Pd2RonnieMohapatraHeapPriorityQueueLab<Integer> heap2 = new Pd2RonnieMohapatraHeapPriorityQueueLab<Integer>(5);
		heap2.add(1);
		heap2.add(7);
		heap2.add(14);
		heap2.add(9);
		heap2.add(3);
		heap2.add(5);
		heap2.add(7);
		heap2.add(2);
		heap2.add(0);
		System.out.println(heap2);
		System.out.println("Adding 6");
		heap2.add(6);
		System.out.println(heap2);
		System.out.println("Item to be removed: " + heap2.remove());
		System.out.println(heap2);
		System.out.println("Peek at root: " + heap2.peek());
	}
}  

/* Output
0 1 5 2 6 14 7 9 3 7 
Item to be removed: 0
1 2 5 3 6 14 7 9 7 
Peek at root: 1
Using Non Default Constructor
0 1 5 2 7 14 7 9 3 
Adding 6
0 1 5 2 6 14 7 9 3 7 
Item to be removed: 0
1 2 5 3 6 14 7 9 7 
Peek at root: 1
*/