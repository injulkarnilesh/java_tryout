package in.algorithm.course.part.one.week.four.heap;

import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomInt;
import org.junit.Assert;
import org.junit.Test;

public class BinaryHeapTest {

    @Test
    public void shouldHaveParentNoSmallerThanChildrenOnInsert() throws Exception {
        final BinaryHeap<Integer> heap = new BinaryHeapImpl(10);
        int itemFive = 5;
        heap.insert(itemFive);
        Integer[] heapData = heapData(heap);
        //5
        Assert.assertEquals(Integer.valueOf(itemFive), heapData[1]);

        int itemFour = 4;
        heap.insert(itemFour);
        heapData = heapData(heap);
        //5, 4
        Assert.assertEquals(Integer.valueOf(itemFour), heapData[2]);

        int itemSeven = 7;
        heap.insert(itemSeven);
        heapData = heapData(heap);
        //7, 4, 5
        Assert.assertEquals(Integer.valueOf(itemSeven), heapData[1]);
        Assert.assertEquals(Integer.valueOf(itemFour), heapData[2]);
        Assert.assertEquals(Integer.valueOf(itemFive), heapData[3]);

        int itemTen = 10;
        heap.insert(itemTen);
        heapData = heapData(heap);
        //10, 7, 5, 4
        Assert.assertEquals(Integer.valueOf(itemTen), heapData[1]);
        Assert.assertEquals(Integer.valueOf(itemSeven), heapData[2]);
        Assert.assertEquals(Integer.valueOf(itemFive), heapData[3]);
        Assert.assertEquals(Integer.valueOf(itemFour), heapData[4]);
    }

    @Test
    public void shouldHaveLargestElementAtTopAlways() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer[] array = getArrayWithIndexedNumbers(size);

        final BinaryHeap<Integer> heap = new BinaryHeapImpl(size);

        for (int i = 0; i < size; i++) {
            heap.insert(array[i]);
            Assert.assertEquals(i + 1, heapData(heap)[1].intValue());
        }
    }

    private Integer[] getArrayWithIndexedNumbers(final int size) {
        final Integer []  array = new Integer[size];
        for(int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    @Test
    public void shouldHaveMaxElementAtTopOnRemovingMaxFromTop() throws Exception {
        final BinaryHeap<Integer> heap = new BinaryHeapImpl(10);
        int itemFive = 5;
        heap.insert(itemFive);
        int itemFour = 4;
        heap.insert(itemFour);
        int itemSeven = 7;
        heap.insert(itemSeven);
        int itemTen = 10;
        heap.insert(itemTen);
        int itemThree = 3;
        heap.insert(itemThree);
        int itemTwo = 2;
        heap.insert(itemTwo);

        //10, 7, 5, 4, 3, 2
        int max = heap.deleteMax();
        Assert.assertEquals(itemTen, max);

        //7, 4, 5, 2, 3
        Integer[] heapData = heapData(heap);
        Assert.assertEquals(itemSeven, heapData[1].intValue());
        Assert.assertEquals(itemFour, heapData[2].intValue());
        Assert.assertEquals(itemFive, heapData[3].intValue());
        Assert.assertEquals(itemTwo, heapData[4].intValue());
        Assert.assertEquals(itemThree, heapData[5].intValue());

        max = heap.deleteMax();
        Assert.assertEquals(itemSeven, max);

        //5, 4, 3, 2
        heapData = heapData(heap);
        Assert.assertEquals(itemFive, heapData[1].intValue());
        Assert.assertEquals(itemFour, heapData[2].intValue());
        Assert.assertEquals(itemThree, heapData[3].intValue());
        Assert.assertEquals(itemTwo, heapData[4].intValue());
    }

    @Test
    public void shouldMaintainMaxAtTopOnDeleteMax() throws Exception {
        final int size = RandomInt.next(100, 1000);
        final Integer[] array = getArrayWithIndexedNumbers(size);

        KnuthShuffle.shuffle(array);

        final BinaryHeap<Integer> heap = new BinaryHeapImpl(size);

        for (int i = 0; i < size; i++) {
            heap.insert(array[i]);
        }

        for(int i = size; i > 0; i--) {
            Assert.assertEquals(i, heap.deleteMax().intValue());
        }

    }

    private Integer[] heapData(final BinaryHeap<Integer> heap) {
        final Comparable[] array = ((BinaryHeapImpl)heap).getData();
        final Integer[] intArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
            intArray[i] = (Integer)array[i];
        return intArray;
    }


    @Test (expected = BinaryHeap.EmptyHeap.class)
    public void shouldThrowExceptionOnDeleteWhenEmptyHeap() throws Exception {
        final BinaryHeap<String> heap = new BinaryHeapImpl(5);
        Assert.assertTrue(heap.isEmpty());
        heap.insert("WHAT");
        Assert.assertFalse(heap.isEmpty());
        heap.deleteMax();

        heap.deleteMax();
    }

    @Test (expected = BinaryHeap.FullHeap.class)
    public void shouldThrowFullHeapOnInsertingIntoFullHeap() throws Exception {
        final BinaryHeap<String> heap = new BinaryHeapImpl(0);
        heap.insert("WHERE");
    }
}
