package org.sem4.first_lesson.dao.impl;

import org.sem4.first_lesson.dao.Repository;
import org.sem4.first_lesson.entity.Classroom;
import org.sem4.first_lesson.entity.Student;
import org.sem4.first_lesson.entity.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/10
 */

@SuppressWarnings("unchecked")
public class RepositoryFactory {
    private static final Map<String, Repository<?, ?>> repositoryMap = new HashMap<>();

    private RepositoryFactory() {
    }

    static {
        repositoryMap.put(Student.class.getName(), StudentRepository.getInstance());
        repositoryMap.put(Classroom.class.getName(), ClassroomRepository.getInstance());
        repositoryMap.put(Subject.class.getName(), SubjectRepository.getInstance());
    }

    public static <T, K> Repository<T, K> getRepository(Class<T> clazz) {
        Repository<T, K> repository = (Repository<T, K>) repositoryMap.get(clazz.getName());

        if (repository == null) {
            throw new IllegalArgumentException("Unsupported repository type: " + clazz.getName());
        }

        return repository;
    }
}
