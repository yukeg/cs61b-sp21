package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private int length;
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        size = 16;
        loadFactor = 0.75;
        length = 0;
        buckets = createTable(size);
    }

    public MyHashMap(int initialSize) {
        size = initialSize;
        loadFactor = 0.75;
        length = 0;
        buckets = createTable(size);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        size = initialSize;
        loadFactor = maxLoad;
        length = 0;
        buckets = createTable(size);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        size = 16;
        length = 0;
        buckets = createTable(size);
    }

    private Node find(K sk) {
        int bucketInd = getBucketInd(sk);
        Collection<Node> sBucket = buckets[bucketInd];
        if (sBucket == null) {
            return null;
        }
        for (Node node : sBucket) {
            if (node.key.equals(sk)) {
                return node;
            }
        }
        return null;
    }

    private int getBucketInd(K key) {
        return Math.floorMod(key.hashCode(), size);
    }

    @Override
    public boolean containsKey(K key) {
        if (find(key) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        Node node = find(key);
        if (node == null) {
            return null;
        } else {
            return node.value;
        }
    }

    @Override
    public int size() {
        return length;
    }

    private void resize() {

    }

    @Override
    public void put(K key, V value) {

        if (containsKey(key)) {
            find(key).value = value;
            return;
        }

        Node newNode = createNode(key, value);
        int bucketInd = getBucketInd(key);
        if (buckets[bucketInd] == null) {
            buckets[bucketInd] = createBucket();
        }
        buckets[bucketInd].add(newNode);
        length++;

        if ((double) length / size >= loadFactor) {
            resize();
        }
    }

    @Override
    public Set<K> keySet() {
        if (length == 0) {
            return null;
        }
        Set<K> keys = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                keys.add(node.key);
            }
        }
        return keys;
    }

    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Operation not supported");
    }
}
