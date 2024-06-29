import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateTodo")
public class UpdateTodo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int slno = Integer.parseInt(request.getParameter("slno"));
        TodoDao todoDao = new TodoDao();
        ToDo todo = todoDao.retrieveSingleData(slno);

        // Display update form
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Update Todo</title><link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\r\n"
        		+ "</head>");
        out.println("<body><div>");
        out.println("<h2>Update Todo</h2>");
        out.println("<form action='UpdateTodoAction' method='post'>");
        out.println("<input type='hidden' name='slno' value='" + slno + "'>");
        out.println("<input type='text' name='newTitle' value='" + todo.getTitle() + "' placeholder='New Title' required>");
        out.println("<input type='checkbox' name='status' " + (todo.isStatus() ? "checked" : "") + "> Completed");
        out.println("<button type='submit'>Update</button>");
        out.println("</form></div>");
        out.println("</body>");
        out.println("</html>");
    }
}
