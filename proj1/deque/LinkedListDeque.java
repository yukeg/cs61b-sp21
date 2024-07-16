package deque;

public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node n) {
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public int size() {
        return size;
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node curFirst = sentinel.next;
        curFirst.prev = new Node(item, curFirst);
        sentinel.next = curFirst.prev;
        curFirst.prev.prev = sentinel;
        size += 1;
    }

    public void addLast(T item) {
        Node curLast = sentinel.prev;
        curLast.next = new Node(item, sentinel);
        sentinel.prev = curLast.next;
        // curLast.next.next = sentinel;
        curLast.next.prev = curLast;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void printDeque() {
        Node cur = sentinel.next;
        while (cur.item != null) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node curFirst = sentinel.next;
        T curFirstVal = curFirst.item;
        sentinel.next = curFirst.next;
        curFirst.next.prev = sentinel;
        size -= 1;

        return curFirstVal;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node curLast = sentinel.prev;
        T curLastVal = curLast.item;
        sentinel.prev = curLast.prev;
        curLast.prev.next = sentinel;
        size -= 1;

        return curLastVal;
    }

    public T get(int index) {
        Node cur = sentinel.next;
        int count = 0;
        while (cur.item != null) {
            if (index == count) {
                return cur.item;
            }
            cur = cur.next;
            count += 1;
        }
        return null;
    }

    private T getRecHelper(int index, int count, Node cur) {
        if (cur.item == null) {
            return null;
        }
        if (index == count) {
            return cur.item;
        }
        return getRecHelper(index, count + 1, cur.next);
    }

    public T getRecursive(int index) {
        Node cur = sentinel.next;
        int count = 0;
        return getRecHelper(index, count, cur);
    }
}