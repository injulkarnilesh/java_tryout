package in.algorithm.course.part.one.week.three;

import in.algorithm.course.util.Check;
import in.algorithm.course.util.RandomInt;
import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class QuickSortTest {

    @Test
    public void shouldPartitionArray() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer[] arrayToPartition = getRandomArrayOfSize(size);

        final int partitionedIndex = QuickSort.partition(arrayToPartition, 0, arrayToPartition.length - 1);

        verifyElementsAfterPartitionAreLarger(arrayToPartition, partitionedIndex);
        verifyElementsBeforePartitionAreSmaller(arrayToPartition, partitionedIndex);
    }

    private Integer[] getRandomArrayOfSize(final int size) {
        final Integer [] array = new Integer[size];
        for(int i = 0; i < array.length; i++) {
            array[i] = RandomInt.next();
        }
        return array;
    }

    private void verifyElementsAfterPartitionAreLarger(final Integer[] array, final int index) {
        for (int i = index+1; i < array.length; i++) {
            Assert.assertTrue(array[index].compareTo(array[i]) <= 0);
        }
    }

    private void verifyElementsBeforePartitionAreSmaller(final Integer[] array, final int index) {
        for (int i = index - 1; i >= 0; i--) {
            Assert.assertTrue(array[index].compareTo(array[i]) >= 0);
        }
    }

    @Test
    public void shouldSortArrayOfRandomIntegers() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer[] arrayToSort = getRandomArrayOfSize(size);

        QuickSort.sort(arrayToSort);

        assertTrue(Check.isSorted(arrayToSort));
    }

    @Test
    public void shouldSortArrayOfKnownStrings() {
        final String [] arrayToSort = new String[] {
                "N", "I", "L", "E", "S", "H"
        };

        QuickSort.sort(arrayToSort);

        assertTrue(Check.isSorted(arrayToSort));
    }

}
