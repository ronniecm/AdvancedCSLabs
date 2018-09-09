package FirstTerm;

public class ArrayBag implements Bag 
{
	private Object[] items;
	private int numItems;
	
	public ArrayBag(Object[] items)
	{ 
		this.items = items;
		numItems = this.items.length;
	}
	
	@Override
	public boolean add(Object item)
	{
		numItems++;
		resizeArray(numItems);
		items[items.length - 1] = item;
		return true;
	}

	@Override
	public boolean remove(Object item) 
	{
		int loc = -1;
		int i = 0;
		while(loc == -1 || i != items.length)
		{
			if(items[i].equals(item))
				loc = i;
			i++;
		}
		
		if(loc != items.length - 1)
		{
			for(int j = loc; j < items.length - 1; j++)
			{
				Object temp = items[j];
				items[j] = items[j+1];
				items[j+1] = temp;
			}
		}
		
		numItems--;
		resizeArray(numItems);
		return true;
	}
	

	@Override
	public int numItems()
	{
		// TODO Auto-generated method stub
		return numItems;
	}

	@Override
	public Object grab() 
	{
		// TODO Auto-generated method stub
		return items[(int)(Math.random() * items.length)];
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return items;
	}
	
	public void printBag()
	{
		for(Object item : items)
			System.out.print(item + " ");
	}
	
	private void resizeArray(int size)
	{
		Object[] resizedArray = new Object[size];
		//items = new Object[size];
		if(size < items.length)
		{
			for(int i = 0; i < resizedArray.length; i++)
			{	
				resizedArray[i] = items[i];
			}
		}
		else
		{
			for(int i = 0; i < items.length; i++)
			{	
				resizedArray[i] = items[i];
			}
		}
			
		items = resizedArray;
	}
	
	public static void main(String[] args)
	{
		Polynomial p = new ArrayBasedPoly(5, 2);
		Polynomial p1 = new ArrayBasedPoly(7, 4);
		Polynomial p2 = new ArrayBasedPoly(3, 1);
		
		Polynomial[] polys = {p, p1, p2};
		Bag ofPolys = new ArrayBag(polys);
		
		ofPolys.printBag();
		
		ofPolys.add(new ArrayBasedPoly(2, 5));
		
		System.out.println();
		
		ofPolys.printBag();
		
		System.out.println();
		
		ofPolys.remove(ofPolys.toArray()[1]);
		
		ofPolys.printBag();
		
		System.out.println("\nNumber of Items in the Bag: " + ofPolys.numItems());
		System.out.println("Grabbing a random item from the bag: " + ofPolys.grab());
		
	}
}