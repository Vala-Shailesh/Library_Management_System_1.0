package com.xadmin.libraryManagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xadmin.libraryManagement.bean.BorrowBook;
import com.xadmin.libraryManagement.dao.BookBorrowManagementDao;

/**
 * Servlet implementation class returnBook
 */
@WebServlet("/returnBook")
public class returnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public returnBook() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
    	// redirect request method
    	doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
    	System.out.println("\n Return book path are working rightly....\n ");

    	HttpSession session = req.getSession();
    	    	
    	if( session.getAttribute("id") != null && session.getAttribute("name") != null ) {
    		
	    	// get user data
    		int UserID = (int)session.getAttribute("id");
	    	int ISBN = Integer.parseInt(req.getParameter("bookISBN"));
	    	String UserName = (String)session.getAttribute("name");
	    	String bookTitle = req.getParameter("bookTitle");
	    	String bookAuthor = req.getParameter("bookAuthor");
	    	
	    	
	    	BorrowBook bb = new BorrowBook(UserID, ISBN, UserName ,bookTitle, bookAuthor);
	    	
	    	BookBorrowManagementDao returnBook = new BookBorrowManagementDao();
	    	
	    	boolean returnBookStatus = returnBook.returnBook(bb);
	    	
	    	if( returnBookStatus ) {
	    		
	    		System.out.println("book are not return suceessfully....");
	    		
	    		session.setAttribute("returnBookStatus", "true");
	    		
	    		res.sendRedirect("./services");	    		
	    	}
	    	else {
	    		
	    		System.out.println("book are not return suceessfully....");
	    		
	    		session.setAttribute("returnBookStatus", "false");
	    		
	    		res.sendRedirect("./services");	
	    	}
    	}
	}

}
