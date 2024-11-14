package edu.uwm.cs351;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * An implementation of the collection interface 
 * specialized to {@link String} objects.
 * The implementation uses dynamic arrays.
 */
public class ArrayCollection<E> extends AbstractCollection<E>
{
	@SuppressWarnings("unchecked")
	private E[] makeArray(int newCap) {
		return (E[]) new Object[newCap];
	}

	private static int INITIAL_CAPACITY = 1;

	private static Consumer<String> reporter = (s) -> System.out.println("Invariant error: "+ s);
	
	private boolean report(String error) {
		reporter.accept(error);
		return false;
	}

	private E[] data;
	private int manyItems;
	private int version;

	public E getData(int i) {
		return data[i];
	}

	private boolean wellFormed() {
		// Check the invariant.
		// 1. data is never null
		if (data == null) return report("data is null"); // test the NEGATION of the condition

		// 2. The data array is at least as long as the number of items
		//    claimed by the sequence.
		if (data.length < manyItems) return report("data is too short");

		// 3. The number of items cannot be negative
		if (manyItems < 0) return report("manyItems is negative");
		
		// If no problems discovered, return true
		return true;
	}
	
	public ArrayCollection() {
		data = makeArray(INITIAL_CAPACITY);
		assert wellFormed() : "invariant broken at beginning";
	}

	@Override // required
	public int size() {
		assert wellFormed() : "invariant broken at start of iterator()";
		return manyItems;
	}

	@Override // implement
	public boolean add(E b) {
		assert wellFormed() : "invariant broken at start of add()";
		ensureCapacity(manyItems+1);

		data[manyItems++] = b;
		++version;
		
		assert wellFormed() : "invariant broken at end of add()";
		return true;
	}
	
	@Override // efficiency
	public void clear() {
		assert wellFormed() : "invariant broken at start of clear()";
		data = makeArray(INITIAL_CAPACITY);
		if (manyItems != 0) {
			++version;
			manyItems = 0;
		}
		assert wellFormed() : "invariant broken at end of clear()";
	}
	
	/**
	 * Change the current capacity of this sequence.
	 * @param minimumCapacity
	 *   the new capacity for this sequence
	 * @postcondition
	 *   This sequence's capacity has been changed to at least minimumCapacity.
	 *   If the capacity was already at or greater than minimumCapacity,
	 *   then the capacity is left unchanged.
	 *   If the size is changed, it must be at least twice as big as before.
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for: new array of minimumCapacity elements.
	 **/
	private void ensureCapacity(int minimumCapacity)
	{
		if (data.length >= minimumCapacity) return;
		int newCap = data.length*2;
		if (newCap < minimumCapacity) newCap = minimumCapacity;
		E[] newData = makeArray(newCap);
		for (int i=0; i < manyItems; ++i) {
			newData[i] = data[i];
		}
		data = newData;
		return;
	}

	@Override // required
	public Iterator<E> iterator() {
		assert wellFormed() : "invariant broken at start of iterator()";
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<E>
	{
		int index;
		boolean canRemove;
		int colVersion;
		
		private boolean wellFormed() {
			if (!ArrayCollection.this.wellFormed()) return false;
			if (version != colVersion) return true; // iterator is irrelevant
			if (index < -1 || index >= manyItems) return report("index out of range");
			if (canRemove && index == -1) return report("cannot remove element at -1");
			return true;
		}
		
		MyIterator() {
			index = -1;
			canRemove = false;
			colVersion = version;
			assert wellFormed() : "iterator constrictor fails invariant";
		}
		
		private void checkVersion() {
			if (version != colVersion) throw new java.util.ConcurrentModificationException("version mismatch");
		}
		
		@Override // required
		public boolean hasNext() {
			assert wellFormed() : "invariant broken in hasNext()";
			checkVersion();
			return ((index+1) < manyItems);
		}

		@Override // required
		public E next() {
			if (!hasNext()) throw new java.util.NoSuchElementException("no more");
			canRemove = true;
			return data[++index];
		}
		
		@Override // implement
		public void remove() {
			assert wellFormed() : "invariant broken in remove()";
			checkVersion();
			if (!canRemove) throw new IllegalStateException("nothing to remove");
			for (int i=index+1; i < manyItems; ++i) {
				data[i-1] = data[i];
			}
			--manyItems;
			--index;
			canRemove = false;
			colVersion = ++version;
		}
	}
}
