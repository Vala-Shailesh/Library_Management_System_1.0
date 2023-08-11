package com.xadmin.libraryManagement.web;

import java.io.File;
import java.io.IOException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.xadmin.libraryManagement.bean.Book;
import com.xadmin.libraryManagement.dao.BookDao;

@WebServlet("/myBook/addBook")
@MultipartConfig
public class addBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public addBook() {
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
		
		
		// check valid user access
		if( id != null  ){
			
			// get user form values
			String b_title = req.getParameter("booktitle");
			String b_author = req.getParameter("bookauthor");
			int b_ISBN = Integer.parseInt(req.getParameter("bookISBN"));
			int b_copy = Integer.parseInt(req.getParameter("bookcopy"));
			int u_id = (int)session.getAttribute("id");
			// get user uploaded file and image
			Part b_cover = req.getPart("bookcover");
			String b_cover_name = b_cover.getSubmittedFileName();
			
			Part b_pdf  = req.getPart("bookpdf");
			String b_pdf_name = b_pdf.getSubmittedFileName();
			
			Book b = new Book(b_ISBN,u_id ,b_title, b_author, b_copy, b_cover_name, b_pdf_name);
			 
			// System.out.println( b.getTitle() + "\n" + b.getAuthor() + "\n" + b.getNumberOfCopy() +  "\n" + b.getBookCover() + "\n" + b.getBookPdf()); 
			 
			// add book into database
			BookDao bookDB = new BookDao();
			
			boolean insertStatus = bookDB.insertBook(b);
			
			if( insertStatus) {
				
				// System.out.println("\n Book are added Successfully....!!! \n");
				
				String bookCoverPath = getServletContext().getRealPath("") + "resources\\Images\\Book_Cover";
				String bookPdfPath = getServletContext().getRealPath("") + "resources\\PDF";
				
//				File bookCoverFlie = new File(bookCoverPath);
//				File bookPdfFlie = new File(bookPdfPath);
				
				b_cover.write(bookCoverPath + File.separator + b_cover_name ); 
				b_pdf.write(bookPdfPath + File.separator + b_pdf_name);
				
				System.out.println(bookCoverPath + "\n" + bookPdfPath);
				
				System.out.println("Book added Successfully..\n");
			}
			
		
			res.sendRedirect("../myBook");
		}
		else {
			res.sendRedirect("/index");
		}
	}

}
