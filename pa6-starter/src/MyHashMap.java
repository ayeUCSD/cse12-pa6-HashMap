import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MyHashMap<K, V> implements DefaultMap<K, V> {
	public static final double DEFAULT_LOAD_FACTOR = 0.75;
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	public static final String ILLEGAL_ARG_CAPACITY = "Initial Capacity must be non-negative";
	public static final String ILLEGAL_ARG_LOAD_FACTOR = "Load Factor must be positive";
	public static final String ILLEGAL_ARG_NULL_KEY = "Keys must be non-null";

	private double loadFactor;
	private int capacity;
	private int size; 

	// Use this instance variable for Separate Chaining conflict resolution
	private List<HashMapEntry<K, V>>[] buckets;

	// Use this instance variable for Linear Probing FUCK LINEAR PROBING ALL MY
	// HOMIES HATE LINEAR PROBING

	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
		this.capacity = DEFAULT_INITIAL_CAPACITY;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
	}

	/**
	 * 
	 * @param initialCapacity the initial capacity of this MyHashMap
	 * @param loadFactor      the load factor for rehashing this MyHashMap
	 * @throws IllegalArgumentException if initialCapacity is negative or loadFactor
	 *                                  not positive
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, double loadFactor) throws IllegalArgumentException {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARG_CAPACITY);
		} else {
			this.capacity = initialCapacity;
		}

		if (loadFactor <= 0) {
			throw new IllegalArgumentException(ILLEGAL_ARG_LOAD_FACTOR);
		} else {
			this.loadFactor = loadFactor;
		}

		buckets = (List<HashMapEntry<K, V>>[]) new List<?>[capacity];
		for (int i = 0; i < capacity; i++) {
			buckets[i] = new ArrayList<>();
		}
	}
	
	/**
	 * Adds the specified key, value pair to this DefaultMap
	 * Note: duplicate keys are not allowed
	 * 
	 * @return true if the key value pair was added to this DefaultMap
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {

		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}

		// check if we have space
		if (((size + 1.0) / capacity) > loadFactor) {
			//System.out.println("SIZE " + size + 1 + " / CAPACITY " + capacity + " IS " + size+1 /capacity);
			expandRehash();
		}

		// prepwork: get index, make the entry
		// can also use key.hashCode() assuming key is not null
		int index = hashKey(key);
		HashMapEntry<K, V> entry = new HashMapEntry<>(key, value);

		// check for duplicate keys
		if(containsKey(key)) {
			return false;
		}

		// put the item inside!
		buckets[index].add(entry);
		size++;
		return true;
	}
	
	
	/**
	 * Replaces the value that maps to the key if it is present
	 * @param key The key whose mapped value is being replaced
	 * @param newValue The value to replace the existing value with
	 * @return true if the key was in this DefaultMap
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}

		// go to index, search list for object, and replace value instead of making
		// a new object
		// prep work: get index
		// can also use key.hashCode() assuming key is not null
		int index = hashKey(key);

		// search for key 
		for (int i = 0; i < buckets[index].size(); i++) {
			if(buckets[index].get(i).getKey().equals(key)) {
				buckets[index].get(i).value = newValue;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remove the entry corresponding to the given key
	 * 
	 * @return true if an entry for the given key was removed
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		// KeyCheck
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		
		
		// prep work: get index
		int index = hashKey(key);

		// search for key 
		for (int i = 0; i < buckets[index].size(); i++) {
			if(buckets[index].get(i).getKey().equals(key)) {
				buckets[index].remove(i);
				size--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds the key, value pair to this DefaultMap if it is not present,
	 * otherwise, replaces the value with the given value
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		//ALTERNATE LAZY METHOD: CALL ADD AND REPLACE IF ADD RETURNS FALSE
		
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		
		int index = hashKey(key);

		// search for key, and terminate if you found it 
		for (int i = 0; i < buckets[index].size(); i++) {
			if(buckets[index].get(i).getKey().equals(key)) {
				buckets[index].get(i).setValue(value);
				return;
			}
		}
		// you get here if you didn't find it, so just add it lol
		buckets[index].add(new HashMapEntry<K, V>(key, value));
		size++;
	}
	
	
	/**
	 * @return the value corresponding to the specified key, null if key doesn't 
	 * exist in hash map
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		
		int index = hashKey(key);

		// search for key, and terminate if you found it 
		for (int i = 0; i < buckets[index].size(); i++) {
			if(buckets[index].get(i).getKey().equals(key)) {
				return buckets[index].get(i).value;
			}
		}
		//if nothing was there return null!
		return null;
	}

	
	/**
	 * 
	 * @return The number of (key, value) pairs in this DefaultMap
	 */
	@Override
	public int size() {
		return size;
	}

	
	
	/**
	 * 
	 * @return true iff this.size() == 0 is true
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	
	/**
	 * @return true if the specified key is in this DefaultMap
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		
		int index = hashKey(key);

		// search for key, and terminate if you found it 
		for (int i = 0; i < buckets[index].size(); i++) {
			if(buckets[index].get(i).getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * 
	 * @return an array containing the keys of this DefaultMap. If this DefaultMap is 
	 * empty, returns array of length zero. 
	 */
	@Override
	public List<K> keys() {
		ArrayList<K> output = new ArrayList<>(size);
		for(List<HashMapEntry<K,V>> l:buckets) {
			for(HashMapEntry<K,V> e : l) {
				output.add(e.getKey());
			}
		}
		Collections.sort((List)output);
		return output;
	}

	private int hashKey(K key) {
		//System.out.println("Key: " + key + "| Hash: " + key.hashCode() + "| Capacity: " + capacity + "| mod: " + key.hashCode() % capacity);
		return Math.abs(key.hashCode() % capacity);
	}

	/**
	 * this method should double the size of our list, and rehash all entries
	 */
	private void expandRehash() {
		// double our capacity
		capacity = capacity * 2;
		List<HashMapEntry<K, V>>[] newBuckets = (List<HashMapEntry<K, V>>[]) new List<?>[capacity];
		for (int i = 0; i < capacity; i++) {
			newBuckets[i] = new ArrayList<>();
		}

		for (List<HashMapEntry<K, V>> l : buckets) {
			for (HashMapEntry<K, V> entry : l) {
				int newHash = hashKey(entry.getKey());
				newBuckets[newHash].add(entry);
			}
		}
	}

	private static class HashMapEntry<K, V> implements DefaultMap.Entry<K, V> {

		K key;
		V value;

		private HashMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public void setValue(V value) {
			this.value = value;
		}
	}
}
