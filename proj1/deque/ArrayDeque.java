package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
//        System.arraycopy(items, realIndex(0), a, 0, items.length - realIndex(0));
//        if (size > items.length - realIndex(0)) {
//            System.arraycopy(items, 0, a, items.length - realIndex(0),
//            size - (items.length - realIndex(0)));
//        }
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = a;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    private int realIndex(int index) {
        int realIndex = index + nextFirst + 1;
        if (realIndex < items.length) {
            return realIndex;
        } else {
            return realIndex - items.length;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (T x : this) {
            System.out.print(x.toString() + " ");
        }
        System.out.println("");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if (4 * size < items.length && size >= 17) {
            resize(items.length / 2);
        }

        int realIndex = realIndex(0);
        T first = items[realIndex];
        nextFirst = realIndex;
        //items[nextFirst + 1] = null;
        size -= 1;
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (4 * size < items.length && size >= 17) {
            resize(items.length / 2);
        }

        int realIndex = realIndex(size - 1);
        T last = items[realIndex];
        nextLast = realIndex;
        //items[nextLast - 1] = null;
        size -= 1;
        return last;
    }

    @Override
    public T get(int index) {
        int realIndex = realIndex(index);
        return items[realIndex];
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        private ArrayDequeIterator() {
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
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Deque) {
            Deque<T> otherDeque = (Deque<T>) other;
            if (this.size != otherDeque.size()) {
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
