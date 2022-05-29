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
	public void createHeapAndCheckMax() {
		filereader.createHeap();

		//System.out.println(filereader.getMaxOfSection('A'));
		assertEquals((int)filereader.getMaxOfSection('A').marks, 94);
	}



	//Write testcase for checking max score of 2 sections

	
}
