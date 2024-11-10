package org.sem4.first_lesson.controller.student;

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
import org.sem4.first_lesson.entity.Student;

import java.io.IOException;
import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/10
 */

@WebServlet(value = "/create-student")
public class CreateStudentController extends HttpServlet {
    private transient Repository<Student, Long> studentRepository;
    private transient Repository<Classroom, Long> classroomRepository;

    @Override
    public void init() {
        studentRepository = RepositoryFactory.getRepository(Student.class);
        classroomRepository = RepositoryFactory.getRepository(Classroom.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Classroom> classes = classroomRepository.getAll();
        req.setAttribute("classes", classes);
        RequestDispatcher dispatcher = req.getRequestDispatcher(JspPath.STUDENT_JSP + "create-student.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student s = new Student(
                req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("address"),
                req.getParameter("telephone"),
                Long.parseLong(req.getParameter("class_id"))
        );

        if (studentRepository.create(s)) {
            resp.sendRedirect("students");
            return;
        }

        resp.sendRedirect("create-student");
    }
}
