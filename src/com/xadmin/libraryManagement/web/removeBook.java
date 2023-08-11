package com.xadmin.libraryManagement.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xadmin.libraryManagement.bean.Book;
import com.xadmin.libraryManagement.dao.BookDao;

/**
 * Servlet implementation class removeBook
 */
@WebServlet("/myBook/removeBook")
public class removeBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public removeBook() {
        super();
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// insert new book into databases
		HttpSession session = req.getSession();
		Object id = session.getAttribute("id");
		
		if( id != null ) {
		
			int u_ID = (int)session.getAttribute("id");
			int b_ISBN = Integer.parseInt(req.getParameter("bookISBN"));
			String b_title = req.getParameter("booktitle");
			String b_author = req.getParameter("bookauthor");
			
			Book b = new Book(b_ISBN,u_ID, b_title, b_author);
			
			BookDao book = new BookDao();
			
			boolean removeStatus = book.removeBook(b);
			
			if( removeStatus ) {
				res.sendRedirect("../myBook");
			}
			else{
				res.sendRedirect("../error");
			}
			
		}
		else {
			res.sendRedirect("../index");
		}
	}

}
