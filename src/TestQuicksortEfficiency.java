import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

import edu.uwm.cs351.Quicksort;
import junit.framework.TestCase;

public class TestQuicksortEfficiency extends TestCase {
	Quicksort<Integer> q;
	Collection<Integer> coll;
	Random r;
	
	@Override
	protected void setUp() {
		q = new Quicksort<Integer>(Comparator.naturalOrder());
		coll = new ArrayList<>();
		r = new Random();
	}
	
	protected void addRandom(int n) {
		for (int i=0;i < n; ++i) {
			coll.add(r.nextInt(n*10));
		}
	}
	
	public void test0() {
		addRandom(1<<10);
		q.sort(coll);
	}

	public void test1() {
		addRandom(1<<11);
		q.sort(coll);
	}

	public void test2() {
		addRandom(1<<12);
		q.sort(coll);
	}

	public void test3() {
		addRandom(1<<13);
		q.sort(coll);
	}

	public void test4() {
		addRandom(1<<14);
		q.sort(coll);
	}

	public void test5() {
		addRandom(1<<15);
		q.sort(coll);
	}

	public void test6() {
		addRandom(1<<16);
		q.sort(coll);
	}

	public void test7() {
		addRandom(1<<17);
		q.sort(coll);
	}

	public void test8() {
		addRandom(1<<18);
		q.sort(coll);
	}

	public void test9() {
		addRandom(1<<19);
		q.sort(coll);
	}

}
