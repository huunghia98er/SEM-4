package org.sem4.first_lesson.controller.classroom;

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
import org.sem4.first_lesson.entity.Classroom;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/05
 */

@WebServlet(value = "/classrooms")
public class ClassroomController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ClassroomController.class.getName());

    private transient Repository<Classroom, Long> repository;

    @Override
    public void init(ServletConfig config) {
        repository = RepositoryFactory.getRepository(Classroom.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Classroom> classrooms = repository.getAll();

        req.setAttribute("classrooms", classrooms);

        RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.CLASSROOM_JSP + "classroom-list.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.warning("Error when forwarding request to student-list.jsp");
        }
    }

}
