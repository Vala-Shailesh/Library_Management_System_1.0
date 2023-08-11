package com.xadmin.libraryManagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.xadmin.libraryManagement.bean.User;

@SuppressWarnings("unused")
public class UserDao {

	private String JDBCDriver = "com.mysql.cj.jdbc.Driver";
	private String JDBCURL = "jdbc:mysql://localhost:3306/library";
	private String userName = "root";
	private String pass = "";
	
	// some commanly query for SQL statement
	private String insertQuery = "Insert user(Name,Email,Password,DateOfJoin, CurrentBorrowBook, TotalBorrowBook) values(?,?,?,?,0,0);";
	private String updateQuery = "Update user set Name = ? , Email = ? , Password = ? where ID = ?;";
	private String selectUser = "SELECT * FROM `user` WHERE  ID = ? AND Name = ? AND Password = ?;";
	private String deleteUser = "Delete user where ID = ? ;"; 
	private String selectUserById = "SELECT * FROM `user` WHERE ID = ?;";
	private String selectUserByEmail = "SELECT * FROM `user` WHERE Email = ?;";
	
	// JDBC connection
	protected Connection openConntection() {
		 
		Connection con = null;
		
		// create MySQL connection
		try {
			Class.forName(JDBCDriver);
			con = DriverManager.getConnection(JDBCURL, userName, pass);
		}
		catch( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		catch( SQLException e) {
			e.printStackTrace();
		}

		return con;
	}
	
	// check user 
	public boolean checkUser( User user) {
		
		boolean flag = false;
		
		try {
			Connection con = openConntection();
			
			PreparedStatement ps = con.prepareStatement(selectUser);
			
			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPass());
			
			ResultSet rs = ps.executeQuery();
			
			if( rs.next()){
				flag = true;
			}
			
		}
		catch( SQLException e ) {
			e.printStackTrace();
			System.out.println( e.getMessage() );	
		}
		
		return flag;
	}
	
	// insert user
	public boolean insertUser( User user ) {
		
		boolean flag = false;
		
		try {
			Connection con = openConntection();
			
			PreparedStatement ps = con.prepareStatement(insertQuery);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPass());
			
			// add current time and date 
			Timestamp date = new Timestamp(new Date().getTime());
			
			ps.setTimestamp(4, date);
			
			int row = 0;
					
			row = ps.executeUpdate();
			
			System.out.println(row);
			
			if( row > 0) {
				flag = true;
			}
			else {
				flag = false;
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
			System.out.println( e.getMessage() );	
		}
		
		return flag;
	}
	
	// update user
	public boolean updateUser( User user) {
		
		boolean flag = false;
		
		try {
			Connection con = openConntection();
			
			PreparedStatement ps = con.prepareStatement(updateQuery);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPass());
			
			// ID as a key parameter
			ps.setInt(4, user.getId());
			
			int row = ps.executeUpdate();
			
			if( row > 0) {
				System.out.println("data are updated...!");
				flag = true;
			}
			else {
				System.out.println("data are not updated...!");
				flag = false;
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	// get user
	public ArrayList<String> getUser( User u ) {
		
		ArrayList<String> user = new ArrayList<String>() ;
		
		try {
			Connection con = openConntection();
			
			PreparedStatement ps = con.prepareStatement(selectUserById);
			
			ps.setInt(1, u.getId());
			
			ResultSet rs = ps.executeQuery();
			
			
			// create a date format
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			
			// get data from database and add into data
			while(rs.next()) {
				
				System.out.println(" user name -> "+ rs.getInt(1));
				user.add(Integer.toString(rs.getInt(1)));
				user.add(rs.getString("Name"));
				user.add(rs.getString("Email"));
				user.add(rs.getString("Password"));
				user.add(rs.getString("DateOfJoin"));
				user.add(Integer.toString(rs.getInt("CurrentBorrowBook")));
				user.add(Integer.toString(rs.getInt("TotalBorrowBook")));
				
				System.out.println(user);
			}
						
		}
		catch( SQLException e ) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return user;
	}
	
	// get user by email 
	public int getUserID( User u ) {
		
		int id = -1;
		
		try {
			Connection con = openConntection();
			
			PreparedStatement ps = con.prepareStatement(selectUserByEmail);
			
			ps.setString(1, u.getEmail());
			
			ResultSet rs = ps.executeQuery();
			
			
			// create a date format
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			
			if( rs.next()) {		
				id = rs.getInt("ID");
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	
	// remove user
	public boolean removeUser( User u ) {
		
		boolean flag = true;
		
		try {
			Connection con = openConntection();
			
			PreparedStatement ps = con.prepareStatement(deleteUser);
			
			ps.setInt(1, u.getId());
			
			int row = ps.executeUpdate();
			
			if( row > 0) {
				flag = true;
			}
		}
		catch( SQLException e ) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
