package bstmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

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

    private List<BSTNode> nodesInOrder(BSTNode node) {
        List<BSTNode> keys = new ArrayList<>();
        if (node.left != null) {
            keys.addAll(nodesInOrder(node.left));
        }
        keys.add(node);
        if (node.right != null) {
            keys.addAll(nodesInOrder(node.right));
        }
        return keys;
    }

    public void printInOrder() {
        for (BSTNode node : nodesInOrder(root)) {
            System.out.print(node.key.toString() + ' ');
        }
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

    private BSTNode parent(BSTNode curNode, BSTNode tarNode) {
        if (tarNode == root || root == null) {
            return null;
        }
        if (curNode.left == tarNode || curNode.right == tarNode ) {
            return curNode;
        }
        if (curNode.key.compareTo(tarNode.key) > 0) {
            return parent(curNode.left, tarNode);
        } else {
            return parent(curNode.right, tarNode);
        }
    }

    private BSTNode findPredecessor(BSTNode curNode) {
        curNode = curNode.left;
        while (curNode.right != null) {
            curNode = curNode.right;
        }
        return curNode;
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

    @Override
    public Set<K> keySet() {
        if (size == 0) {
            return null;
        }
        List<K> keys = new ArrayList<>();
        for (BSTNode node : nodesInOrder(root)) {
            keys.add(node.key);
        }
        return Set.copyOf(keys);
    }

    @Override
    public V remove(K key) {
        BSTNode delNode = find(root, key);
        if (delNode == null) {
            return null;
        } else {
            size--;
            BSTNode delParent = parent(root, delNode);

            if (delParent == null) {
                if (delNode.left == null && delNode.right == null ) {
                    clear();
                } else if (delNode.left == null) {
                    root = delNode.right;
                } else if (delNode.right == null) {
                    root = delNode.left;
                } else {
                    BSTNode predecessor = findPredecessor(delNode);
                    remove(predecessor.key);
                    delNode.key = predecessor.key;
                    delNode.val = predecessor.val;
                }
            } else {

                if (delNode.left == null && delNode.right == null ) {
                    if (delParent.left == delNode) {
                        delParent.left = null;
                    } else {
                        delParent.right = null;
                    }
                } else if (delNode.left == null) {
                    if (delParent.left == delNode) {
                        delParent.left = delNode.right;
                    } else {
                        delParent.right = delNode.right;
                    }
                } else if (delNode.right == null) {
                    if (delParent.left == delNode) {
                        delParent.left = delNode.left;
                    } else {
                        delParent.right = delNode.left;
                    }
                } else {
                    BSTNode predecessor = findPredecessor(delNode);
                    remove(predecessor.key);
                    delNode.key = predecessor.key;
                    delNode.val = predecessor.val;
                }
            }
            return delNode.val;
        }
    }

    @Override
    public V remove(K key, V value) {
        BSTNode delNode = find(root, key);
        if (delNode.val == value) {
            return remove(key);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
