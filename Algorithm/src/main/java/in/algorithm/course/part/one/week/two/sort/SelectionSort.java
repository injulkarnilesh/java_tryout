package in.algorithm.course.part.one.week.two.sort;

public class SelectionSort {
    public static void sort(final Comparable [] arrayToSort) {

        for (int i = 0; i < arrayToSort.length - 1; i++) {

            int min = i;
            for (int j = i + 1 ; j < arrayToSort.length; j++) {
                if (arrayToSort[j].compareTo(arrayToSort[min]) < 0) {
                    min = j;
                }
            }

            swap(arrayToSort, i, min);
        }

    }

    private static void swap(final Comparable[] array, final int i, final int j) {
        final Comparable toSwap = array[i];
        array[i] = array[j];
        array[j] = toSwap;
    }
}
