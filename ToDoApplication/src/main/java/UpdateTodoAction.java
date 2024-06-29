import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateTodoAction")
public class UpdateTodoAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int slno = Integer.parseInt(request.getParameter("slno"));
        String newTitle = request.getParameter("newTitle");
        boolean status = request.getParameter("status") != null; 
        
        TodoDao todoDao = new TodoDao();
        todoDao.updateData(slno, new ToDo(newTitle, status));
        
      
        response.sendRedirect("ListTodoform");
    }
}
