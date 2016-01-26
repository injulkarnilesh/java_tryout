package in.algorithm.course.part.one.week.four.heap;

import in.algorithm.course.part.one.week.four.priorityqueue.PriorityQueue;

public class BinaryHeapMaxPriorityQueue<T extends Comparable> implements PriorityQueue<T> {

    private BinaryHeap<T> heap;

    public BinaryHeapMaxPriorityQueue(final int capacity) {
        heap = new BinaryHeapImpl(capacity);
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void enqueue(final T item) {
        heap.insert(item);
    }

    @Override
    public T dequeue() {
        return heap.deleteMax();
    }

    @Override
    public int size() {
        return heap.size();
    }
}
