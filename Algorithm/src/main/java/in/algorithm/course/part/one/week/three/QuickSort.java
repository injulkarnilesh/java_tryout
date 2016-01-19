package in.algorithm.course.part.one.week.three;

import in.algorithm.course.util.ArrayUtils;

public class QuickSort {

    public static void sort(final Comparable[] arrayToSort) {
        sortRecursively(arrayToSort, 0, arrayToSort.length-1);
    }

    private static void sortRecursively(final Comparable[] array, final int low, final int high) {
        if(low >= high)
            return;
        final int partitionIndex = partition(array, low, high);
        sortRecursively(array, low, partitionIndex-1);
        sortRecursively(array, partitionIndex+1, high);
    }

    public static int partition(final Comparable[] arrayToPartition, final int low, final int high) {
        int i = low+1;
        int j = high;


        while (i <= j) {
            while (arrayToPartition[i].compareTo(arrayToPartition[low]) <= 0) {
                i++;
                if(i >= high)
                    break;
            }

            while(arrayToPartition[j].compareTo(arrayToPartition[low]) >= 0) {
                j--;
                if(j <= low)
                    break;
            }

            if( i < j) {
                ArrayUtils.swap(arrayToPartition, i, j);
            }
        }

        ArrayUtils.swap(arrayToPartition, j, low);
        return j;
    }
}
