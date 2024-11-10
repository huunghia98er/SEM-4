package org.sem4.first_lesson.controller.student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sem4.first_lesson.common.JspPath;
import org.sem4.first_lesson.dao.Repository;
import org.sem4.first_lesson.dao.impl.RepositoryFactory;
import org.sem4.first_lesson.entity.Student;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/05
 */

@WebServlet(value = "/students")
public class StudentController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(StudentController.class.getName());

    private transient Repository<Student, Long> repository;

    @Override
    public void init(ServletConfig config) {
        repository = RepositoryFactory.getRepository(Student.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // GET student list
        List<Student> students = repository.getAll();

        req.setAttribute("students", students);

        // return student list
        RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.STUDENT_JSP + "student-list.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.warning("Error when forwarding request to student-list.jsp");
        }
    }

}
