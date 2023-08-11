package com.xadmin.libraryManagement.bean;

public class BorrowBook {

	private int userID;
	private int ISBN;
	private String userName;
	private String bookTitle;
	private String bookAurhor;
	
	// constructor
	public BorrowBook(int userID, int ISBN, String userName, String bookTitle, String bookAurhor) {
		super();
		this.userID = userID;
		this.ISBN = ISBN;
		this.userName = userName;
		this.bookTitle = bookTitle;
		this.bookAurhor = bookAurhor;
	}
	
	public BorrowBook(int userID, int ISBN) {
		super();
		this.userID = userID;
		this.ISBN = ISBN;
	}

	public BorrowBook(int userID ) {	
		this.userID = userID;
	}
	
	// getter and setter
	public int getUserId() {
		return userID;
	}
	public void setUserId(int userId) {
		this.userID = userId;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int ISBN) {
		this.ISBN = ISBN;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAurhor;
	}
	public void getBookAuthor(String bookAurhor) {
		this.bookAurhor = bookAurhor;
	}
	
	
}
