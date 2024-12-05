package edu.uwm.cs351;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Quicksort<E> {
	private final Comparator<E> comparator;
	
	public Quicksort(Comparator<E> c) {
		comparator = c;
	}
	
	/**
	 * Sort the collection (without changing it) and return a new sorted collection
	 * @param source collection to sort, must not be null
	 * @return sorted collection.
	 */
	public Collection<E> sort(Collection<E> source) {
		List<E> result = new ArrayList<E>(source);
		// insertion sort
		for (int i=1; i < result.size(); ++i) {
			E elem = result.get(i);
			int hole = i;
			while (hole > 0 && comparator.compare(result.get(hole-1), elem) > 0) {
				result.set(hole,  result.get(hole-1));
				--hole;
			}
			result.set(hole, elem);
		}
		return result;
	}
}
