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


@WebServlet("/findcustomer")
public class FindCustomer extends HttpServlet {
	protected CustomersDao customerDao;
	
	@Override
	public void init() throws ServletException {
		customerDao = CustomersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Customers customer = null;
		
		String username = req.getParameter("username");
		
		if (username != null && !username.trim().isEmpty()) {
			try {
				customer = customerDao.getCustomerFromUserName(username);
				req.setAttribute("success", "Displaying results for " + username);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("customer", customer);
		req.getRequestDispatcher("/FindCustomer.jsp").forward(req, resp);	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Customers customer = null;
		String username = req.getParameter("username");
		
		if (username != null && !username.trim().isEmpty()) {
			try {
				customer = customerDao.getCustomerFromUserName(username);
				req.setAttribute("success", "Displaying results for " + username);
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("customer", customer);
		req.getRequestDispatcher("/FindCustomer.jsp").forward(req, resp);	
	}
}