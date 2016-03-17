package in.algorithm.course.part.one.week.five.application;

import java.util.Objects;

public class KDTreeImpl implements KDTree {

    private int k;
    private Node root;

    private KDTreeImpl(final int k) {
        this.k = k;
        root = null;
    }

    public static KDTree createNewForDimensionsOf(final int k) {
        return new KDTreeImpl(k);
    }

    @Override
    public void insert(final Point point) {

        if (Objects.isNull(point)) {
            return;
        }

        if (point.getD() != k) {
            throw new WrongDimensionPointException();
        }

        if (isEmpty()) {
            root = Node.createNew(point);
            return;
        }

        insert(root, point, 0);
    }

    private void insert(final Node currentNode, final Point point, final int depth) {
        final Point currentPoint = currentNode.getPoint();
        final int dimensionToUse = dimensionAtDepth(depth);
        if (point.getNthDimension(dimensionToUse) < currentPoint.getNthDimension(dimensionToUse)) {
            if (currentNode.hasLeftChild()) {
                insert(currentNode.getLeft(), point, depth+1);
            } else {
                currentNode.setLeft(Node.createNew(point));
            }
        } else {
            if (currentNode.hasRightChild()) {
                insert(currentNode.getRight(), point, depth+1);
            } else {
                currentNode.setRight(Node.createNew(point));
            }
        }
    }

    private int dimensionAtDepth(final int depth) {
        return depth % k;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(root);
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public Point get(final Point point) {
        if (isEmpty()) {
            return Point.INVALID_POINT;
        }
        return get(root, point, 0);
    }

    private Point get(final Node currentNode, final Point point, final int depth) {
        if (Objects.isNull(currentNode)) {
            return Point.INVALID_POINT;
        }
        if (currentNode.getPoint().equals(point)) {
            return currentNode.getPoint();
        }

        final int currentDimension = dimensionAtDepth(depth);

        if (point.getNthDimension(currentDimension) < currentNode.getPoint().getNthDimension(currentDimension)) {
            return get(currentNode.getLeft(), point, depth+1);
        } else {
            return get(currentNode.getRight(), point, depth+1);
        }

    }

    public static class Node {
        private final Point point;
        private Node left;
        private Node right;

        private Node(final Point point) {
            this.point = point;
        }

        public static Node createNew(final Point point) {
            return new Node(point);
        }

        public Point getPoint() {
            return point;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(final Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(final Node right) {
            this.right = right;
        }

        public boolean hasLeftChild() {
            return Objects.nonNull(this.left);
        }

        public boolean hasRightChild() {
            return Objects.nonNull(this.right);
        }
    }

}
