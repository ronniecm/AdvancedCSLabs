//Name:  Ronnie Mohapatra  
//Date: March 4, 2019
// What I learned: a). Algorithm of using a heap to sort elements in a list
//                 b). Good refresher on Math.random()
//                 c). Algorithm for making a heap out of randomly sorted elements
// How I feel about this lab: I think it was fun finding another way to sort items and I would love to more.
// I am wondering: What are other things that are good with heaps other than priority queues?

package PriorityQueue;

import java.text.DecimalFormat;

public class Pd2RonnieMohapatraHeapSortLab
{
    public static final int SIZE = 10;
    public static void main(String[] args)
    {
        //Part 1: Given a heap, sort it. Do this part first.   
        double heap[] = {-1,99,80,85,17,30,84,2,16,1};
        display(heap);
        sort(heap);
        display(heap);
      
        //Part 2:  Generate 10 random numbers, make a heap, sort it.
        
        heap = new double[SIZE + 1];
        createRandom(heap);
        System.out.print("Generated random list:    " );
        display(heap);
        makeHeap(heap);
        System.out.print("Made into a max heap:    ");
        display(heap); 
        sort(heap);
        System.out.print("Sorted:    ");
        display(heap);
   }
   
	//******* Part 1 ******************************************
    public static void display(double[] array)
    {
        for(int k = 1; k < array.length; k++)
            System.out.print(array[k] + "    ");
        System.out.println("\n");	
    }
    
    public static void sort(double[] array)
    {
        int first = 1;
        int last = array.length - 1;

        while(first != last)
        {
            swap(array, first, last--);
            heapDown(array, first, last);
        }
    }

    public static void swap(double[] array, int a, int b)
    {
        double temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
   
    public static void heapDown(double[] array, int k, int size)
    {
        while(2 * k <= size && !isGreaterThangreaterChildren(array, k, size)) //loop iff current node has a greaterChild and the current node is greater its greaterChildren
        {
            int greaterChild;
            if(2 * k + 1 > size) //if right greaterChild does not exist set greaterChild to left child index
                greaterChild = 2 * k;
            else //use turnary operator to set greaterChild to index of larger child
                greaterChild = 2 * k + (array[2 * k] > array[2 * k + 1] ? 0 : 1);
            
            swap(array, k, greaterChild); //swap current node with its larger child
            k = greaterChild; //set current node to greater child index
        }
    }
   
    // ****** Part 2 *******************************************
    public static void makeHeap(double[] array)
    {
        for(int i = array.length - 1; i >= 2; i--)
            heapDown(array, i/2, array.length - 1); //heap down from the current's parent node
    }
   
    //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
    public static void createRandom(double[] array)
    {  
        DecimalFormat d = new DecimalFormat("0.00");
        for(int i = 1; i < array.length; i++)
        {
            array[i] = Double.parseDouble(d.format(Math.random() * 100 + 1));
        }
    }

    public static boolean isGreaterThangreaterChildren(double[] array, int current, int size)
    {
        //turnary operator used in case node has only one child
        return array[current] > array[2 * current] && (2 * current + 1 <= size ? array[current] > array[2 * current + 1] : true);
    }
}
 /* Output

99.0    80.0    85.0    17.0    30.0    84.0    2.0    16.0    1.0    

1.0    2.0    16.0    17.0    30.0    80.0    84.0    85.0    99.0    

Generated random list:    89.02    37.32    2.42    91.21    60.37    17.48    94.92    83.74    12.37    78.57    

Made into a max heap:    94.92    91.21    89.02    83.74    78.57    17.48    2.42    37.32    12.37    60.37    

Sorted:    2.42    12.37    17.48    37.32    60.37    78.57    83.74    89.02    91.21    94.92  

*/