package PriorityQueue;

//Name:   
//Date:
//What I learned:
//How I feel about this lab:

//I am wondering (the what-if moment):
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
	 items = new Comparable[initialCapacity];
	 numItems = 0;
 }
 

 
 public boolean isEmpty()
 {
    // your code goes here    
    return numItems == 0;  
 
 }
 
 public E peek()
 {
   return (E) items[1];  
  
 }
 
 public E remove()
 {
	Comparable temp = items[1];
	items[1] = items[numItems];
	items[numItems--] = null;
	
	reheapDown();
    return (E) temp;  
 }
 
 public boolean add(E obj)
 {
    if(numItems == items.length - 1)
    	doubleCapacity();
   items[++numItems] = obj;
   reheapUp();
   return true;
 }
 
    
 private void reheapDown()
 {
	 int parentIndex = 1;
	 int smallerChildIndex = 2 * parentIndex + (items[2 * parentIndex].compareTo(items[2 * parentIndex + 1]) < 0 ? 0 : 1);
	 
	 while(items[2 * parentIndex] != null && items[2 * parentIndex + 1] != null)
	 {
		 swap(parentIndex, smallerChildIndex);
		 parentIndex = smallerChildIndex;
		 if(items[2 * parentIndex] == null || items[2 * parentIndex + 1] == null)
			 break;
		 smallerChildIndex = 2 * parentIndex + (items[2 * parentIndex].compareTo(items[2 * parentIndex + 1]) < 0 ? 0 : 1);
	 }
 }
 
 private void reheapUp()
 {
	 int child = numItems;
	 int parent = child % 2 == 0 ? child / 2 : (child - 1) / 2;
	 while(items[child] != null && items[parent] != null && items[child].compareTo(items[parent]) < 0)
	 {
		 swap(child, parent);
		 child = parent;
		 parent = child % 2 == 0 ? child / 2 : (child - 1) / 2;
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
	 System.out.println("Item to be removed: " + heap.remove());
	 System.out.println(heap);
	 System.out.println("Peek at root: " + heap.peek());
 }
}  //HeapPriorityQueue_shell
