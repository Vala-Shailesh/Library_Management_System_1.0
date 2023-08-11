package com.xadmin.libraryManagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.xadmin.libraryManagement.bean.Book;

public class BookDao {

	// some variable declaration
	private String JDBCDriver = "com.mysql.cj.jdbc.Driver";
	private String JDBCURL = "jdbc:mysql://localhost:3306/library";
	private String user = "root";
	private String pass = "";
	
	// some commonly used query
	private String insertBook = "Insert into books(ISBN,UserID ,Author, Title, NumberOfCopy, BookCover, BookPdf , DateOfAddition, AvaliableStatus, NumberOfBorrowBook) values(?,?,?,?,?,?,?,?,1,0);";
	private String updateBook = "Update books set Author = ?, Title = ?, BookCover = ?, BookPdf = ?, NumberOfCopy = ? where ISBN = ? AND UserID = ?;";
	private String seletBook = "Select * from books where Title = ? and Author = ? ; ";
	private String removeBook = "Delete from books where ISBN = ? AND UserID = ? AND Title = ? AND Author = ?;";
//	private String getNumberOfBookAvaliable = "Select NumberOfCopy from books where ISBN = ?;";
	private String getAllBooks = "Select * from books;";

	private String getBookById = "Select * from books where userID = ?;";
	private String getBookByISBN = "Select * from books where ISBN = ?;";
	
	// get connection
	protected Connection openConnection() {
		
		Connection con = null;
		
		try {
			
			Class.forName(JDBCDriver);
			
			con = DriverManager.getConnection(JDBCURL,user,pass);
		}
		catch( ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch( SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	// insert book
	public boolean insertBook( Book book ){
		
		boolean flag = false;
		
		try {
			Connection con = openConnection();
			
			PreparedStatement ps = con.prepareStatement(insertBook);
			
			ps.setDouble(1, book.getISBN());
			ps.setInt(2, book.getUserID());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getTitle());
			ps.setDouble(5, book.getNumberOfCopy());
			ps.setString(6, book.getBookCover());
			ps.setString(7, book.getBookPdf());
			
			// date current date
			Timestamp date = new Timestamp(new Date().getTime());
			
			ps.setTimestamp(8, date);
			
			int row = ps.executeUpdate();
			
			if( row > 0 ){
				flag = true;
			}
			
		}
		catch( SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	// update book
	public boolean updateBook( Book book ){
		
		boolean flag = false;
		
		try {
			Connection con = openConnection();
			
			PreparedStatement ps = con.prepareStatement(updateBook);
			
			ps.setString(1, book.getAuthor());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getBookCover());
			ps.setString(4, book.getBookPdf());
			ps.setDouble(5, book.getNumberOfCopy());
			
			// take ID as a key parameter
			ps.setDouble(6, book.getISBN());
			ps.setDouble(7, book.getUserID());
			
			int row = ps.executeUpdate();
			
			if( row > 0 ){
				flag = true;
			}
			
		}
		catch( SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	// get book by id
	public ArrayList<Book> getBookById(Book b) {
		
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			Connection con = openConnection();
			
			PreparedStatement ps = con.prepareStatement(getBookById);
			
			ps.setInt(1, b.getUserID());
			
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() ){
				
				Book book = new Book();
				
				book.setISBN(rs.getInt("ISBN"));
				book.setTitle(rs.getString("Title"));
				book.setAuthor(rs.getString("Author"));
				book.setBookCover(rs.getString("BookCover"));
				book.setBookPdf(rs.getString("BookPdf"));
				book.setNumberOfCopy(rs.getInt("NumberOfCopy"));
			
				// System.out.println("\n\n Book Details \n" + book.getTitle() + "\n" + book.getBookPdf());
				
				bookList.add(book);
			}
			
			// System.out.println("it working....");
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		
		return bookList;
	}
	
	// get book by ISBN
	public Book getBookByISBN(Book b) {
			
			Book book = new Book();
			
			try {
				
				System.out.println( "user id " + b.getISBN());
				
				Connection con = openConnection();
				
				PreparedStatement ps = con.prepareStatement(getBookByISBN);
				
				ps.setInt(1, b.getISBN());
				
				ResultSet rs = ps.executeQuery();
				
				while( rs.next() ){			
					
					book.setISBN(rs.getInt("ISBN"));
					book.setTitle(rs.getString("Title"));
					book.setAuthor(rs.getString("Author"));
					book.setBookCover(rs.getString("BookCover"));
					book.setBookPdf(rs.getString("BookPdf"));
					book.setNumberOfCopy(rs.getInt("NumberOfCopy"));
				
					// System.out.println("\n\n Book Details \n" + book.getTitle() + "\n" + book.getBookPdf());
				}
				
				// System.out.println("it working....");
			}
			catch( SQLException e ) {
				e.printStackTrace();
			}
			
			return book;
		}
	
	// get book
	public Book getBook( Book b){
		
		Book book = new Book();
		
		try {
			Connection con = openConnection();
			
			PreparedStatement ps = con.prepareStatement(seletBook);
			
			ps.setString(1,b.getTitle());
			ps.setString(2, b.getAuthor());
			
			ResultSet rs = ps.executeQuery();
			
			// create a date format
//			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			
			while(rs.next()){
				
				book.setTitle(rs.getString("Title"));
				book.setAuthor(rs.getString("Author"));
				book.setISBN(rs.getInt("ISBN"));
				book.setBookCover(rs.getString("BookCover"));
				book.setBookPdf(rs.getString("BookPdf"));
				
//				book.add(Integer.toString(rs.getInt(1)));
//				book.add(rs.getString(2));
//				book.add(rs.getString(3));
//				book.add(Integer.toString(rs.getInt(4)));
//				book.add(format.format(rs.getTimestamp(5)));
//				book.add(Boolean.toString(rs.getBoolean(6)));
//				book.add(Integer.toString(7));
			}
			
		}
		catch( SQLException e){
			e.printStackTrace();
		}
		
		return book;
	}
	
	// remove book
	public boolean removeBook( Book book ){
		
		boolean flag = false;
		
		try{
			Connection con = openConnection();
			
			PreparedStatement ps = con.prepareStatement(removeBook);
			
			ps.setInt(1 ,(int)book.getISBN());
			ps.setInt(2,book.getUserID());
			ps.setString(3, book.getTitle());
			ps.setString(4, book.getAuthor());
			
			int row = ps.executeUpdate();

			if( row > 0 ) 
			{
				flag = true; 
			}
			
			return flag;
		}
		catch(SQLException e){
			e.printStackTrace();		
		}
		
		return flag;
	}
 
	// get all books 
	public ArrayList<Book> getAllBooks(){
		
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			Connection con = openConnection();
			
			PreparedStatement ps = con.prepareStatement(getAllBooks);
			
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() ){
				
				Book book = new Book();
				
				book.setISBN(rs.getInt("ISBN"));
				book.setTitle(rs.getString("Title"));
				book.setAuthor(rs.getString("Author"));
				book.setBookCover(rs.getString("BookCover"));
				book.setBookPdf(rs.getString("BookPdf"));
				book.setNumberOfCopy(rs.getInt("NumberOfCopy"));
			
				// System.out.println("\n\n Book Details \n" + book.getTitle() + "\n" + book.getBookPdf());
				
				bookList.add(book);
			}
			
			// System.out.println("it working....");
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		
		return bookList;
		
	}
	
}
