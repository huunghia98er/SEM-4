package org.sem4.first_lesson.dao.impl;

import org.sem4.first_lesson.common.DatabaseManager;
import org.sem4.first_lesson.dao.Repository;
import org.sem4.first_lesson.entity.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/10
 */

class SubjectRepository implements Repository<Subject, Long> {

    private static final Logger logger = Logger.getLogger(SubjectRepository.class.getName());
    private DatabaseManager factory;

    private SubjectRepository() {
        try {
            factory = DatabaseManager.getInstance();
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    public static SubjectRepository getInstance() {
        return SubjectRepositoryHolder.INSTANCE;
    }

    @Override
    public List<Subject> getAll() {
        String sql = "SELECT * FROM subjects";
        List<Subject> subjects = new ArrayList<>();

        try (Statement statement = factory.getStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getLong("id"));
                subject.setName(rs.getString("name"));
                subject.setHours(rs.getInt("hours"));
                subjects.add(subject);
            }

        } catch (SQLException e) {
            logger.warning("Error fetching all subjects: " + e.getMessage());
        }

        return subjects;
    }

    @Override
    public boolean create(Subject subject) {
        String sql = "INSERT INTO subjects (name, hours) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = factory.getPreparedStatement(sql)) {
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getHours());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.warning("Error creating subject: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Subject subject) {
        String sql = "UPDATE subjects SET name = ?, hours = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = factory.getPreparedStatement(sql)) {
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getHours());
            preparedStatement.setLong(3, subject.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.warning("Error updating subject: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM subjects WHERE id = ?";

        try (PreparedStatement preparedStatement = factory.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.warning("Error deleting subject: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Subject findById(Long aLong) {
        return null;
    }

    private static class SubjectRepositoryHolder {
        private static final SubjectRepository INSTANCE = new SubjectRepository();
    }
}

