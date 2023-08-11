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
 * Servlet implementation class borrowBook
 */

@WebServlet("/borrowBook")
public class borrowBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public borrowBook() {
        super();
       
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	    // get session attribute
		HttpSession session = req.getSession();
		
		if( session.getAttribute("id") != null && session.getAttribute("name") != null ) {
			
			int userID = (int)(session.getAttribute("id"));
			String userName = (String)session.getAttribute("name");
			
			// borrow book using specific user ID
			
			int bookISBN = Integer.parseInt(req.getParameter("bookISBN"));
			String bookTitle = req.getParameter("bookTitle");
			String bookAuthor = req.getParameter("bookAuthor");
			
			System.out.println("\n Book are ready for Borrowing.....");
			System.out.println("\n" + userID + "\t" + userName);
			System.out.println("\n" + bookISBN + " \t" + bookTitle + "\t" + bookAuthor + "\n");
			
			BorrowBook bb = new BorrowBook(userID, bookISBN, userName, bookTitle, bookAuthor);
			
			BookBorrowManagementDao borrowBook = new BookBorrowManagementDao();
			
			boolean borrowBookStatus = borrowBook.borrowBook(bb);
			
			if( borrowBookStatus ){
				
				System.out.println("book are borrowing suceessfully......");
			
				session.setAttribute("borrowBookStatus", "true");
				
			}
			else {
				session.setAttribute("borrowBookStatus", "false");
			}
			res.sendRedirect("./home");
		
		}
		else {
			res.sendRedirect("./index");
		}
		
	}

}
