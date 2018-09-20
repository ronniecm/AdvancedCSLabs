package FirstTerm;

public class ListNode<E> {
	private E value;
	private ListNode<E> next;
	
	public ListNode(E initVal, ListNode<E> nextNode)
	{
		value= initVal;
		next = nextNode;
	}
	
	public E getValue()
	{
		return value;
	}
	
	public void setValue(E newValue)
	{
		value = newValue;
	}
	
	public ListNode<E> getNext()
	{
		return next;
	}
	
	public void setNext(ListNode<E> nextNode)
	{
		next = nextNode;
	}
}
