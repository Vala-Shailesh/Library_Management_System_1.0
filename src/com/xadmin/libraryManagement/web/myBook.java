package com.xadmin.libraryManagement.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/myBook")
public class myBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public myBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// redirect upcoming file to .jsp file

		HttpSession session = req.getSession();
		
		Object id = session.getAttribute("id");
		
		if( id != null ) {	
		
			RequestDispatcher rd = req.getRequestDispatcher("myBook.jsp");
			rd.forward(req, res);
		}
		else {
			res.sendRedirect("./index");
		}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doGet(req, res);
	}

}
