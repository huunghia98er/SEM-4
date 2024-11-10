<%@ page import="org.sem4.first_lesson.entity.Classroom" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: HuuNghia
  Date: 07-Nov-24
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1>Create student</h1>
    <form method="post" action="">
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Email address</label>
            <input type="email" name="email" class="form-control" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label class="form-label">Address</label>
            <textarea name="address" class="form-control"></textarea>
        </div>
        <div class="mb-3">
            <label class="form-label">Telephone</label>
            <input type="text" name="telephone" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Class</label>
            <select name="class_id" class="form-control">
                <% for (Classroom c : (List<Classroom>) request.getAttribute("classes")) { %>
                <option value="<%= c.getId()%>"><%= c.getName() %>
                </option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
