<%@page import="com.xadmin.libraryManagement.dao.BookDao"%>
<%@page import="com.xadmin.libraryManagement.bean.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- Favicons -->
	<link type="image/x-png" sizes="16x16" rel="icon" href="./resources/Images/Flavicons/Web/icon-16.png">
	<link type="image/x-png" sizes="32x32" rel="icon" href="./resources/Images/Flavicons/Web/icon-32.png">
	<link type="image/x-png" sizes="96x96" rel="icon" href="./resources/Images/Flavicons/Web/icon-96.png">
	<link rel="icon" type="image/x-png" sizes="72x72" href="./resources/Images/Flavicons/Android Chrome/icon-72.png">
	<link rel="icon" type="image/x-png" sizes="96x96" href="./resources/Images/Flavicons/Android Chrome/icon-96.png">
	<meta name="msapplication-square70x70logo" content="./resources/Images/Flavicons/Wiindow/icon-70.png">

	<!-- favicon -->
	<link type="image/png" sizes="16x16" rel="icon" href=".../icons8-library-16.png">
	<link type="image/png" sizes="32x32" rel="icon" href=".../icons8-library-32.png">
	<link type="image/png" sizes="96x96" rel="icon" href=".../icons8-library-96.png">
	
     <!-- boostarp addition -->
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"
          defer></script>

     <!-- Custom external styling -->
     <link rel="stylesheet" type="text/css" href="./resources/CSS/styleSheet.css">
     <link rel="stylesheet" type="text/css" href="./resources/CSS/login.css">
     <link rel="stylesheet" type="text/css" href="./resources/CSS/mediaQuery.css"> 
 
     <!-- script -->
     <script type="text/javascript" src="./resources/JS/popupForm.js" type="module" defer></script> 
     <script type="text/javascript" src="./resources/JS/bookControl.js" type="module" defer></script>
     <script type="text/javascript" src="./resources/JS/notificationBox.js" type="module" defer></script>

     <title> Our Library </title>
</head>

