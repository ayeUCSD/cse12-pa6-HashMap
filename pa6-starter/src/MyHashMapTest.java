import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.*;

public class MyHashMapTest {

	private DefaultMap<String, String> testMap; // use this for basic tests
	private DefaultMap<String, String> mapWithCap; // use for testing proper rehashing
	public static final String TEST_KEY = "Test Key";
	public static final String TEST_VAL = "Test Value";

	@Before
	public void setUp() {
		testMap = new MyHashMap<>();
		mapWithCap = new MyHashMap<>(4, MyHashMap.DEFAULT_LOAD_FACTOR);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPut_nullKey() {
		System.out.println("Testing Null Key:");
		testMap.put(null, TEST_VAL);
	}

	@Test
	public void testKeys_nonEmptyMap() {
		System.out.println("Testing Nonempty Map");
		// You don't have to use array list
		// This test will work with any object that implements List
		List<String> expectedKeys = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			// key + i is used to differentiate keys since they must be unique
			testMap.put(TEST_KEY + i, TEST_VAL + i);
			expectedKeys.add(TEST_KEY + i);
		}
		List<String> resultKeys = testMap.keys();
		// we need to sort because hash map doesn't guarantee ordering
		Collections.sort(resultKeys);
		assertEquals(expectedKeys, resultKeys);
	}

	/* Add more of your tests below */

	@Test
	public void testPutTemplate() {
		System.out.println("Testing putTemplate");
		testMap.put(TEST_KEY, TEST_VAL);
	}

	/////////////////// MY TESTS START HERE ////////////////////////////////
	
	
	
	//test works but fails autograder
	@Test
	public void testGetEmpty() {
		System.out.println("Testing GetEmpty");
		assertNull(testMap.get(TEST_KEY));
	}
	
	
	//test works but fails autograder?????
	@Test
	public void testPutSize() {
		System.out.println("Testing PutSize");
		testMap.put(TEST_KEY, TEST_VAL);
		assertEquals(1,testMap.size());
	}
	
	
	//test passes but fails autograder
	@Test
	public void testPutDuplicate() {
		System.out.println("Testing PutDuplicate");
		testMap.put(TEST_KEY, TEST_VAL);
		assertFalse(testMap.put(TEST_KEY, TEST_VAL));
		// System.out.println("FAILURE ");
		// System.out.println(testMap.keys());

	}

	
	//test passes but fails autograder
	@Test
	public void testPutGetNull() {
		System.out.println("Testing PutGetNull");
		testMap.put(TEST_KEY, null);
		assertNull(testMap.get(TEST_KEY));
	}
	
	
	/////// contains key tests ///////////
	
	
	//fails autograder
	@Test
	public void testHasKey() {
		System.out.println("Testing HasKey");
		testMap.put(TEST_KEY, TEST_VAL);
		assertTrue(testMap.containsKey(TEST_KEY));
	}
	
	
	//fails in autograder
	@Test
	public void testNoKey() {
		System.out.println("Testing NoKey");
		assertFalse(testMap.containsKey(TEST_KEY));
	}
	
	

}