package org.sem4.first_lesson.controller.subject;

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
import org.sem4.first_lesson.entity.Subject;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/05
 */

@WebServlet(value = "/subjects")
public class SubjectController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SubjectController.class.getName());

    private transient Repository<Subject, Long> repository;

    @Override
    public void init(ServletConfig config) {
        repository = RepositoryFactory.getRepository(Subject.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Subject> subjects = repository.getAll();

        req.setAttribute("subjects", subjects);

        RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.SUBJECT_JSP + "subject-list.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.warning("Error when forwarding request to student-list.jsp");
        }
    }

}
