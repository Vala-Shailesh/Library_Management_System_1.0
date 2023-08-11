package com.xadmin.libraryManagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.xadmin.libraryManagement.bean.Book;
import com.xadmin.libraryManagement.bean.BorrowBook;

public class BookBorrowManagementDao {

	// some useful parameter's
	private String JDBCDriver = "com.mysql.cj.jdbc.Driver";
	private String JDBCURL = "jdbc:mysql://localhost:3306/library";
	private String user = "root";
	private String pass = "";
	
	// some useful MySQL queries
	// BorrowBook 
	private String borrowBookUser = "Insert into borrowBook( UserID, UserName, ISBN, BookTitle, BookAuthor, BorrowTime) values(?,?,?,?,?,?);";
	private String returnBook = "Delete from borrowBook where UserID = ? and ISBN = ?; ";
	private String selectISBNbyUserID = "Select ISBN from borrowBook where UserID = ?; ";
	
	// user
	private String selectUser = "Select * from user where ID = ?;";
	private String userReturnBook = "Update user set CurrentBorrowBook = ? where ID = ?;";
	private String userBorrowBook = "Update user set CurrentBorrowBook = ? , TotalBorrowBook = ? where ID = ?;";
	
	// books
	private String selectBook = "Select * from books where ISBN = ? and Author = ? and Title = ?; ";
	// private String selectBookByUserID = "Select * from books where UserID = ? and ISBN = ? and Author = ? and Title = ?; ";
	private String bookBorrow = "Update books set AvaliableStatus = ?, NumberOfBorrowBook = ? where ISBN = ? ;";
	private String bookReturn = "Update books set AvaliableStatus = ?, NumberOfBorrowBook = ? where ISBN = ? ;";
	
	
	// open connection with databases
	protected Connection openConnection() {
		
		Connection con = null;
		
		try {
			Class.forName(JDBCDriver);
			
			con = DriverManager.getConnection(JDBCURL, user, pass);
		}
		catch( ClassNotFoundException e ){
			e.printStackTrace();
		}
		catch( SQLException e){
			e.printStackTrace();
		}
		
		return con;
	}
	
	// borrow book update
	public boolean borrowBook( BorrowBook bb ){
		
		boolean flag = false;
		
		try {
			
			Connection con = openConnection();
			
			boolean u_status = updateUserBookBorrow(con, bb.getUserId());
			boolean b_status = updateBookBorrow(con,bb.getUserId() , bb.getISBN(), bb.getBookAuthor(), bb.getBookTitle());
			
			if( u_status && b_status ) {
				
				flag = true;
			}	
			else {
				System.out.println("User status -> " + u_status + "\t Book status -> " + b_status);
				System.out.println("Some error are ocurr....!");
			}
	
			if( flag ) { 
				
				PreparedStatement ps = con.prepareStatement(borrowBookUser);
				
				ps.setInt(1, bb.getUserId());
				ps.setString(2, bb.getUserName());
				ps.setInt(3, bb.getISBN());
				ps.setString(4, bb.getBookAuthor());
				ps.setString(5, bb.getBookTitle());
				
				// date current date
				Timestamp date = new Timestamp(new Date().getTime());
				
				ps.setTimestamp(6, date);
				
				int row = ps.executeUpdate();
				
				if( row > 0){
					flag = true;
				}
				else {
					flag = false;
				}
			}
			
		}
		catch( SQLException e ){
			e.printStackTrace();
		}
		
		return flag;
	}
	
	// return book update
	public boolean returnBook( BorrowBook bb ){
		
		boolean flag = false;
		
		 try {
			
			Connection con = openConnection();
			
			// book are successfully return then only user and book details are update
			boolean userUpdateStatus = updateUserBookReturn(con, bb.getUserId());
			boolean bookUpdateStatus = updateBookReturn(con,bb.getISBN(), bb.getBookAuthor(), bb.getBookTitle()); 
			
			if( userUpdateStatus && bookUpdateStatus) {
				
				System.out.println("all data are updated.......successfully...!");	
				flag = true;
			}
			else { 
				System.out.println("all data are not updated.......successfully...!" + bookUpdateStatus );	
			}

			if( flag ){
				PreparedStatement ps = con.prepareStatement(returnBook);
				
				ps.setInt(1, bb.getUserId());
				ps.setInt(2, bb.getISBN());
				
				int row = ps.executeUpdate();
			
				if( row > 0 ) {
					flag = true;
				}
				else {
					flag = false;
				}
			}
		}
		catch( SQLException e){
			e.printStackTrace();
		}
		
		return flag;
	}


