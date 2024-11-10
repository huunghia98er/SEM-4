package org.sem4.first_lesson.dao.impl;

import org.sem4.first_lesson.common.DatabaseManager;
import org.sem4.first_lesson.dao.Repository;
import org.sem4.first_lesson.entity.Classroom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/10
 */

class ClassroomRepository implements Repository<Classroom, Long> {

    private static final Logger logger = Logger.getLogger(ClassroomRepository.class.getName());

    private DatabaseManager factory;

    private ClassroomRepository() {
        try {
            factory = DatabaseManager.getInstance();
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    public static ClassroomRepository getInstance() {
        return ClassroomRepositoryHolder.INSTANCE;
    }

    @Override
    public List<Classroom> getAll() {
        String sql = "SELECT * FROM classrooms";

        try {
            ResultSet rs = factory.getPreparedStatement(sql).executeQuery(sql);

            List<Classroom> classrooms = new ArrayList<>();

            while (rs.next()) {
                Classroom classroom = new Classroom();
                classroom.setId(rs.getLong("id"));
                classroom.setName(rs.getString("name"));
                classrooms.add(classroom);
            }

            return classrooms;
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }

        return List.of();
    }

    @Override
    public boolean create(Classroom classroom) {
        String sql = "INSERT INTO classrooms (name) VALUES (?)";

        PreparedStatement statement = factory.getPreparedStatement(sql);

        try {
            statement.setString(1, classroom.getName());

            statement.execute();

            return true;
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Classroom classroom) {
        String sql = "UPDATE classrooms SET name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = factory.getPreparedStatement(sql)) {
            preparedStatement.setString(1, classroom.getName());
            preparedStatement.setLong(2, classroom.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.warning("Error updating classroom: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM classrooms WHERE id = ?";

        try (PreparedStatement preparedStatement = factory.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.warning("Error deleting classroom: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Classroom findById(Long aLong) {
        return null;
    }

    private static class ClassroomRepositoryHolder {
        private static final ClassroomRepository INSTANCE = new ClassroomRepository();
    }
}
