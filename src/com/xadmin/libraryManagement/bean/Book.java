package com.xadmin.libraryManagement.bean;

public class Book {

	private int ISBN;
	private int userID;
	private String title;
	private String author;
	private int numberOfCopy;
	private String bookCover;
	private String bookPdf;	
	private String currentTime;
	private int avaliableStatus;
	private int numberOfBorrowBook;
	

	// constructor
	public Book(int ISBN,int userID, String title, String author, int numberOfCopy, String bookCover, String bookPdf, String currentTime) {
		super();
		this.ISBN = ISBN;
		this.userID = userID;
		this.title = title;
		this.author = author;
		this.numberOfCopy = numberOfCopy;
		this.bookCover = bookCover;
		this.bookPdf = bookPdf;
		this.currentTime = currentTime;
	}	
	
	public Book(int ISBN,int userID, String title, String author, int numberOfCopy, String bookCover, String bookPdf) {
		super();
		this.ISBN = ISBN;
		this.userID = userID;
		this.title = title;
		this.author = author;
		this.numberOfCopy = numberOfCopy;
		this.bookCover = bookCover;
		this.bookPdf = bookPdf;
	}	
	
	public Book(int ISBN, String title, String author, int numberOfCopy, String bookCover, String bookPdf) {
		super();
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.numberOfCopy = numberOfCopy;
		this.bookCover = bookCover;
		this.bookPdf = bookPdf;
	}
	public Book(int ISBN,int userID, String title, String author) {
		super();
		this.ISBN = ISBN;
		this.userID = userID;
		this.title = title;
		this.author = author;
	}
	
	public Book(int ISBN, String title, String author, int numberOfCopy) {
		super();
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.numberOfCopy = numberOfCopy;
	}

	public Book(int ISBN, String title, String author,String bookCover, String bookPdf) {
		super();
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.bookCover = bookCover;
		this.bookPdf = bookPdf;
	}
	
	public Book(String title, String bookCover) {
		super();
		this.title = title;
		this.bookCover = bookCover;
	}

	public Book(int ISBN , int userID) {
		super();
		this.ISBN = ISBN;
		this.userID = userID;
	}
	
	public Book(int userID ){
		super();
		this.userID = userID;
	}
	
	public Book() {
		super();
	}

	// getter and setter
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int ISBN) {
		this.ISBN = ISBN;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getNumberOfCopy() {
		return numberOfCopy;
	}
	public void setNumberOfCopy(int numberOfCopy) {
		this.numberOfCopy = numberOfCopy;
	}
	public String getBookCover() {
		return bookCover;
	}
	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}
	public String getBookPdf() {
		return bookPdf;
	}
	public void setBookPdf(String bookPdf) {
		this.bookPdf = bookPdf;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	public int getAvaliableStatus() {
		return avaliableStatus;
	}
	public void setAvaliableStatus(int avaliableStatus) {
		this.avaliableStatus = avaliableStatus;
	}
	public int getNumberOfBorrowBook() {
		return numberOfBorrowBook;
	}
	public void setNumberOfBorrowBook(int numberOfBorrowBook) {
		this.numberOfBorrowBook = numberOfBorrowBook;
	}
	
}
