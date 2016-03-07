package in.algorithm.course.part.one.week.five;

import java.util.List;

public interface SearchTree<T extends Comparable> {

    void insert(T item);

    List<T> values();

    T search(T item);
}
