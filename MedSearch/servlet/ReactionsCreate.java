package servlet;

import model.*;
import dal.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/usercreate")
public class ReactionsCreate extends HttpServlet {
	
	protected ReactionsDao reactionsDao;
	
	@Override
	public void init() throws ServletException {
		reactionsDao = ReactionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int reactionId = req.getParameter("reactionid");
        if (reactionId <= 0) {
            messages.put("success", "Invalid ReactionId");
        } else {
        	// Create the Reaction.
        	Drugs drugIdA = req.getParameter("drugida");
        	Drugs drugIdB = req.getParameter("drugidb");
        	String drugName = req.getParameter("drugName");
        	String description = req.getParameter("description");
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Reactions reaction = new Reactions(reactionId, drugIdA, drugIdB, drugName, description);
	        	reaction = reactionsDao.create(reaction);
	        	messages.put("success", "Successfully created " + reactionId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }