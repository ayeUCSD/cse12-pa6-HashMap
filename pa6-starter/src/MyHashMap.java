import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
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
		// if you use Separate Chaining
		buckets = (List<HashMapEntry<K, V>>[]) new List<?>[capacity];
		for(int i = 0; i < capacity; i++){
			buckets[i] = new LinkedList<>();
		}
	}

	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {

		if (key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}

		// check if we have space
		if ((size + 1.0 / capacity) > loadFactor) {
			expandRehash();
		}

		// prepwork: get index, make the entry
		// can also use key.hashCode() assuming key is not null
		int keyHash = hashKey(key);
		HashMapEntry<K, V> entry = new HashMapEntry<>(key, value);
		buckets[keyHash].add(entry);
		size++;
		return true;
	}

	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public V get(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}



	private int hashKey(K key){
		return key.hashCode() % capacity;
	}

	/**
	 * this method should double the size of our list, and rehash all entries
	 */
	private void expandRehash() {
		//double our capacity
		capacity = capacity * 2;
		List<HashMapEntry<K, V>>[] newBuckets = (List<HashMapEntry<K, V>>[]) new List<?>[capacity];
		for(int i = 0; i < capacity; i++){
			newBuckets[i] = new LinkedList<>();
		}

		for(List<HashMapEntry<K, V>> l : buckets){
			for(HashMapEntry<K,V> entry : l){
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
