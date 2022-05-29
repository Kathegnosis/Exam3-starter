import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.*;

public class MyHashMapTest {
	
	private FileReader filereader;
	private DefaultMap<Integer, Student> testMap; // use this for basic tests

	@Before
	public void setUp() {
		filereader = new FileReader("src\\input.txt");
	}

	@Test
	public void createHeapAndCheckMaxA() {
		filereader.createHeap();

		//System.out.println(filereader.getMaxOfSection('A'));
		assertEquals((int)filereader.getMaxOfSection('A').marks, 94);
	}
	
	@Test
	public void createHeapAndCheckMaxB() {
		filereader.createHeap();

		//System.out.println(filereader.getMaxOfSection('A'));
		assertEquals((int)filereader.getMaxOfSection('B').marks, 88);
	}
	
	@Test
	public void returnNullMax() {
		filereader.createHeap();

		//System.out.println(filereader.getMaxOfSection('A'));
		assertEquals(filereader.getMaxOfSection('P'), null);
	}



	//Write testcase for checking max score of 2 sections

	
}
