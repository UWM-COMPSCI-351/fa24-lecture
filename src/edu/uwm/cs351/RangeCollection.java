package edu.uwm.cs351;

import java.util.AbstractCollection;
import java.util.Iterator;

public class RangeCollection extends AbstractCollection<Integer>{

	@Override  //efficiency
	public boolean contains(Object o) {
		if(!(o instanceof Integer)) return false;//throw new IllegalStateException("Invalid Input");
		Integer i = (Integer)o;
		return i>=low && i<high;
	}

	private final int low;
	private final int high;
	public RangeCollection(int low, int high) {
		this.low = low;
		this.high= high;
	}

	@Override //Required
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

	@Override  //Required
	public int size() {
		return high-low;
	}
	
	private class RangeIterator implements Iterator<Integer>{
        private int current=low-1;
		@Override //Required
		public boolean hasNext() {
			return current+1 < high;
		}

		@Override //Required
		public Integer next() {
			return ++current;
		}
		
	}
	
}