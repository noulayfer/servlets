<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<h1>All Users</h1>
<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
    </tr>
        <tr>
    <c:forEach items="${users}" var="user">
            <td>${user.userId}</td>
            <td>${user.getFirstName()}</td>
            <td>${user.getLastName()}</td>
            <td>${user.getAge()}</td>
            <td>
                <form action="tomcat/delete-user" method="post">
                    <input type="hidden" name="userId" value="${user.getUserId()}">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <form action="tomcat/action-on-user" method="post">
    <label for="userId">User ID:</label>
        <input type="text" name="userId" id="userId">
        <br>
        <label for="firstName">Parameter 1:</label>
        <input type="text" name="firstName" id="firstName">
        <br>
        <label for="lastName">Parameter 2:</label>
        <input type="text" name="lastName" id="lastName">
        <br>
        <label for="age">Parameter 3:</label>
        <input type="text" name="age" id="age">
        <br>
        <button type="submit" name="action" value="Set">Set</button>
        <button type="submit" name="action" value="Create">Create</button>
    </form>
</table>
</body>
</html>
