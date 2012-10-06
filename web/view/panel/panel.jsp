<%-- 
    Document   : panel
    Created on : Aug 15, 2012, 10:39:22 PM
    Author     : jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <a href="<%= request.getContextPath() %>/user/panel">User</a><br>
        <a href="<%= request.getContextPath() %>/service/panel">Service</a>
    </body>
</html>

