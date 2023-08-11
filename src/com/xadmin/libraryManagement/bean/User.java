package com.xadmin.libraryManagement.bean;

public class User {

	private int id;
	private String name;
	private String email;
	private String pass;
	private int currentNoOfBorrowBooks;
	private int totalNoOfBorrowBooks;
	
	// constructor
	public User(int id, String name, String email, String pass, int currentNoOfBorrowBooks, int totalNoOfBorrowBooks) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.currentNoOfBorrowBooks = currentNoOfBorrowBooks;
		this.totalNoOfBorrowBooks = totalNoOfBorrowBooks;
	}
	
	public User(int id, String name, String pass) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

	public User(int id, String name, String email, String pass) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pass = pass;
	}

	public User(String name, String email, String pass) {
		super();
		this.name = name;
		this.email = email;
		this.pass = pass;
	}
	
	public User(int id) {
		super();
		this.id = id;
	}
	
	public User( String email) {
		super();
		this.email = email;
	}
	
	public User() {
		super();
	}

	// getter and setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getCurrentNoOfBorrowBooks() {
		return currentNoOfBorrowBooks;
	}
	public void setCurrentNoOfBorrowBooks(int currentNoOfBorrowBooks) {
		this.currentNoOfBorrowBooks = currentNoOfBorrowBooks;
	}
	public int getTotalNoOfBorrowBooks() {
		return totalNoOfBorrowBooks;
	}
	public void setTotalNoOfBorrowBooks(int totalNoOfBorrowBooks) {
		this.totalNoOfBorrowBooks = totalNoOfBorrowBooks;
	}
	
}
