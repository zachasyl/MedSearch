package servlet;

import model.*;
import dal.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findinteractions")
public class FindReactions extends HttpServlet {
	protected ReactionsDao reactionsDao;
	
	@Override
	public void init() throws ServletException {
		reactionsDao = ReactionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Reactions> reactions = new ArrayList<Reactions>();
		String drugNameA = req.getParameter("drugNameA");
		String drugNameB = req.getParameter("drugNameB");
		
		boolean existDrugNameA = drugNameA != null && !drugNameA.trim().isEmpty();
		boolean existDrugNameB = drugNameB != null && !drugNameB.trim().isEmpty();
		
		if (existDrugNameA && existDrugNameB) {
			try {
				// this Reaction instance contains the DrugName of drugNameB
				reactions = reactionsDao.getReactionsBetweenDrugNames(drugNameA, drugNameB);
				req.setAttribute("success", "Displaying results for " + drugNameA +
								 " and " + drugNameB);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("reactions", reactions);
		// store the drug name that is not stored in reactions
		req.setAttribute("drugNameA", drugNameA);
		req.getRequestDispatcher("/FindReactions.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		List<Reactions> reactions = new ArrayList<Reactions>();
		String drugNameA = req.getParameter("drugNameA");
		String drugNameB = req.getParameter("drugNameB");
		DrugsDao drugsDao = DrugsDao.getInstance();
		
		boolean existDrugNameA = drugNameA != null && !drugNameA.trim().isEmpty();
		boolean existDrugNameB = drugNameB != null && !drugNameB.trim().isEmpty();
		
		if (existDrugNameA && existDrugNameB) {
			try {
				// this Reaction instance contains the DrugName of drugNameB
				reactions = reactionsDao.getReactionsBetweenDrugNames(drugNameA, drugNameB);
				req.setAttribute("success", "Displaying results for " + drugNameA +
						 		 " and " + drugNameB);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("reactions", reactions);
		// store the drug name that is not stored in reactions
		req.setAttribute("drugNameA", drugNameA);
		req.getRequestDispatcher("/FindReactions.jsp").forward(req, resp);
	}
	
	
	
	
	
	
	
}