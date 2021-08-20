<%-- 
    Document   : admin_page
    Created on : Jun 10, 2021, 9:11:20 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>

        <h2>CREATE NEW USER</h2>
        <form action="MainController">
            User ID<input type="text" name="userID"/><br/>
            Password<input type="password" name="password"/><br/>
            Full name<input type="text" name="fullname"/><br/>
            Email<input type="text" name="email"/><br/>
            Phone<input type="text" name="phone"/><br/>
            Photo<input type="file" name="photo"/> <br/>
            <input type="submit" name="action" value="Create"/><br/>
        </form>

        <h2>DELETE USER</h2>
        <form action="MainController">
            Input User ID to Delete<input type="text" name="delete"/>
            <input type="submit" name="action" value="Delete">
        </form>

        <h2>UPDATE USER</h2>
        <form action="MainController">
            Input User ID to Update<input type="text" name="userID"/>
            <input type="submit" name="action" value="Find"/>
        </form>
        <form action="MainController">
            User ID<input type="text" name="userID" value="${sessionScope.user.userID}"/><br/>
            Password<input type="password" name="password" value="${sessionScope.user.password}"/><br/>
            Role ID<input type="text" name="roleID" value="${sessionScope.user.roleID}"/><br/>
            Full name<input type="text" name="fullname" value="${sessionScope.user.fullname}"/><br/>
            Email<input type="text" name="email" value="${sessionScope.user.email}"/><br/>
            Phone<input type="text" name="phone" value="${sessionScope.user.phone}"/><br/>
            Photo<input type="file" name="photo" value="${sessionScope.user.photo}"/> <br/>
            <input type="submit" name="action" value="Update"/>
        </form>
        <br/>
        <a href="GetAllUserController">Go to Home</a><br/>    
        <a href="admin_page.jsp">Go to Admin Page</a>
    </body>

</html>
