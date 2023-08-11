package com.xadmin.libraryManagement.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.Cookie;

import com.xadmin.libraryManagement.bean.User;
import com.xadmin.libraryManagement.dao.UserDao;

@WebServlet("/login")
public class login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
		// get parameter from user
		int id = Integer.parseInt(req.getParameter("userid"));
		String u_name = req.getParameter("username");
		String pass = req.getParameter("password");
		
		User user = new User(id,u_name,pass);
		
		// authorize user 
		UserDao ud = new UserDao();
		
		Boolean userStatus = ud.checkUser(user);
		
		if( userStatus){
			
			System.out.println("User are valid and login.....!");
			System.out.println("user details -> " + user.getId() + " " + user.getPass());
			
			HttpSession s = req.getSession();
			
			s.setAttribute("id", user.getId()); 
			s.setAttribute("name", user.getName());
			
			s.setMaxInactiveInterval(60*60);
			
			/*
			 * Cookie c = new Cookie("id", Integer.toString(user.getId()));
			 * 
			 * res.addCookie(c);
			 */
			
			RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
			rd.forward(req, res);
			
			// res.sendRedirect("./home");
			
		}
		else{
			
			System.out.println(userStatus + " invalid user..!!!");

			req.setAttribute("loginStatus", "false");
			
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);
			
			// res.sendRedirect("./index");
			
		}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// transfer upcoming request to doGet method
		doGet(req, res);
	}

}
