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
		System.err.println("Testing Null Key:");
		testMap.put(null, TEST_VAL);
	}

	@Test
	public void testKeys_nonEmptyMap() {
		System.err.println("Testing Nonempty Map");
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
		System.err.println("Testing putTemplate");
		testMap.put(TEST_KEY, TEST_VAL);
	}

	/////////////////// MY TESTS START HERE ////////////////////////////////
	
	
	
	//test works but throws exception in autograder
	@Test
	public void testGetEmpty() {
		System.err.println("Testing GetEmpty");
		assertNull(testMap.get(TEST_KEY));
	}
	
	
	//test works but throws exception in autograder?????
	@Test
	public void testPutSize() {
		System.err.println("Testing PutSize");
		testMap.put(TEST_KEY, TEST_VAL);
		assertEquals(1,testMap.size());
	}
	
	
	//test passes but throws exception in autograder
	@Test
	public void testPutDuplicate() {
		System.err.println("Testing PutDuplicate");
		testMap.put(TEST_KEY, TEST_VAL);
		assertFalse(testMap.put(TEST_KEY, TEST_VAL));
		// System.err.println("FAILURE ");
		// System.err.println(testMap.keys());

	}

	
	//test passes but throws exception inautograder
	@Test
	public void testPutGetNull() {
		System.err.println("Testing PutGetNull");
		testMap.put(TEST_KEY, null);
		assertNull(testMap.get(TEST_KEY));
	}
	
	
	/////// contains key tests ///////////
	
	
	//throws exception in autograder
	@Test
	public void testHasKey() {
		System.err.println("Testing HasKey");
		testMap.put(TEST_KEY, TEST_VAL);
		assertTrue(testMap.containsKey(TEST_KEY));
	}
	
	
	//throws exception in autograder
	@Test
	public void testNoKey() {
		System.err.println("Testing NoKey");
		assertFalse(testMap.containsKey(TEST_KEY));
	}
	
	
	@Test
	public void testReHashWithCap() {
		System.err.println("Testing ReHashWIthCap");
		mapWithCap.put(TEST_KEY +1, TEST_VAL);
		mapWithCap.put(TEST_KEY +2, TEST_VAL);
		mapWithCap.put(TEST_KEY +3, TEST_VAL);
		
		//should rehash here and not crash
		mapWithCap.put(TEST_KEY +4, TEST_VAL);
		mapWithCap.put(TEST_KEY +5, TEST_VAL);
		assertEquals(5, mapWithCap.size());
	}
	
	

}