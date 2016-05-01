package in.algorithm.course.part.one.week.six;

class Node<K, V> {
    private final K key;
    private V value;
    private Node<K, V> next;

    private Node(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Node<K, V> createNew(final K key, final V value) {
        return new Node<>(key, value);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(final V value) {
        this.value = value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(final Node<K, V> next) {
        this.next = next;
    }
}