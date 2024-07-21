package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
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

    @Override
    public int size() {
        return size;
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node curFirst = sentinel.next;
        curFirst.prev = new Node(item, curFirst);
        sentinel.next = curFirst.prev;
        curFirst.prev.prev = sentinel;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node curLast = sentinel.prev;
        curLast.next = new Node(item, sentinel);
        sentinel.prev = curLast.next;
        // curLast.next.next = sentinel;
        curLast.next.prev = curLast;
        size += 1;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public void printDeque() {
//        Node cur = sentinel.next;
//        while (cur.item != null) {
//            System.out.print(cur.item + " ");
//            cur = cur.next;
//        }
//        System.out.println("");
        for (T x : this) {
            System.out.print(x.toString() + " ");
        }
        System.out.println("");
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;

        public LinkedListDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof LinkedListDeque) {
            LinkedListDeque<T> otherDeque = (LinkedListDeque<T>) other;
            if (this.size != otherDeque.size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!(otherDeque.get(i).equals(this.get(i)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}