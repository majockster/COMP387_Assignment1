<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import ="assignment.team._387a2.helperObjects.CookieHelper" %>
<%@ page import="java.util.Map, assignment.team._387a2.helperObjects.JSPHelper" %>

<%
    String adminType = "admin";

    Map<String, Cookie> cookiesAdminCheck = CookieHelper.ConvertRequestCookies(request);

    if (cookiesAdminCheck.get("personID") == null)
    {
%>
Could not retrieve user cookie. </body></html>
<%
        JSPHelper.KillProgram();
    }

    if (cookiesAdminCheck.get("userType") == null)
    {
%>
Could not retrieve user's type cookie. </body></html>
<%
        JSPHelper.KillProgram();
    }

    if (!cookiesAdminCheck.get("userType").getValue().equals(adminType))
    {
%>
You are not an admin, and thus cannot view this page. Sorry! </body></html>
<%
        JSPHelper.KillProgram();
    }
%>