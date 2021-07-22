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


@WebServlet("/findprescriptions")
public class FindPrescriptions extends HttpServlet {
	protected PrescriptionsDao prescriptionsDao;
	
	@Override
	public void init() throws ServletException {
		prescriptionsDao = PrescriptionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Prescriptions prescription = null;
		
		String prescriptionId = req.getParameter("prescriptionId");
		
		if (prescriptionId != null && !prescriptionId.trim().isEmpty()) {
			try {
				prescription = prescriptionsDao.getPrescriptionByPrescriptionId(prescriptionId);
				req.setAttribute("success", "Displaying results for " + prescriptionId);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("prescription", prescription);
		req.getRequestDispatcher("/FindPrescriptions.jsp").forward(req, resp);	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Prescriptions prescription = null;
		String prescriptionId = req.getParameter("prescriptionId");
		
		if (prescriptionId != null && !prescriptionId.trim().isEmpty()) {
			try {
				prescription = prescriptionsDao.getPrescriptionByPrescriptionId(prescriptionId);
				req.setAttribute("success", "Displaying results for " + prescriptionId);
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("prescription", prescription);
		req.getRequestDispatcher("/FindPrescriptions.jsp").forward(req, resp);	
	}
}