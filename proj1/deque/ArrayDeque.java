package deque;

public class ArrayDeque<T> {
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

    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast += 1;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = nextFirst + 1;
        while (i < items.length && items[i] != null) {
            System.out.print(items[i] + " ");
            i += 1;
        }

        int j = 0;
        while (j < nextFirst + 1 && items[j] != null) {
            System.out.print(items[j] + " ");
            i += 1;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T first = items[nextFirst + 1];
        nextFirst += 1;
        //items[nextFirst + 1] = null;
        size -= 1;
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T last = items[nextLast - 1];
        nextLast -= 1;
        //items[nextLast - 1] = null;
        size -= 1;
        return last;
    }

    public T get(int index) {
        int realIndex = index + nextFirst + 1;
        if (realIndex < items.length) {
            return items[realIndex];
        } else {
            return items[realIndex - items.length];
        }
    }
}