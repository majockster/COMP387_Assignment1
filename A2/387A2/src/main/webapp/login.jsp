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


<%

	String email = request.getParameter("email");
	String password = request.getParameter("password");
	Connection connection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/soen387a1","root","");

	PreparedStatement ps = connection.prepareStatement("select * from person where email = ?");
	ps.setString(1, email);
	ResultSet rs = ps.executeQuery();
	if (rs.next())
	{
		if (password == rs.getString("password"))
		{
			String pid = rs.getString("personID");
			String fname = rs.getString("firstName");
			String lname = rs.getString("lastName");

			// Load cookies
			Map<String, Cookie> cookies = CookieHelper.ConvertRequestCookies(request);
			cookies.put("personID", new Cookie("personID", pid));
			cookies.put("firstName", new Cookie("firstName", fname));
			cookies.put("lastName", new Cookie("lastName", lname));

			PreparedStatement ps2 = connection.prepareStatement("select * from administrator where personID = ?");
			int pID = Integer.parseInt(pid);
			ps2.setInt(1, pID);
			ResultSet rs2 = ps2.executeQuery();
			if (rs2.next())
			{
				cookies.put("userType", new Cookie("userType", "admin"));
				response.sendRedirect("/admin");
			}
			else
			{
				cookies.put("userType", new Cookie("userType", "student"));
				response.sendRedirect("/student");
			}
		}
		else
		{
			//out.print("<h2>Invalid email or password</h2>");
		}
	}
	else
	{
		//out.print("<h2>Invalid email or password</h2>");
	}




%>



