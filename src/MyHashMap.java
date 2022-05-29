import java.util.List;
import java.util.Objects;

import java.util.ArrayList;
import java.util.Comparator;

public class MyHashMap<K, V> implements DefaultMap<K, V> {

    public static final int DEFAULT_INITIAL_CAPACITY = 10;
    public static final String ILLEGAL_ARG_CAPACITY = 
                                        "Initial Capacity must be non-negative";
    public static final String ILLEGAL_ARG_NULL_KEY = "Keys must be non-null";

    private double loadFactor;
    private int capacity;
    private int size;	
    private Comparator myComparator;
    private Character[] sections;

    // Use this instance variable for Separate Chaining conflict resolution
    //private List<HashMapEntry<K, V>>[] buckets;  
    private List<MaxHeap<K, V>> buckets = new ArrayList<MaxHeap<K, V>>(DEFAULT_INITIAL_CAPACITY);

    // Use this instance variable for Linear Probing
    //	private HashMapEntry<K, V>[] entries; 

    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, null);}

    @SuppressWarnings("unchecked")
        public MyHashMap(int initialCapacity, Comparator myComparator)
            throws IllegalArgumentException {
            //constrcutor for the hashMap

            this.myComparator = myComparator;

            this.capacity = initialCapacity;

            sections = new Character[initialCapacity];

            size = 0;

            buckets = new ArrayList<MaxHeap<K, V>>(initialCapacity);

            for (int i = 0; i<initialCapacity; i++) {
                buckets.add(null);
            }

        }

    @Override
        public boolean put(K key, V value) throws IllegalArgumentException {
           //Method to add the key value pair to the hashMap
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null.");
                }

            // this decides what section it is (key = section, value = student obj)
            int keyHash = Math.abs(Objects.hashCode(key)); 
            int index = keyHash % capacity;


            // System.out.println(buckets.get(index));
            // if the section here is empty, make a new maxheap and add the value
            // try {
                // buckets.get(index);
                if(buckets.get(index) == null) {
                    //System.out.println("AYOOOO");
                    //System.out.println("WE'RE HERE!");
                    MaxHeap newTree = new MaxHeap(50, myComparator);
                    newTree.add(value,key);
                    buckets.add(index, newTree);
                    size++;
                    return true;
                } 

            // } catch (Exception IndexOutOfBoundsException) {
            //     if(buckets.get(index) == null) {
            //         System.out.println("AYOOOO");
            //         //System.out.println("WE'RE HERE!");
            //         MaxHeap newTree = new MaxHeap(50, myComparator);
            //         newTree.add(value,key);
            //         buckets.add(index, newTree);
            //         size++;
            //         return true;
            //     } 
            // }

            // if its not empty, check if the student already exists here and if so, dont add them
            // if they dont exist here, add them
            // else {
            MaxHeap aTree = buckets.get(index);
            MaxHeap aTreeCopy = new MaxHeap<V, K>(50, myComparator);

            HeapEntry<V,K> highestScorer = aTree.peek();

            while(aTree.heapSize != 0) {
                highestScorer = aTree.remove();
                aTreeCopy.add(highestScorer.key, highestScorer.value);

                if(highestScorer.key.equals(value)) {
                    return false;
                }
                

            }
            // student doesnt exist, can add
            aTreeCopy.add(value, key);
            buckets.set(index, aTreeCopy);
            size++;
            return true;

            // }
        }

    @Override
        public V get(K key) throws IllegalArgumentException {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null.");
            }

            int keyHash = Math.abs(Objects.hashCode(key)); 
            int index = keyHash % capacity;

            // stop giving me error :(
            
            // try {
            //     buckets.get(index); 
            // }
            // catch (Exception IndexOutOfBoundsException) {
            //     return null;
            // }

            HeapEntry<V,K> newHeapEntry = (HeapEntry<V, K>) buckets.get(index).peek();
            return newHeapEntry.getKey();
        }

    @Override
        public boolean containsKey(K key) throws IllegalArgumentException {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null.");
            }

            int keyHash = Math.abs(Objects.hashCode(key)); 
            int index = keyHash % capacity;

            return buckets.get(index) != null;
        }

    @Override
        public int size() {
            //Method to get size of the hashMap
            return size;
        }

    @Override
        public boolean isEmpty() {
            //Method to check if hashMap is empty
            return buckets.isEmpty();
        }

    protected static class HashMapEntry<K, V> implements DefaultMap.Entry<K, V> {

        K key;
        V value;

        public HashMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
            public K getKey() {
                return this.key;
            }

        @Override
            public V getValue() {
                return this.value;
            }

        @Override
            public void setValue(V value) {
                this.value = value;
            }

        @Override
            @SuppressWarnings("unchecked")
            public boolean equals(Object o) {
                if (o instanceof MyHashMap.HashMapEntry<?, ?>) {
                    HashMapEntry<K, V> other = null;
                    try {
                        other = (HashMapEntry<K, V>) o;
                    } catch (ClassCastException e) {
                        return false;
                    }

                    return Objects.equals(key, other.key);
                }


                return false;
            }

    }
}