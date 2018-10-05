package FirstTerm;

public class RonnieArrayList<E> {
	private Object[] array;
	
	public RonnieArrayList()
	{
		try {
			array = new Object[1];
		}
		
		catch(Exception e) {
			System.out.println("SIKEEE");
		}
	}
	
	public RonnieArrayList(int size)
	{
		array = new Object[size];
	}
}
