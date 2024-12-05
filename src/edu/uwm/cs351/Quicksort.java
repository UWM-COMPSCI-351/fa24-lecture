package edu.uwm.cs351;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Quicksort<E> {
	private final Comparator<E> comparator;
	int i,j;
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
		quickSort(result,0,result.size()-1);
		return result;
	}

	private void quickSort(List<E> result, int min, int max) {
        if(max>min) {
        i = 0;
        j = 0;
        partition(result,min,max);
        quickSort(result,min,j);
        quickSort(result,i,max);
        }
		
	}

	private void partition(List<E> result, int min, int max) {
		i = min;
		j = max+1;
		
		int p= min;
		int q = max+1;
		E pivot = result.get(min);
		
		while(true) {
			while(comparator.compare(result.get(++i),pivot)<0) {
				if(i == max) {
					break;
				}
			}
			
			while(comparator.compare(result.get(--j),pivot)>0) {}
			
			if(j<=i) {
				break;
			}
			
			E temp = result.get(i);
			result.set(i, result.get(j));
			result.set(j, temp);
			
			if(comparator.compare(result.get(i),pivot)==0) {
				++p;
				temp = result.get(p);
				result.set(p, result.get(i));
				result.set(i, temp);	
			}
			if(comparator.compare(result.get(j),pivot)==0) {
				--q;
				temp = result.get(q);
				result.set(q, result.get(j));
				result.set(j, temp);	
			}	
		}
		E temp = result.get(p);
		result.set(p, result.get(j));
		result.set(j, temp);	

		i = j+1;
		for(int k = max;k>q;k--,i++) {
			temp = result.get(k);
			result.set(k, result.get(i));
			result.set(i, temp);	
		}
		
		j = j-1;
		for(int k = min;k<p;k++,j--) {
			temp = result.get(k);
			result.set(k, result.get(j));
			result.set(j, temp);	
		}

	}
}
