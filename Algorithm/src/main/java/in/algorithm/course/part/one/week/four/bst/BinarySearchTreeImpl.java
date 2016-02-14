package in.algorithm.course.part.one.week.four.bst;

import java.util.Objects;

public class BinarySearchTreeImpl<T extends Comparable> implements BinarySearchTree<T> {

    private Node<T> root;

    private BinarySearchTreeImpl() {
        root = null;
    }

    public static BinarySearchTree createNew() {
        return new BinarySearchTreeImpl();
    }

    @Override
    public void put(final T item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("NULL can not be put");
        }

        root = put(root, item);

    }

    private Node<T> put(final Node<T> currentNode, final T value) {

        if (Objects.isNull(currentNode)) {
            return Node.createNew(value);
        }

        final int comparison = value.compareTo(currentNode.getValue());
        if (toLeft(comparison)) {
            currentNode.setLeft(put(currentNode.getLeft(), value));
        } else if (toRight(comparison)) {
            currentNode.setRight(put(currentNode.getRight(), value));
        } else {
            currentNode.setValue(value);
        }

        currentNode.reCalculateCount();

        return currentNode;
    }

    private boolean toRight(final int comparison) {
        return comparison > 0;
    }

    private boolean toLeft(final int comparison) {
        return comparison < 0;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(root);
    }

    @Override
    public T get(final T item) {
        if (Objects.isNull(item)) {
            return null;
        }

        final Node<T> match = get(root, item);
        return Objects.isNull(match) ? null : match.getValue();
    }

    private Node<T> get(final Node<T> currentNode, final T value) {
        if (Objects.isNull(currentNode)) {
            return null;
        }
        final int comparison = value.compareTo(currentNode.getValue());
        if (toLeft(comparison)) {
            return get(currentNode.getLeft(), value);
        } else if (toRight(comparison)) {
            return get(currentNode.getRight(), value);
        }

        return currentNode;
    }

    @Override
    public int size() {
        return root == null ? 0 : root.getCount();
    }

    @Override
    public boolean contains(final T item) {
        return Objects.nonNull(get(item));
    }

    @Override
    public void remove(final T item) {
        if (Objects.isNull(item)) {
            return;
        }
        root = remove(root, item);
    }

    private Node<T> remove(final Node<T> currentNode, final T item) {
        if (Objects.isNull(currentNode)) {
            return null;
        }

        final int comparison = item.compareTo(currentNode.getValue());
        if (toLeft(comparison)) {
            currentNode.setLeft(remove(currentNode.getLeft(), item));
        } else if (toRight(comparison)) {
            currentNode.setRight(remove(currentNode.getRight(), item));
        } else {
            if ( hasNoLeftChild(currentNode) && hasNoRightChild(currentNode)) {
                return null;
            }
            else {
                if (hasNoLeftChild(currentNode)) {
                    return currentNode.getRight();
                }
                else if (hasNoRightChild(currentNode)) {
                    return currentNode.getLeft();
                } else {
                    final Node<T> nodeToReplaceCurrentNode = min(currentNode.getRight());
                    currentNode.setRight(
                            remove(currentNode.getRight(), nodeToReplaceCurrentNode.getValue())
                    );
                    currentNode.setValue(nodeToReplaceCurrentNode.getValue());
                }

            }
        }

        currentNode.reCalculateCount();

        return currentNode;
    }

    private boolean hasNoRightChild(final Node<T> currentNode) {
        return Objects.isNull(currentNode.getRight());
    }

    private boolean hasNoLeftChild(final Node<T> currentNode) {
        return Objects.isNull(currentNode.getLeft());
    }

    @Override
    public T min() {
        final Node<T> minNode = min(root);
        return Objects.isNull(minNode) ? null : minNode.getValue();
    }

    private Node<T> min(final Node<T> node) {

        if (Objects.isNull(node)) {
            return null;
        }

        if (hasNoLeftChild(node)) {
            return node;
        }

        return min(node.getLeft());
    }

    @Override
    public T max() {
        final Node<T> maxNode = max(root);
        return Objects.isNull(maxNode) ? null : maxNode.getValue();
    }

    private Node<T> max(final Node<T> node) {

        if (Objects.isNull(node)) {
            return null;
        }

        if (hasNoRightChild(node)) {
            return node;
        }

        return max(node.getRight());

    }

    private final static class Node<V> {

        private V value;
        private Node<V> left;
        private Node<V> right;
        private int count;

        private Node(final V value) {
            this.value = value;
            count = 1;
        }

        public static <V>  Node createNew(final V value) {
            return new Node(value);
        }

        public V getValue() {
            return value;
        }

        public void setValue(final V value) {
            this.value = value;
        }

        public Node<V> getLeft() {
            return left;
        }

        public void setLeft(final Node<V> left) {
            this.left = left;
        }

        public Node<V> getRight() {
            return right;
        }

        public void setRight(final Node<V> right) {
            this.right = right;
        }

        public int getCount() {
            return count;
        }

        public void setCount(final int count) {
            this.count = count;
        }

        public void reCalculateCount() {
            this.setCount(1 + safeCount(this.getLeft()) + safeCount(this.getRight()));
        }

        private int safeCount(final Node<V> node) {
            return Objects.isNull(node) ? 0 : node.getCount();
        }

        @Override
        public String toString() {
            final StringBuilder string = new StringBuilder(128);
            string.append(this.left);
            string.append(" - ");
            string.append("[" + this.value + "] (" + this.count +  ")");
            string.append(" - ");
            string.append(this.right);
            return string.toString();
        }
    }
}
