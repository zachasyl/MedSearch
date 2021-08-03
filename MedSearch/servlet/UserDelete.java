package servlet;
import dal.*;
import model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userdelete")
public class UserDelete extends HttpServlet {
	protected UsersDao usersDao;
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete User");        
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("fail", "Invalid Username");
            messages.put("disableSubmit", "false");
        } else {
	        try {
	        	// Check if the user exists
		        Users user = usersDao.getUserByUserName(userName);
		        if (user == null) {
		        	messages.put("fail", "The username does not exist.");
		        } else {
		            // Delete the user
		        	user = usersDao.delete(user);
			        if (user == null) {
			            messages.put("success", "Successfully deleted " + userName);
			            messages.put("disableSubmit", "true");
			        } else {
			        	messages.put("fail", "Failed to delete " + userName);
			        	messages.put("disableSubmit", "false");
			        }	
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
    }
}