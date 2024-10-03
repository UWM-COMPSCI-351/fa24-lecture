package edu.uwm.cs351;

import java.util.Comparator;

public class InsertionSort<E> {

	private final Comparator<E> comp;
	
	public InsertionSort(Comparator<E> c) {
		comp = c;
	}
	
	public void sort(E[] array) 
	{
		// Perform insertion sort on the array
        for (int currIdx = 1; currIdx < array.length; currIdx++) 
        {
            E currVal = array[currIdx];
            int prevIdx = currIdx - 1;
            
            // Move elements of array that are greater than currentValue
            // to one position ahead of their current position
            while (prevIdx >= 0 && comp.compare(array[prevIdx], currVal) > 0) 
            {
                array[prevIdx + 1] = array[prevIdx];
                prevIdx--;
            }
            // move the temporary value to it's correct position
            array[prevIdx + 1] = currVal;
        }
    }
}
