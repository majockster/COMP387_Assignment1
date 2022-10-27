<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>

<%
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection(
"jdbc:mysql://localhost:3306/soen387a1","root","");
Statement statement=con.createStatement();
ResultSet result=statement.executeQuery(query);
while(rs.next())
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
con.close();
}catch(Exception e){ System.out.println(e);}
}
}


// Connect to MySQL Server
// mysqli_connect( Hostname, username, password)
if (!($conn = mysqli_connect("localhost", "root", ""))) {
    die("Could not connect to database </body>

</html>");
}

// Open database
// mysqli_select_db( connection, database_name
if (!mysqli_select_db($conn, "soen387a1")) {
    die("Could not open SOEN387A1 database </body>

</html>");
}

%>