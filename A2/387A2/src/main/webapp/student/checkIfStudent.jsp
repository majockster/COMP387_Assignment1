<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import ="assignment.team._387a2.helperObjects.CookieHelper" %>
<%@ page import="java.util.Map, assignment.team._387a2.helperObjects.JSPHelper" %>

<%
    String studentType = "student";

    Map<String, Cookie> cookiesStudentCheck = CookieHelper.ConvertRequestCookies(request);

    if (cookiesStudentCheck.get("personID") == null)
    {
        %>
            Could not retrieve user cookie. </body></html>
        <%
        JSPHelper.KillProgram();
    }

    if (cookiesStudentCheck.get("userType") == null)
    {
        %>
            Could not retrieve user's type cookie. </body></html>
        <%
        JSPHelper.KillProgram();
    }

    if (!cookiesStudentCheck.get("userType").getValue().equals(studentType))
    {
        %>
            You are not a student, and thus cannot view this page. Sorry! </body></html>
        <%
        JSPHelper.KillProgram();
    }
%>