<body>

	<!-- Registration Acknowledge -->
    <%
    	
    	String loginBox = "none";
    	String registerBox = "none"; 
    	String popupDisplay = "block";
    	
    	Object loginStatus = request.getAttribute("loginStatus");
    	Object registerStatus = request.getAttribute("registerStatus");
    	
     	if( loginStatus != null){
    		loginBox = "block";
    		popupDisplay = "none";
    	} 
    	
    	if( registerStatus != null){
    		registerBox = "block";
    		popupDisplay = "none";
    	} 
    	
    %>
	
	<!-- popup box for invalid login -->
     <div class="container register-box box-shadow-2 p-4 rounded-2" style="display : <%= loginBox %>; " >
		<h3 class="text-danger"> Invalid User </h3> 
		<hr>
		<p> Please ! Enter <b>right</b> details for login </p>
		<button type="button" class="btn btn-end btn-danger registerBox-close-btn w-25"> Ok </button>
	</div>
	 
	<!-- popup box for successfully register -->
	<div class="container register-box box-shadow-2 p-4 rounded-2" style="display: <%= registerBox %>;">
		<h3 class="text-success"> Welcome to Our Library </h3> 
		<hr>
		<p> Your are successfully register and your user ID is <b><%= request.getAttribute("id") %></b> </p>
		<button type="button" class="btn btn-end btn-success registerBox-close-btn w-25 mt-4"> Ok </button>
	</div>
	
     <!-- header -->
     <nav class="navbar navbar-expand-sm navbar-dark bg-dark text-lg">
          <div class="container-fluid w-90">
               <a class="navbar-brand h1 text-light text-xxl" href="#"> Book's Library </a>
               <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar"
                    aria-controls="navbar">
                    <span class="navbar-toggler-icon"></span>
               </button>
               <div class="collapse navbar-collapse justify-content-end" id="navbar">
                    <div class="navbar-nav">
                         <a class="nav-link active" aria-current="page" href="#"> Home </a>
                         <a class="nav-link" href="#"> Contact </a>
                         <a class="nav-link open-login-form" href="#"> Login </a>
                    </div>
               </div>
          </div>
     </nav>

     <!-- carousel -->
     <div id="carousel" class="carousel slide">
          <div class="carousel-indicators">
               <button type="button" data-bs-target="#carousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
               <button type="button" data-bs-target="#carousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
               <button type="button" data-bs-target="#carousel" data-bs-slide-to="2"aria-label="Slide 3"></button>
          </div>
          <div class="carousel-inner">
               <div class="carousel-item active">
                    <img src="./resources/Images/Main/carousel_img_1.jpg" class="d-block w-100" height="700px" alt="Library Image">
                    <div class="carousel-caption d-none d-md-block">
                         <h2 class=""> Books key to Success </h2>
                         <p class=""> Books give a lot of required knowledge about grow our self. </p>
                    </div>
               </div>
               <div class="carousel-item">
                    <img src="./resources/Images/Main/carousel_img_2.jpg" class="d-block w-100" height="700px" alt="Library Image">
                    <div class="carousel-caption d-none d-md-block">
                         <h2> Books are our best friend </h2>
                         <p> We know some time we feel, some book are give all answer of our life like any good friend </p>
                    </div>
               </div>
               <div class="carousel-item">
                    <img src="./resources/Images/Main/carousel_img_3.jpg" class="d-block w-100" height="700px" alt="Library Image">
                    <div class="carousel-caption d-none d-md-block">
                         <h2> Books are very good teacher </h2>
                         <p> Any one book are always come in any person like are guide to live a best life as a good teacher. </p>
                    </div>
               </div>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#carousel" data-bs-slide="prev">
               <span class="carousel-control-prev-icon" aria-hidden="true"></span>
               <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carousel" data-bs-slide="next">
               <span class="carousel-control-next-icon" aria-hidden="true"></span>
               <span class="visually-hidden">Next</span>
          </button>
     </div>

     <!-- About us  --> 
     <div class="container-fluid m-3 mt-5 row">
           <div class="col-sm-12 col-lg-6 col-md-6 about">
                <h1> About us </h1>
                <p class="text-justify"> Our library are provide a lot of usefull book for you. </p>
                <p class="text-justify"> We all know improtant of books in our life and now day all things are come to in online platform and that way we can provide a lot of books through this website to read any books and anytime. </p>
           </div>
           <div class="col-sm-12 col-lg-6 col-md-6">
                <img src="./resources/Images/Main/about_us.jpg" alt="library books images.." height="500px" class="mw-100">
           </div>
     </div>

     <!-- books gallery -->
     	<%
	    // out.println("It working...");
	
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		BookDao book = new BookDao();
		
		bookList = book.getAllBooks();

		String displayBookList = "";
		if(bookList.size() < 0  ){
			displayBookList = "display-none";
		}
		
		out.println("<div class=\"" + displayBookList + " \"> ");
	%>
 	
     <!-- Read book -->
         <div class="p-3"> 
	     <div class="container-fluid m-4 mt-5">
	          <h1 class="align-center"> Our Books Gallery </h1>
	          <p class="align-center m-0">
	               Below are given a lot of infromation in various field as well as giude you to do more about our career. Also some of them are feel good in between regular life tension.
	          </p>
	          <p class="align-center m-0"> Books are very special for all person that are know improtant of books. </p>
	     </div>
     </div>
     
     <div class="container-fiuld container-center w-90  p-4">
     	 <div class="container-fluid row justify-content-center w-100 p-4 m-0">    	 	
     	 	 <% for( int s = 0; s < bookList.size(); s++ ){ %>
     	 	 	<div class="col-lg-3 col-md-4 col-sm-6 row p-0 my-4 mx-2 rounded-3 book-pointer">
     	 	 		<div class="container-center box-shadow-1 mt-3 p-0" style="width: 200px; height: 250px;">
		     	 		<img alt="Books Images.." src="<%= "http://localhost:8085/Library_Management_System_1.0/resources/Images/Book_Cover/"  + bookList.get(s).getBookCover() %>"  width="200px" height="250px" class="rounded-2" /> 					
     	 	 		</div>
	     	 		<p class="align-center mt-3"> <b> Title : </b> <%= bookList.get(s).getTitle() %> </p>	
					<p class="align-center mt-1"> <b> Author : </b> <%= bookList.get(s).getAuthor() %> </p>	 
     	 		</div>		
     	 	 <% } %>  	 
     	 </div>
   	   	 <div class="row w-100 p-3">
             <button type="button" class="btn btn-dark btn-center col-1" id="viewMoreBook" style="min-width: 120px;"> View More </button>
         </div>
     </div>
     
     <%= "</div>" %>

     <!-- contact --> 
     <div class="container-fluid w-100 p-0 mt-4">
          <h1 class="align-center"> Contact Us </h1>
          <div class="d-flex justify-content-start mt-5 p-4 contact-bg">
               <div class="col-lg-4 col-md-6 col-sm-12 extra-container">
                    <form class="mt-90"> 
                         <div class="m-5">
                              <input type="text" class="form-control form-control-lg rounded-5" placeholder="Enter your name..." name="name" id="c_username">
                         </div>
                         <div class="m-5">
                              <input type="email" class="form-control form-control-lg rounded-5" placeholder="Enter your email..." name="email" id="c_useremail">
                         </div>
                         <div class="m-5">
                              <input type="tel" class="form-control form-control-lg rounded-5" placeholder="Enter your number..." name="number" id="c_usernumber">
                         </div>
                         <div class="m-5 d-grid">
                              <button type="submit" class="btn btn-block rounded-5 btn-success"> Send </button>
                         </div>
                    </form>
               </div>
          </div>
     </div>

     <!-- footer -->
     <div class="container-fluid w-100 p-5 box-shadow-1 mt-5">
          <div class="container-sm d-flex justify-content-evenly">
               <a href="#" class="anchor-tag"> Our Library </a>
               <a href="https://getbootstrap.com/" class="anchor-tag"> Boostrop </a>
               <a href="https://en.wikipedia.org/wiki/Book" class="anchor-tag"> Book wikipedia </a>
               <div class="icon">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-linkedin linkedin" viewBox="0 0 16 16">
                         <path d="M0 1.146C0 .513.526 0 1.175 0h13.65C15.474 0 16 .513 16 1.146v13.708c0 .633-.526 1.146-1.175 1.146H1.175C.526 16 0 15.487 0 14.854V1.146zm4.943 12.248V6.169H2.542v7.225h2.401zm-1.2-8.212c.837 0 1.358-.554 1.358-1.248-.015-.709-.52-1.248-1.342-1.248-.822 0-1.359.54-1.359 1.248 0 .694.521 1.248 1.327 1.248h.016zm4.908 8.212V9.359c0-.216.016-.432.08-.586.173-.431.568-.878 1.232-.878.869 0 1.216.662 1.216 1.634v3.865h2.401V9.25c0-2.22-1.184-3.252-2.764-3.252-1.274 0-1.845.7-2.165 1.193v.025h-.016a5.54 5.54 0 0 1 .016-.025V6.169h-2.4c.03.678 0 7.225 0 7.225h2.4z"/>
                    </svg>
               </div>
               <div class="icon">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-twitter twitter" viewBox="0 0 16 16">
                         <path d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"/>
                    </svg>
               </div>
               <div class="icon">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-instagram instagram" viewBox="0 0 16 16">
                         <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"/>
                       </svg>
               </div>
          </div>
          <div class="container-sm d-flex justify-content-center my-4"> 
          		<b>&copy;copyright 2023</b> - All Rights Reserved.
          </div>
     </div>

     <!-- login -->
      <div class="login-box box-shadow-2 display-none" style="display : <%= popupDisplay %>">  
          <div class="close-form-icon">
               <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                    <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
               </svg>
          </div>
          <div id="login" class=""> 
               <h1 class="align-center m-3 p-3"> Login </h1>
               <div class="d-flex flex-column justify-content-center mt-4">
                    <form action="./login" method="post" class="w-100 form login-form">
                    	<label for="userid">
                              <input type="number" name="userid" id="userid" placeholder="User ID" class="input" min="0" title="Please enter your name" required="required">
                              <div class="input-icon">
                                   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                   </svg>
                              </div>
                         </label>
                         <label for="username">
                              <input type="text" name="username" id="username" placeholder="User name" class="input" required="required">
                              <div class="input-icon">
                                   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                   </svg> 
                              </div>
                         </label>
                         <label for="password">
                              <input type="password" name="password" id="password" placeholder="User password" class="input" required="required">
                              <div class="input-icon">
                                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-key-fill" viewBox="0 0 16 16">
  										<path d="M3.5 11.5a3.5 3.5 0 1 1 3.163-5H14L15.5 8 14 9.5l-1-1-1 1-1-1-1 1-1-1-1 1H6.663a3.5 3.5 0 0 1-3.163 2zM2.5 9a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
									</svg>
                              </div>
                         </label>                         
                         <button type="submit" class="btn btn-success btn-center mt-4"> Login </button>
                    </form>
                    <div class="d-flex justify-content-evenly w-75 container mt-4">
                    <div class="icon">
                         <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-linkedin linkedin" viewBox="0 0 16 16">
                              <path d="M0 1.146C0 .513.526 0 1.175 0h13.65C15.474 0 16 .513 16 1.146v13.708c0 .633-.526 1.146-1.175 1.146H1.175C.526 16 0 15.487 0 14.854V1.146zm4.943 12.248V6.169H2.542v7.225h2.401zm-1.2-8.212c.837 0 1.358-.554 1.358-1.248-.015-.709-.52-1.248-1.342-1.248-.822 0-1.359.54-1.359 1.248 0 .694.521 1.248 1.327 1.248h.016zm4.908 8.212V9.359c0-.216.016-.432.08-.586.173-.431.568-.878 1.232-.878.869 0 1.216.662 1.216 1.634v3.865h2.401V9.25c0-2.22-1.184-3.252-2.764-3.252-1.274 0-1.845.7-2.165 1.193v.025h-.016a5.54 5.54 0 0 1 .016-.025V6.169h-2.4c.03.678 0 7.225 0 7.225h2.4z"/>
                         </svg>
                    </div>
                    <div class="icon">
                         <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-twitter twitter" viewBox="0 0 16 16">
                              <path d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"/>
                         </svg>
                    </div>
                    <div class="icon">
                         <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-instagram instagram" viewBox="0 0 16 16">
                              <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"/>
                         </svg>
                    </div>
                    </div>
                    <p class="align-center mt-4"> You have account ? <a href="" class="anchor-tag registration-open"> Register Now!</a> </p>
               </div>
          </div>
          <div id="registration" class="display-none"> 
               <h1 class="align-center m-3 p-3"> Registration </h1>
               <div class="d-flex flex-column justify-content-center mt-4">
                    <form action="./registration" method="post" class="w-100 form registration-form">
                         <label for="username">
                              <input type="text" name="username" id="username" placeholder="User name" class="input" required="required">
                              <div class="input-icon">
                                   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                                   </svg>
                              </div>
                         </label>
                         <label for="useremail">
                              <input type="email" name="useremail" id="useremail" placeholder="User email" class="input" required="required">
                              <div class="input-icon">
                                   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope-at-fill" viewBox="0 0 16 16">
                                        <path d="M2 2A2 2 0 0 0 .05 3.555L8 8.414l7.95-4.859A2 2 0 0 0 14 2H2Zm-2 9.8V4.698l5.803 3.546L0 11.801Zm6.761-2.97-6.57 4.026A2 2 0 0 0 2 14h6.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.606-3.446l-.367-.225L8 9.586l-1.239-.757ZM16 9.671V4.697l-5.803 3.546.338.208A4.482 4.482 0 0 1 12.5 8c1.414 0 2.675.652 3.5 1.671Z"/>
                                        <path d="M15.834 12.244c0 1.168-.577 2.025-1.587 2.025-.503 0-1.002-.228-1.12-.648h-.043c-.118.416-.543.643-1.015.643-.77 0-1.259-.542-1.259-1.434v-.529c0-.844.481-1.4 1.26-1.4.585 0 .87.333.953.63h.03v-.568h.905v2.19c0 .272.18.42.411.42.315 0 .639-.415.639-1.39v-.118c0-1.277-.95-2.326-2.484-2.326h-.04c-1.582 0-2.64 1.067-2.64 2.724v.157c0 1.867 1.237 2.654 2.57 2.654h.045c.507 0 .935-.07 1.18-.18v.731c-.219.1-.643.175-1.237.175h-.044C10.438 16 9 14.82 9 12.646v-.214C9 10.36 10.421 9 12.485 9h.035c2.12 0 3.314 1.43 3.314 3.034v.21Zm-4.04.21v.227c0 .586.227.8.581.8.31 0 .564-.17.564-.743v-.367c0-.516-.275-.708-.572-.708-.346 0-.573.245-.573.791Z"/>
                                      </svg>
                              </div>
                         </label>
                         <label for="password">
                              <input type="password" name="password" id="password" placeholder="User password" class="input" required="required">
                              <div class="input-icon">
                                     <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-key-fill" viewBox="0 0 16 16">
  										<path d="M3.5 11.5a3.5 3.5 0 1 1 3.163-5H14L15.5 8 14 9.5l-1-1-1 1-1-1-1 1-1-1-1 1H6.663a3.5 3.5 0 0 1-3.163 2zM2.5 9a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
									</svg>
                              </div>
                         </label>
                         <label for="repassword">
                              <input type="password" name="repassword" id="repassword" placeholder="User re-password" class="input" required="required">
                              <div class="input-icon">
                                     <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-key-fill" viewBox="0 0 16 16">
  										<path d="M3.5 11.5a3.5 3.5 0 1 1 3.163-5H14L15.5 8 14 9.5l-1-1-1 1-1-1-1 1-1-1-1 1H6.663a3.5 3.5 0 0 1-3.163 2zM2.5 9a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
									</svg>
                              </div>
                         </label>
                         <button type="submit" class="btn btn-success btn-center mt-4"> Register </button>
                    </form>
                    <p class="align-center mt-3"> You have already account ! <a href="" class="anchor-tag login-open"> Login Now!</a> </p>      
               </div>
          </div>
     </div>



     <!-- book source -- https://www.gutenberg.org/ -->
</body>

</html>