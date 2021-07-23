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


@WebServlet("/prescriptionsupdate")
public class PrescriptionsUpdate extends HttpServlet {
	
	protected PrescriptionsDao prescriptionsDao;
	
	@Override
	public void init() throws ServletException {
		prescriptionsDao = PrescriptionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        int prescriptionId= req.getParameter("PrescriptionId");
        if (prescriptionId < 0) {
            messages.put("success", "Please enter a valid prescriptionId");
        } else {
        	try {
        		Prescriptions prescription = prescriptionsDao.getPrescriptionByPrescriptionId(prescriptionId);
        		if(prescriptionId == 0) {
        			messages.put("success", "PrescriptionId does not exist.");
        		}
        		req.setAttribute("prescription", prescription);
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
        int prescriptionId = req.getParameter("prescriptionid");
        if (prescriptionId < 0) {
            messages.put("success", "Please enter a valid PrescriptionId.");
        } else {
        	try {
        		Prescriptions prescription = prescriptionsDao.getPrescriptionByPrescriptionId(prescriptionId);
        		if(prescription == null) {
        			messages.put("success", "PrescriptionId does not exist. No update to perform.");
        		} else {
        			Date newFillDate = req.getParameter("fillDate");
        			if (newFillDate == null) {
        	            messages.put("success", "Please enter a valid FillDate.");
        	        } else {
        	        	prescription = prescriptionsDao.updateFillDate(prescription, newFillDate);
        	        	messages.put("success", "Successfully updated " + prescriptionId);
        	        }
        		}
        		req.setAttribute("prescription", prescription);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
}
