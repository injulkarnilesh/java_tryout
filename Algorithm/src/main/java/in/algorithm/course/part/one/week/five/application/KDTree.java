package in.algorithm.course.part.one.week.five.application;

public interface KDTree {

    void insert(Point point);

    Point get(Point point);

    boolean isEmpty();

    class WrongDimensionPointException extends RuntimeException {

    }
}
