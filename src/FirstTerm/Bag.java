package FirstTerm;

public interface Bag {
	boolean add(Object item);
	boolean remove(Object item);
	int numItems();
	Object grab();
	Object[] toArray();
}
