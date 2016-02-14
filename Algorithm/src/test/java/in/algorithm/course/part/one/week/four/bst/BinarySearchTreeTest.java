package in.algorithm.course.part.one.week.four.bst;

import in.algorithm.course.part.one.week.two.sort.application.KnuthShuffle;
import in.algorithm.course.util.RandomInt;
import in.algorithm.course.util.RandomString;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    @Before
    public void setUp() {
        bst = BinarySearchTreeImpl.createNew();
    }


    @Test
    public void shouldPutAndGetValues() throws Exception {
        final int size = RandomInt.next(10, 100);
        final Integer[] values = integerArrayOfNumbersTill(size);

        KnuthShuffle.shuffle(values);

        putValuesIntoBst(values);

        for (int i = 0; i < values.length; i++) {
            assertEquals(Integer.valueOf(i), bst.get(i));
        }

        assertNull(bst.get(RandomInt.next(100, 1000)));

    }

    private Integer[] integerArrayOfNumbersTill(final int size) {
        final Integer[] values = new Integer[size];
        for (int i =0; i < size; i++) {
            values[i] = i;
        }
        return values;
    }

    private void putValuesIntoBst(final Integer[] values) {
        for (int i = 0; i < values.length; i++) {
            bst.put(values[i]);
        }
    }

    @Test
    public void shouldUpdateElementOnPut() throws Exception {
        final BinarySearchTree<KeyValuePair> anotherBst = BinarySearchTreeImpl.createNew();
        final int size = RandomInt.next(10, 20);

        final KeyValuePair[] keyValues = new KeyValuePair[size];
        for (int i = 0; i < keyValues.length; i++) {
            keyValues[i] = KeyValuePair.createNew(RandomInt.next(i*100, (i+1)*100), RandomString.next());
            anotherBst.put(keyValues[i]);
        }

        for (int i = 0; i < keyValues.length; i++) {
            final String oldValue = keyValues[i].value;
            assertEquals(oldValue, anotherBst.get(keyValues[i]).value);

            final String newValue = RandomString.next();
            final KeyValuePair newPair = KeyValuePair.createNew(keyValues[i].key, newValue);
            anotherBst.put(newPair);
            assertEquals(newValue, anotherBst.get(newPair).value);
        }

    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotAcceptNullValue() throws Exception {
        bst.put(null);
    }

    @Test
    public void shouldGetSize() throws Exception {
        final int size = RandomInt.next(100, 200);
        for (int i = 0; i < size; i++) {
            bst.put(i);
            assertEquals(i+1, bst.size());
        }

    }

    @Test
    public void shouldCheckIfEmpty() throws Exception {
        assertTrue(bst.isEmpty());
        bst.put(RandomInt.next());
        assertFalse(bst.isEmpty());
    }

    @Test
    public void shouldCheckIfContains() throws Exception {
        assertFalse(bst.contains(RandomInt.next()));

        final int size = RandomInt.next(10, 100);
        for (int i = 0; i < size; i++) {
            final int value = RandomInt.next(1000);
            bst.put(value);
            assertTrue(bst.contains(value));
        }

        assertFalse(bst.contains(RandomInt.next(size, 1000)));
    }

    @Test
    public void shouldRemoveExistingElementWithNoChildNodes() throws Exception {
        bst.put(1);
        bst.put(0);
        bst.put(2);

        assertEquals(3, bst.size());

        bst.remove(0);

        assertEquals(2, bst.size());
        assertFalse(bst.contains(0));
    }

    @Test
    public void shouldRemoveRootNodeWhenOnlyElement() throws Exception {
        final int value = RandomInt.next();
        bst.put(value);
        assertEquals(1, bst.size());

        bst.remove(value);
        assertEquals(0, bst.size());
        assertTrue(bst.isEmpty());
        assertFalse(bst.contains(value));
    }

    @Test
    public void shouldRemoveElementWithOneChild() throws Exception {
        bst.put(20);
        bst.put(10);
        bst.put(5);
        bst.put(30);
        bst.put(40);

        assertEquals(5, bst.size());

        bst.remove(10);
        assertEquals(4, bst.size());
        assertFalse(bst.contains(10));

        bst.remove(30);
        assertEquals(3, bst.size());
        assertFalse(bst.contains(30));
    }

    @Test
    public void shouldRemoveElementWithTwoChildren() throws Exception {
        bst.put(20);

        bst.put(10);
        bst.put(5);
        bst.put(15);
        bst.put(1);
        bst.put(7);
        bst.put(11);
        bst.put(17);

        bst.put(30);
        bst.put(25);
        bst.put(35);

        assertEquals(11, bst.size());

        bst.remove(5);
        assertEquals(10, bst.size());
        assertFalse(bst.contains(5));

        bst.remove(10);
        assertEquals(9, bst.size());
        assertFalse(bst.contains(10));

        bst.remove(30);
        assertEquals(8, bst.size());
        assertFalse(bst.contains(30));
    }

    @Test
    public void shouldRemove() throws Exception {
        final int size = RandomInt.next(10, 200);
        final Integer[] values = integerArrayOfNumbersTill(size);

        KnuthShuffle.shuffle(values);

        putValuesIntoBst(values);

        assertEquals(size, bst.size());

        for (int i = 0; i < values.length; i++) {
            bst.remove(i);
            assertEquals(size-i-1, bst.size());
            assertFalse(bst.contains(i));
        }
    }


    @Test
    public void shouldGetMinElement() throws Exception {
        final Integer min = RandomInt.next(10, 30);
        final int size = RandomInt.next(20, 100);

        final Integer [] inputs = new Integer[size];
        for (int i = 0; i < inputs.length-1; i++) {
            inputs[i] = RandomInt.next(min+1, 1000);
        }
        inputs[size-1] = min;

        KnuthShuffle.shuffle(inputs);

        putValuesIntoBst(inputs);

        assertEquals(min, bst.min());

    }

    @Test
    public void shouldGetNullForMinIfEmpty() throws Exception {
        assertTrue(bst.isEmpty());
        assertNull(bst.min());
    }

    @Test
    public void shouldGetNullForMaxIfEmpty() throws Exception {
        assertTrue(bst.isEmpty());
        assertNull(bst.max());
    }

    @Test
    public void shouldGetMaxElement() throws Exception {
        final int size = RandomInt.next(10, 300);
        final Integer values[] = integerArrayOfNumbersTill(size);

        KnuthShuffle.shuffle(values);

        putValuesIntoBst(values);

        assertEquals(Integer.valueOf(size-1), bst.max());

    }

    private static class KeyValuePair implements Comparable<KeyValuePair> {
        private Integer key;
        private String value;

        private KeyValuePair(final Integer key, final String value) {
            this.key = key;
            this.value = value;
        }

        public static KeyValuePair createNew(final Integer key, final String value) {
            return new KeyValuePair(key, value);
        }

        @Override
        public int compareTo(final KeyValuePair obj) {
            return this.key.compareTo(obj.key);
        }

        @Override
        public boolean equals(final Object obj) {
            return key.equals(obj);
        }
    }
}
