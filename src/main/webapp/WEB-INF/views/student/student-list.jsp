<%@ page import="java.util.List" %>
<%@ page import="org.sem4.first_lesson.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: HuuNghia
  Date: 07-Nov-24
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Listing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1>List Student</h1>
    <a href="create-student">Create a new student</a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Address</th>
            <th scope="col">Telephone</th>
        </tr>
        </thead>
        <tbody>
        <% for (Student s : (List<Student>) request.getAttribute("students")) { %>
        <tr>
            <th scope="row"><%= s.getId() %>
            </th>
            <td><%= s.getName() %>
            </td>
            <td><%= s.getEmail() %>
            </td>
            <td><%= s.getAddress() %>
            </td>
            <td><%= s.getPhone() %>
            </td>
        </tr>
        <% } %>


        </tbody>
    </table>
</div>
</body>
</html>