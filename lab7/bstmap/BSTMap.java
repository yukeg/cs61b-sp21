package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode {

        private K key;
        private V val;
        private BSTNode left;
        private BSTNode right;

        private BSTNode(K key, V value) {
            this.key = key;
            this.val = value;
            this.left = null;
            this.right = null;
        }

    }
    private BSTNode root;
    private int size;

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K sk) {
        if (find(root, sk) == null) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        if (find(root, key) == null) {
            return null;
        }
        return find(root, key).val;
    }

    @Override
    public void put(K key, V value) {
        root = insert(root, key, value);
    }

    private BSTNode insert(BSTNode node, K ik, V iv) {
        if (node == null) {
            size++;
            return new BSTNode(ik, iv);
        }
        if (ik.compareTo(node.key) < 0) {
            node.left = insert(node.left, ik, iv);
        } else if (ik.compareTo(node.key) > 0) {
            node.right = insert(node.right, ik, iv);
        } else {
            // If the key already exists, update the value
            node.val = iv;
        }
        return node;
    }

    private BSTNode find(BSTNode node, K sk) {
        if (node == null) {
            return null;
        }
        if (sk.equals(node.key)) {
            return node;
        } else if (sk.compareTo(node.key) < 0) {
            return find(node.left, sk);
        } else {
            return find(node.right, sk);
        }
    }

    public void printInOrder() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Operation not supported");
    }
}
