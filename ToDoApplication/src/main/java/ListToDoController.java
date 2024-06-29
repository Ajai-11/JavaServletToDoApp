

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListToDo")
public class ListToDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String title = request.getParameter("title");
	        User user = (User) request.getSession().getAttribute("user");

	        if (user != null) {
	            ToDo t = new ToDo(title);
	            TodoDao td = new TodoDao();
	            td.storeData(t, user.getId());
	            request.setAttribute("tl", title);
	        }

	        RequestDispatcher rd = request.getRequestDispatcher("success.html");
	        rd.forward(request, response);
	        doGet(request, response);

	}

}
