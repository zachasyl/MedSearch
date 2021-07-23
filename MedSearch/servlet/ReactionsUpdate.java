package servlet;

import model.*;
import dal.*;

import java.io.IOException;
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


@WebServlet("/reactionsupdate")
public class ReactionsUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        int reactionId= req.getParameter("ReactionId");
        if (reactionId < 0) {
            messages.put("success", "Please enter a valid reactionId");
        } else {
        	try {
        		Reactions reaction = reactionsDao.getReactionById(reactionId);
        		if(reactionId == 0) {
        			messages.put("success", "ReactionId does not exist.");
        		}
        		req.setAttribute("reaction", reaction);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        int reactionId = req.getParameter("reactionid");
        if (reactionId < 0) {
            messages.put("success", "Please enter a valid ReactionId.");
        } else {
        	try {
        		Reactions reaction = reactionsDao.getReactionById(reactionId);
        		if(reaction == null) {
        			messages.put("success", "ReactionId does not exist. No update to perform.");
        		} else {
        			String newDescription = req.getParameter("description");
        			if (newDescription == null || newDescription.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Description.");
        	        } else {
        	        	reaction = reactionsDao.updateDescription(reaction, newDescription);
        	        	messages.put("success", "Successfully updated " + reactionId);
        	        }
        		}
        		req.setAttribute("reaction", reaction);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
}