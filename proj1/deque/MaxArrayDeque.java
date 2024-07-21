package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmptor;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmptor = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (T x : this) {
            if (cmptor.compare(x, maxItem) > 0) {
                maxItem = x;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (T x : this) {
            if (c.compare(x, maxItem) > 0) {
                maxItem = x;
            }
        }
        return maxItem;
    }
}
