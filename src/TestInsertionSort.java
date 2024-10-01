import java.util.Comparator;

import edu.uwm.cs351.InsertionSort;
import junit.framework.TestCase;

public class TestInsertionSort extends TestCase {

	InsertionSort<String> isort;
	String[] array;
	
	public void test0() {
		isort = new InsertionSort<String>(Comparator.naturalOrder());
		array = new String[]{"aa", "A", "zz","BB"};
		isort.sort(array);
		assertEquals("A", array[0]);
		assertEquals("BB", array[1]);
		assertEquals("aa", array[2]);
		assertEquals("zz", array[3]);
	}
	
	public void test1() {
		isort = new InsertionSort<String>((s1,s2) -> s2.compareTo(s1));
		array = new String[]{"aa", "A", "zz","BB"};
		isort.sort(array);
		assertEquals("zz", array[0]);
		assertEquals("aa", array[1]);
		assertEquals("BB", array[2]);
		assertEquals("A", array[3]);
	}
	
	public void test2() {
		isort = new InsertionSort<String>(String.CASE_INSENSITIVE_ORDER);
		array = new String[]{"aa", "A", "zz","BB"};
		isort.sort(array);
		assertEquals("A", array[0]);
		assertEquals("aa", array[1]);
		assertEquals("BB", array[2]);
		assertEquals("zz", array[3]);
	}
	
	public void test3() {
		isort = new InsertionSort<String>((s1,s2) -> 0);
		array = new String[]{"aa", "A", "zz","BB"};
		isort.sort(array);
		assertEquals("aa", array[0]);
		assertEquals("A", array[1]);
		assertEquals("zz", array[2]);
		assertEquals("BB", array[3]);
	}
	
	/// some efficiency tests:
	
	private static int POWER = 20;
	private static final int MAX = 1 << POWER;
	
	public void test8() {
		isort = new InsertionSort<String>((s1,s2) -> 0);
		array = new String[MAX];
		for (int i=0; i < MAX; ++i) {
			array[i] = ""+i;
		}
		isort.sort(array);
		for (int i=0; i < MAX; ++i) {
			assertEquals(""+i, array[i]);
		}
	}
	
	public void test9() {
		isort = new InsertionSort<String>(String.CASE_INSENSITIVE_ORDER);
		array = new String[MAX+1];
		for (int i=0; i < MAX; ++i) {
			array[i] = "" + (1_000_000_000+i);
		}
		array[MAX] = "0";
		isort.sort(array);
		assertEquals("0", array[0]);
		for (int i=0; i < MAX; ++i) {
			assertEquals(""+(1_000_000_000+i), array[i+1]);
		}
	}
}
