import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListTodoform")
public class ListTodoform extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head><title>Simple To Do List</title><link rel=\"stylesheet\" type=\"text/css\" href=\"styles3.css\">"
        		+ "<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0\" /></head>");
        out.println("<body>");
        out.println("<h2>Simple TO DO List</h2>");

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("index.html");
            return;
        }

        out.println("<div><form action='ListToDo' method='post'>");
        out.println("<input type='text' name='title' placeholder='Enter New Todo' required>");
        out.println("<button type='submit'>Create ToDo</button><br><br>");
        out.println("</form></div>");
        
        out.println("<div><table border='0'><tr><th>Title</th><th>Status</th><th>Update</th><th>Delete</th></tr>");
        TodoDao todoDao = new TodoDao();
        ResultSet rs = todoDao.retrieveData(user.getId());
        try {
            while (rs.next()) {
                int slno = rs.getInt("Slno");
                String title = rs.getString("title");
                boolean status = rs.getBoolean("status");
                out.println("<tr><td>" + title + "</td>"
                        + "<td>" + (status ? "Completed" : "Pending") + "</td>"
                        + "<td><form action='UpdateTodo' method='get'><input type='hidden' name='slno' value='" + slno + "'><button type='submit'><span class=\"material-symbols-outlined\">\r\n"
                        		+ "edit\r\n"
                        		+ "</span></button></form></td>"
                        + "<td><form action='ListTodoform' method='post'><input type='hidden' name='action' value='delete'><input type='hidden' name='slno' value='" + slno + "'>"
                        		+ "<button type='submit'><span class=\"material-symbols-outlined\">\r\n"
                        		+ "delete\r\n"
                        		+ "</span></button></form></td></tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</table>");
        
        out.println("<form action='Logout' method='get'>");
        out.println("<button type='submit'>Logout</button>");
        out.println("</form></div>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            TodoDao todoDao = new TodoDao();
            int slno = Integer.parseInt(request.getParameter("slno"));
            if (action.equals("delete")) {
                todoDao.deleteData(slno);
            }
        }
        response.sendRedirect("ListTodoform");
    }
}
