<%-- 
    Document   : home
    Created on : Jun 9, 2021, 11:45:10 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <!--WELCOME-->
        <c:if test="${sessionScope.LOGIN_USER != null}">
            <h2 style="color:black">Welcome: ${sessionScope.LOGIN_USER.fullname}</h2>
        </c:if>
            
        <!--LOGOUT-->    
        <a href="LogoutController">Logout</a>
        
        <!--SEARCH USER BY NAME-->
        <form action="MainController">
            <input type="text" name="search"/>
            <input type="submit" name="action" value="Search"/>
        </form>
            
        <!--GET ALL USER-->    
        <form action="GetAllUserController">
            <table border="1">

                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Photo</th>
                    </tr>
                </thead>

                <c:forEach items="${requestScope.LIST}" var="t">
                    <tbody>
                        <tr>
                            <td>${t.userID}</td>
                            <td>${t.fullname}</td>
                            <td>${t.email}</td>
                            <td>${t.phone}</td>
                            <td>
                                <a href="#"><img src="${t.photo}" width="300" height="100"></a>
                            </td>
                        </tr>
                    </tbody>  
                </c:forEach>

            </table>

        </form>        
    </body>
</html>
