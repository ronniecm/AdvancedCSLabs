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
		numItems--;
		int loc = -1;
		
		for(int i = 0; i < items.length; i++)
		{
			if(items[i].equals(item))
				loc = i;
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
	
	private void resizeArray(int size)
	{
		Object[] resizedArray = new Object[size];
		//items = new Object[size];
		if(numItems < items.length)
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
		String[] words = {"hi", "there", "hello", "bye"};
		Bag ofWords = new ArrayBag(words);
		ofWords.remove("hello");
		ofWords.add("ronnie");
		ofWords.remove("bye");
		for(Object word : ofWords.toArray())
			System.out.println(word);
			
	}
}