	// User data update -> after book is borrowing
	public boolean updateUserBookBorrow(Connection con, int id) {
		
		boolean flag = false;
		
		try {
			// book are successful borrow that only user data are update
			PreparedStatement ps = con.prepareStatement(selectUser);
		
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			int numberOfBorrowBook = -1;
			int totalNumberOfBorrowBook = -1;
			
			if( rs.next() ) {
			
				numberOfBorrowBook = rs.getInt("CurrentBorrowBook");
				totalNumberOfBorrowBook = rs.getInt("TotalBorrowBook");
			}
			
			System.out.println("Number of borrow book " + numberOfBorrowBook + " and  Total number of borrow book " + totalNumberOfBorrowBook );
			
			if( numberOfBorrowBook >= 0 && totalNumberOfBorrowBook >= 0 ){
				
				numberOfBorrowBook++;
				totalNumberOfBorrowBook++;
			
				PreparedStatement ps_2 = con.prepareStatement(userBorrowBook);
				
				ps_2.setInt(1, numberOfBorrowBook);
				ps_2.setInt(2, totalNumberOfBorrowBook);
				ps_2.setInt(3, id);
			
				int updateUser = ps_2.executeUpdate();
			
				if( updateUser > 0){
					flag = true;
				}
			}
		}
		catch( SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}
	
	// Book data update -> after book is borrowing
	public boolean updateBookBorrow(Connection con, int UserID, int ISBN, String author, String title) {
			
			boolean flag = false;
			
			try {
				// book are successful borrow that only user data are update
				PreparedStatement ps = con.prepareStatement(selectBook);
			
				ps.setInt(1, ISBN);
				ps.setString(2, author);
				ps.setString(3, title);
				
				ResultSet rs = ps.executeQuery();
				
				if( rs.next() ) {
					
					
					short avaliableStatus = rs.getByte("AvaliableStatus");
					int numberOfBorrowBook = rs.getInt("NumberOfBorrowBook");
					int numberOfCopyBook = rs.getInt("NumberOfCopy");
					
					if( avaliableStatus == 1 && numberOfCopyBook >= numberOfBorrowBook){			
						numberOfBorrowBook++;
					
						// check if last book then change avaliable book status
						if( numberOfBorrowBook >= numberOfCopyBook ) {
							avaliableStatus = 0;
						}
						else {
							// System.out.println("book are not borrow....!!!");
						}
						
						PreparedStatement ps_2 = con.prepareStatement(bookBorrow);
						
						ps_2.setShort(1, avaliableStatus);
						ps_2.setInt(2, numberOfBorrowBook);
						ps_2.setInt(3, ISBN);
						
						int updateBook = ps_2.executeUpdate();
						
						if( updateBook > 0){
							flag = true;
						}
					}
					else{
						System.out.println("Book are not avaliable for borrowing.....!");
					}
				}
			}
			catch( SQLException e) {
				e.printStackTrace();
			}
			
			return flag;
			
		}

	// User data update -> after book is returning
	public boolean updateUserBookReturn(Connection con, int id) {
		
		boolean flag = false;
		
		try {
			// book are successful borrow that only user data are update
			PreparedStatement ps = con.prepareStatement(selectUser);
		
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			// set pointer
			rs.next();
			
			int numberOfBorrowBook = rs.getInt("CurrentBorrowBook");
			
			if( numberOfBorrowBook > 0 ) {				
				numberOfBorrowBook--;
			}
			else {
				numberOfBorrowBook = 0;
			}
			
			PreparedStatement ps_2 = con.prepareStatement(userReturnBook);
			
			ps_2.setInt(1, numberOfBorrowBook);
			ps_2.setInt(2, id);
			
			int updateUser = ps_2.executeUpdate();
			
			if( updateUser > 0){
				flag = true;
			}
		}
		catch( SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// Book data update -> after book is returning
	public boolean updateBookReturn(Connection con, int ISBN, String author, String title) {
			
			boolean flag = false;
			
			try {
				
				// book are successful borrow that only user data are update
				PreparedStatement ps = con.prepareStatement(selectBook);
			
				ps.setInt(1, ISBN);
				ps.setString(2, author);
				ps.setString(3, title);
				
				ResultSet rs = ps.executeQuery();
				
				System.out.println( ISBN + " " +  author + " " + title); 
				
//				if(rs.next() ) {
//					System.out.println("\n" + rs.getInt("NumberOfCopy"));
//				}
//				rs.previous();
				
				Short avaliableStatus = -1;
				int numberOfBorrowBook = -1;
				
				// set pointer
				if(rs.next()) {
					
					avaliableStatus = rs.getShort("AvaliableStatus");
					numberOfBorrowBook = rs.getInt("NumberOfBorrowBook");
				}
				
				 System.out.println("It come in after if statment...!! Avaliable status is -> " + avaliableStatus );
				
				
				if( avaliableStatus != -1 && numberOfBorrowBook != -1 ) {
									
					if( avaliableStatus != 1 ){			
						avaliableStatus = 1;
					}
					if(numberOfBorrowBook > 0 ) {
						numberOfBorrowBook--;
					}
					
					PreparedStatement ps_2 = con.prepareStatement(bookReturn);
					
					ps_2.setShort(1, avaliableStatus);
					ps_2.setInt(2, numberOfBorrowBook);
					ps_2.setInt(3, ISBN);
					
					int updateBook = ps_2.executeUpdate();
					
					if( updateBook > 0){
						flag = true;
					}
				}
			}
			catch( SQLException e) {
				e.printStackTrace();
			}
			
			return flag;
			
		}
	
	// select borrowed book using userID
	public ArrayList<Book> getAllBookISBNByUserID(BorrowBook bb){
		
		ArrayList<Book> BookList = new ArrayList<Book>();
		
		ArrayList<Integer> ISBNList = new ArrayList<Integer>();
		
		try {
			Connection con = openConnection();
			
			PreparedStatement ps = con.prepareStatement(selectISBNbyUserID);
			
			ps.setInt(1,bb.getUserId());
			
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() ) {
				ISBNList.add(rs.getInt("ISBN"));
			}
			
			System.out.println( "\n User are borrow total number of is -> " + ISBNList.size());
			
			if( ISBNList.size() > 0) {
				
				// PreparedStatement ps_2 = con.prepareStatement("");
				
				BookDao book = new BookDao();
				
				Book b1 = new Book();
				Book b2 = new Book();
				
				for(int i=0; i<ISBNList.size(); i++ ) {
							
					b1.setISBN(ISBNList.get(i));			 
					 
					System.out.println(b1.getISBN());	
					 
					b2 = book.getBookByISBN(b1);
					
					System.out.println(b2.getTitle());
					
					BookList.add(b2);
				}
			}
			
		}
		catch( SQLException e) {
			e.printStackTrace();
		}
		
		return BookList;
	}
}
