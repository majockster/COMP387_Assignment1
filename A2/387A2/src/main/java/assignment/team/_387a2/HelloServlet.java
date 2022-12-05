package assignment.team._387a2;

import assignment.team._387a2.domainObjects.Person;
import assignment.team._387a2.tableGateways.PersonTableGateway;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PersonTableGateway table = new PersonTableGateway();

        Person person = table.findById(1);
        message += person.getFirstName() + person.getLastName();
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy()
    {
    }
}