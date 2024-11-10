package org.sem4.first_lesson.dao.impl;

import org.sem4.first_lesson.common.DatabaseManager;
import org.sem4.first_lesson.dao.Repository;
import org.sem4.first_lesson.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/07
 */
class StudentRepository implements Repository<Student, Long> {

    private static final Logger logger = Logger.getLogger(StudentRepository.class.getName());

    private DatabaseManager factory;

    private StudentRepository() {
        try {
            factory = DatabaseManager.getInstance();
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    public static StudentRepository getInstance() {
        return StudentRepositoryHolder.INSTANCE;
    }

    @Override
    public List<Student> getAll() {
        String sql = "select * from students";

        ArrayList<Student> arrayList = new ArrayList<>();

        try {
            ResultSet rs = factory.getStatement().executeQuery(sql);

            while (rs.next()) {
                arrayList.add(
                        new Student(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("address"),
                                rs.getString("phone"),
                                rs.getLong("class_id")
                        )
                );
            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }

        return arrayList;
    }

    @Override
    public boolean create(Student student) {
        String sql = "insert into students(name, email, phone, address) value (?,?,?,?)";

        PreparedStatement preparedStatement = factory.getPreparedStatement(sql);

        try {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getAddress());

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = factory.getPreparedStatement(sql)) {
            // Set parameters for the prepared statement
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setLong(5, student.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.warning("Error updating student: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (PreparedStatement preparedStatement = factory.getPreparedStatement(sql)) {
            preparedStatement.setLong(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.warning("Error deleting student: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Student findById(Long aLong) {
        return null;
    }

    private static class StudentRepositoryHolder {
        private static final StudentRepository INSTANCE = new StudentRepository();
    }
}
