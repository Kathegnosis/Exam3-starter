import static org.junit.Assert.*;

import org.junit.*;
public class CircularArrayListTest {

	
	@Test
	public void test_baseCase() throws Exception {

		ArrayListADT<Integer> cal = new CircularArrayList<Integer> (10);
		//Complete testcase to check elements at few positions
		cal.addFront(2);
		cal.addFront(6);
		cal.addRear(0);
		
		assertEquals(0, cal.getRear());
		
		assertEquals(6, cal.getFront());
		
	}
	
	
	@Test
	public void testRemoval() throws Exception {
		ArrayListADT<Integer> cal = new CircularArrayList<Integer> (10);
		//Complete testcase to check elements at few positions
		cal.addFront(2);
		cal.addFront(6);
		cal.addRear(0);
		
		cal.remove();
		
		assertEquals(2, cal.getFront());
		
	}
	
	@Test
	public void emptyStuff() throws Exception {
		ArrayListADT<Integer> cal = new CircularArrayList<Integer> (10);
		//Complete testcase to check elements at few positions
		
		cal.remove();
		
		assertEquals(null, cal.getFront());
		
	}
	
	@Test
	public void unexpectedErrorWhy() {
		ArrayListADT<Integer> cal = new CircularArrayList<Integer> (10);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		cal.addFront(2);
		assertEquals(cal.getSize(), 13);
		
		assertEquals(cal.getFront(), 2);
		
		assertEquals((int)cal.remove(), 2);
		
	}
	
	@Test
	public void unexpectedErrorPt2() {
		ArrayListADT<Integer> cal1 = new CircularArrayList<Integer> (10);
		cal1.addRear(2);
		assertEquals(cal1.getSize(), 1);
		
		assertEquals(cal1.getFront(), 2);
		
		assertEquals((int)cal1.remove(), 2);
		
	}
	
	
}
