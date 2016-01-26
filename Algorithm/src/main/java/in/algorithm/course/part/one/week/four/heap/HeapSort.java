package in.algorithm.course.part.one.week.four.heap;

public class HeapSort {

    public static void sort(final Comparable[] array) {
        BinaryHeap<Comparable> heap = new BinaryHeapZeroIndexedImpl(array);

        for (int i = 0; i < array.length; i++) {
            heap.insert(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            heap.deleteMax();
        }

    }
}
