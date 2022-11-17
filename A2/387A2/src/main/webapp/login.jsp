<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "assignment.team._387a2.helperObjects.SQLConnection" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="assignment.team._387a2.helperObjects.CookieHelper" %>
<%@ page import="assignment.team._387a2.tableGateways.CourseTableGateway" %>
<%@ page import="assignment.team._387a2.tableGateways.StudentTableGateway" %>
<%@ page import="assignment.team._387a2.rowGateways.StudentGateway" %>
<%@ page import="assignment.team._387a2.rowGateways.CourseGateway" %>
<%@ page import="static java.lang.Integer.parseInt" %>
<%@ page import="assignment.team._387a2.tableGateways.PersonTableGateway" %>
<%@ page import="assignment.team._387a2.rowGateways.PersonGateway" %>
<%@ page import="assignment.team._387a2.tableGateways.AdministratorTableGateway" %>
<%@ page import="assignment.team._387a2.rowGateways.AdministratorGateway" %>


<%
	javax.servlet.jsp.JspWriter _jspx_out = null;
	_jspx_out = out;
	

	String email = request.getParameter("email");
	String password = request.getParameter("password");

	PersonTableGateway ptg = new PersonTableGateway();
	PersonGateway person = ptg.findByEmail(email);
	if (person != null)
	{

		if (person.getPassword() == password)
		{
			String pid = String.valueOf(person.getPersonId());
			String fname = person.getFirstName();
			String lname = person.getLastName();

			Map<String, Cookie> cookies = CookieHelper.ConvertRequestCookies(request);
			cookies.put("personID", new Cookie("personID", pid));
			cookies.put("firstName", new Cookie("firstName", fname));
			cookies.put("lastName", new Cookie("lastName", lname));

			AdministratorTableGateway atg = new AdministratorTableGateway();
			AdministratorGateway admin = atg.findByPersonId(Integer.parseInt(pid));
			if (admin == null) {
				cookies.put("userType", new Cookie("userType", "student"));
				response.sendRedirect("/student");
			}
			else
			{
				cookies.put("userType", new Cookie("userType", "admin"));
				response.sendRedirect("/admin");
			}
		}
		else
		{
			out.print("<h2>Invalid email or password</h2>");
		}
	}
	else
	{
		out.print("<h2>Invalid email or password</h2>");
	}

%>



