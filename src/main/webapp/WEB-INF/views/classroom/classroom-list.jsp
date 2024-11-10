<%@ page import="org.sem4.first_lesson.entity.Classroom" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: HuuNghia
  Date: 10-Nov-24
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
    <h1>List Classroom</h1>
    <a href="create-classroom">Create a new Classroom</a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
        </tr>
        </thead>
        <tbody>
        <% for (Classroom c : (List<Classroom>) request.getAttribute("classrooms")) { %>
        <tr>
            <th scope="row"><%= c.getId() %>
            </th>
            <td><%= c.getName() %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
