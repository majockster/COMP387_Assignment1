<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "assignment.team._387a2.helperObjects.SQLConnection" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="assignment.team._387a2.helperObjects.CookieHelper" %>



<%
    Cookie[] array = request.getCookies();
    for (int i = 0; i< array.length; i++)
    {
        if (array[i].getName().equals("firstName"))
        {
            array[i].setMaxAge(0);
            response.addCookie(array[i]);
        }
        if (array[i].getName().equals("lastName"))
        {
            array[i].setMaxAge(0);
            response.addCookie(array[i]);
        }
        if (array[i].getName().equals("personID"))
        {
            array[i].setMaxAge(0);
            response.addCookie(array[i]);
        }
        if (array[i].getName().equals("userType"))
        {
            array[i].setMaxAge(0);
            response.addCookie(array[i]);
        }
    }
    response.sendRedirect("login.jsp");
%>




