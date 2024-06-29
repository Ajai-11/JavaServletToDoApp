// Loginuser.java
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Loginuser")
public class Loginuser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        System.out.println("Email: " + email);
        System.out.println("Password: " + pass);

        UserDao userDao = new UserDao();
        User user = userDao.validateUser(email, pass);

        if (user != null) {
            request.getSession().setAttribute("user", user);
            System.out.println("User found: " + user.getName());
            response.sendRedirect("ListTodoform");
        } else {
            System.out.println("User not found or invalid credentials");
            response.sendRedirect("index.html");
        }
    }
}
