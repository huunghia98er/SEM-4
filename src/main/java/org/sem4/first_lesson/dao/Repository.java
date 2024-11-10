package org.sem4.first_lesson.dao;

import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/07
 */

public interface Repository<T, K> {
    List<T> getAll();

    boolean create(T t);

    boolean update(T t);

    boolean delete(K k);

    T findById(K k);
}
