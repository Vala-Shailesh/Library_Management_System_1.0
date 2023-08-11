<%@page import="com.xadmin.libraryManagement.bean.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.xadmin.libraryManagement.bean.BorrowBook"%>
<%@page import="com.xadmin.libraryManagement.dao.BookBorrowManagementDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
	
     <!-- boostarp addition -->
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous" defer></script>

     <!-- Custom external styling -->
     <link rel="stylesheet" type="text/css" href="./resources/CSS/styleSheet.css">
     <link rel="stylesheet" type="text/css" href="./resources/CSS/services.css">  
     
     <!-- Script -->
     <script type="text/javascript" src="./resources/JS/bookControl.js" defer></script>
   	 <script type="text/javascript" src="./resources/JS/notificationBox.js" type="module" defer></script>
     
	<title>Insert title here</title>
	
</head>
<body>

	<!-- header -->
     <nav class="navbar navbar-expand-sm navbar-dark bg-dark text-lg">
          <div class="container-fluid w-90">
               <a class="navbar-brand h1 text-light text-xxl" href="./home"> Book's Library </a>
               <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar"
                    aria-controls="navbar">
                    <span class="navbar-toggler-icon"></span>
               </button>
               <div class="collapse navbar-collapse justify-content-end" id="navbar">
                    <div class="navbar-nav">
                         <a class="nav-link" aria-current="page" href="./home"> Home </a>
                         <a class="nav-link" aria-current="page" href="./myBook"> My books </a>
                         <a class="nav-link active" href="./services"> Services  </a>
                         <a class="nav-link" aria-current="page" href="./account"> Account </a>
                    </div>
               </div>
          </div>
     </nav>
     
	     <!-- Notification popup box -->
	     <%
			String bookReturnSuccessfully = "none";
			String bookReturnFail = "none";
			
			if( session.getAttribute("returnBookStatus") != null ){
			
				String bookReturnStatus = (String)session.getAttribute("returnBookStatus");
				
				// out.println("seession have some value...! " + bookReturnStatus);
				
				if( bookReturnStatus.equals("true") ){
					bookReturnSuccessfully = "block";
				}	
				else{
					bookReturnFail = "block";
				}
			} 
			session.removeAttribute("returnBookStatus");
	
		 %>
		
		<!-- popup box for successfully update data -->
	     <div class="container register-box box-shadow-1 p-4 rounded-2" style="display : <%= bookReturnSuccessfully %>; " >
			<h3 class="text-success"> Book Return Successfully </h3> 
			<hr>
			<p> I hope you like book and Our Library services  </p>
			<button type="button" class="btn btn-end btn-success registerBox-close-btn w-25"> Thank You  </button>
		</div>
		 <div class="container register-box box-shadow-1 p-4 rounded-2" style="display : <%= bookReturnFail %>; " >
			<h3 class="text-danger"> Book Return Fail </h3> 
			<hr>
			<p> Some unexpected error are occur in book return process </p>
			<button type="button" class="btn btn-end btn-danger registerBox-close-btn w-25"> Try again </button>
		</div>
		
     
	<%
		int userID = (int)session.getAttribute("id");
	
		BorrowBook bb = new BorrowBook(userID);
	
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		BookBorrowManagementDao bookBorrowed = new BookBorrowManagementDao();
		
	    bookList = bookBorrowed.getAllBookISBNByUserID(bb);
	    
	    String footerVisibility = "footer";
	    
	    if( bookList.size() > 3 ){
	    	footerVisibility = "";	    	
	    }
	    
	%>
	
	<div class="container mt-5">
	 	<h1 class="align-center"> Borrow Book List </h1>
	</div>
	
	<!-- Read book -->
     <div class="container-fiuld container-center w-90  p-0 m-0">
     	 <div class="container-fluid row justify-content-center w-100 p-4 m-0">    	 	
     	 	 <% for( int s = 0; s < bookList.size(); s++ ){ %>
     	 	 	<div class="col-lg-3 col-md-4 col-sm-6 row p-0 my-4 mx-2 rounded-3 book-pointer">
     	 	 		<div class="container-center box-shadow-1 mt-3 p-0" style="width: 200px; height: 250px;">
		     	 		<img alt="Books Images.." src="<%= "http://localhost:8085/Library_Management_System_1.0/resources/Images/Book_Cover/"  + bookList.get(s).getBookCover() %>"  width="200px" height="250px" class="rounded-2" /> 					
     	 	 		</div>
     	 	 		<form action="./returnBook" method="post" id="returnBook_<%= s%>">
     	 	 			<input type="hidden" name="bookISBN" value="<%= bookList.get(s).getISBN() %>"  />
     	 	 			<input type="hidden" name="bookTitle" value="<%= bookList.get(s).getTitle() %> "  />
     	 	 			<input type="hidden" name="bookAuthor" value="<%= bookList.get(s).getAuthor() %>"  />
     	 	 			
     	 	 		</form>
	     	 		<p class="align-center mt-3"> <b> Title : </b> <%= bookList.get(s).getTitle() %> </p>	
					<p class="align-center mt-1"> <b> Author : </b> <%= bookList.get(s).getAuthor() %> </p>	 
					<div class="container w-75 d-flex justify-content-evenly mt-2">
						<button type="submit" form="returnBook_<%= s%>" class="btn btn-warning m-1"> Return </button> 
						<a href=" <%= "http://localhost:8085/Library_Management_System_1.0/resources/PDF/" + bookList.get(s).getBookPdf() %>" target="_blank" class="btn btn-info m-1"> Download </a>
					</div>
     	 		</div>		
     	 	 <% } %>  	 
     	 </div>
   	   	 <div class="row w-100 p-3">
             <button type="button" class="btn btn-dark btn-center col-1" id="viewMoreBook" style="min-width: 120px;"> View More </button>
         </div>
     </div>
     
     <!-- footer -->
     <div class="container-fluid w-100 p-5 box-shadow-1 mt-5 <%= footerVisibility %>" >
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
     </div>

</body>
</html>