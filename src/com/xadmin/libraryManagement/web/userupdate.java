package com.xadmin.libraryManagement.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.libraryManagement.bean.User;
import com.xadmin.libraryManagement.dao.UserDao;

/**
 * Servlet implementation class userupdate
 */
@WebServlet("/userupdate")
public class userupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public userupdate() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		// get paramater form request object
		int id = Integer.parseInt(req.getParameter("userid"));
		String name = req.getParameter("username"); 
		String email  = req.getParameter("useremail");
		String pass = req.getParameter("password");
		
		System.out.println(name + " " + email + " " + pass);
	
			
	 	// get user old datails
	 	User user = new User(id,name,email,pass);
	 	
	 	UserDao userData = new UserDao();
	 	
	 	boolean updateStatus = userData.updateUser(user);
	 	
	 	// System.out.print("User update status -> " + updateStatus);
	 	System.out.println(updateStatus);
	
		req.setAttribute("updateStatus", updateStatus);
		// res.sendRedirect("./account");
		
		 RequestDispatcher rd = req.getRequestDispatcher("account.jsp");
		 rd.forward(req, res);
	}

}
