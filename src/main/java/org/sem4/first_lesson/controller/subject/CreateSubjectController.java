package org.sem4.first_lesson.controller.subject;

import jakarta.servlet.RequestDispatcher;
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

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/10
 */

@WebServlet(value = "/create-subject")
public class CreateSubjectController extends HttpServlet {
    private transient Repository<Subject, Long> subjectRepository;

    @Override
    public void init() {
        subjectRepository = RepositoryFactory.getRepository(Subject.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.SUBJECT_JSP + "create-subject.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject s = new Subject(
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("hours"))
        );

        if (subjectRepository.create(s)) {
            resp.sendRedirect("subjects");
            return;
        }

        resp.sendRedirect("create-subject");
    }
}
