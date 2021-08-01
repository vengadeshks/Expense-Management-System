<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
           <meta charset="ISO-8859-1">
           <!-- ===== BOX ICONS ===== -->
	        <link href='https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css' rel='stylesheet'>
	
	         <!-- ===== CSS ===== -->
	        <link rel="stylesheet" href="../css/style.css">
	        
	        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	  
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
	        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	        
	        <title>Online Expense Management</title>
	      
</head>
<body onload="isUpdate()">


       
        <div class="l-navbar navbar-dark bg-dark" id="nav-bar">
            <nav class="nav">
                <div>
                     <a href="javascript:void(0)" onclick="window.history.back()" class="nav__link">
                    <i class='bx bx-log-out nav__icon' ></i>
                    <span class="nav__name">Back</span>
                   </a>
                </div>

              
            </nav>
        </div>


<div class="container">
    <div class="row align-items-center my-1">
        <!-- For Demo Purpose -->
        <div class="col-md-5 pr-lg-5 mb-5 mb-md-0">
            <img src="../images/thumbs-up.png" alt="" class="img-fluid mb-3 d-none d-md-block">
            <%
    if (request.getParameter("categoryt_id") == null) {%>
    	     <h1>Add Category</h1>
         <%} else {%>
    	    <h1>Update Category</h1>
          <%}
       %>
            <p class="font-italic text-muted mb-0">Information provided below will be used to sign in to your zoho expense account.</p>
          
        </div>

        <!-- Registeration Form -->
        <div class="col-md-3 col-lg-5 ml-auto">
        <div class="login-form">
          <%
    if (request.getParameter("categoryt_id") == null) {%>
    	     <form class="Category_add" onsubmit="event.preventDefault();addCategory()"  autocomplete="off">
         <%} else {%>
    	     <form class="Category_update" onsubmit="event.preventDefault();updateCategory()"  autocomplete="off">
          <%}
       %>
      
       
        <h2 class="text-center mb-4">Category Form</h2>
      
       <!-- Last Name -->
     
                    <p class="font-weight-bold">Category Name</p>
                    <div class="input-group col-lg-6 mb-4">
                       
                        <input id="catgory_name" type="text" name="catgory_name" placeholder="Enter Category Name" class="form-control bg-white border-left-0 border-md" required>
                    </div>

                    <!-- Email Address -->
                    <p class="font-weight-bold">Description</p>
                    <div class="input-group col-lg-12 mb-4">
                       
                        <input id="category_desc" type="text" name="category_desc" placeholder="Optional" class="form-control bg-white border-left-0 border-md">
                    </div>  
                   


       <%
    if (request.getParameter("categoryt_id") == null) {%>
    	  <div class="form-group  mx-auto">
          <button type="submit" name="category_create" class="btn btn-primary login-btn btn-block">Add</button>
          </div>
         <%} else {%>
    	  <div class="form-group  mx-auto">
          <button type="submit" name="category_update" class="btn btn-primary login-btn btn-block">Update</button>
           </div>
          <%}
       %>
      
		
    </form>
   
</div>
        </div>
    </div>
</div>

<script src="../js/category.js"></script>


</body>
</html>