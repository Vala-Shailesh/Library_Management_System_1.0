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

@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public registration() {
        super();
    }

    // doGet method for request 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, res);
	}

    // doPsot method for request 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// get user paramater's
		String u_name = req.getParameter("username");
		String u_email = req.getParameter("useremail");
		String u_pass = req.getParameter("password");
		String u_rpass = req.getParameter("repassword");
		
		// add user to database -> register user
		User user = new User(u_name,u_email,u_pass);

		System.out.println(u_pass + " and " + u_rpass);
		System.out.println(u_pass.equals(u_rpass));
		
		// validation of password
		if( u_pass.equals(u_rpass) ) {
			
			UserDao ud = new UserDao();
			
			boolean insertStatus = ud.insertUser(user);
			
			System.out.println(insertStatus);
			
			if (insertStatus) {
				
				System.out.println("User are register successfully...!");
				
				int id = ud.getUserID(user);
				
				req.setAttribute("id", id);
				
				System.out.println("user id -> " + req.getParameter("id"));
				
				req.setAttribute("registerStatus", "true");
				
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, res);
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, res);
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, res);
		}
	}

}
