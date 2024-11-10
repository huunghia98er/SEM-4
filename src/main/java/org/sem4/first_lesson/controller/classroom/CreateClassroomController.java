package org.sem4.first_lesson.controller.classroom;

import jakarta.servlet.RequestDispatcher;
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

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/10
 */

@WebServlet(value = "/create-classroom")
public class CreateClassroomController extends HttpServlet {
    private transient Repository<Classroom, Long> classroomRepository;

    @Override
    public void init() {
        classroomRepository = RepositoryFactory.getRepository(Classroom.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.CLASSROOM_JSP + "create-classroom.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Classroom s = new Classroom(
                req.getParameter("name")
        );

        if (classroomRepository.create(s)) {
            resp.sendRedirect("classrooms");
            return;
        }

        resp.sendRedirect("create-classroom");
    }
}
