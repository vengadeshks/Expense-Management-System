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
<body onload="fetchData()">


       
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
            
    	     <h1>Add Expense</h1>
        
          
            <p class="font-italic text-muted mb-0">Information provided below will be used to work with your expenses in zoho expense account.</p>
          
        </div>

        <!-- Registeration Form -->
        <div class="col-md-3 col-lg-5 ml-auto">
        <div class="login-form">
          
    	     <form class="Expense_add" onsubmit="event.preventDefault();addExpense()"  autocomplete="off">
     
      
       
        <h2 class="text-center mb-4">Expense Form</h2>
      
    
     
                    <p class="font-weight-bold">Expense Date</p>
                    <div class="input-group col-lg-6 mb-4">
                        <input id="exp_date" type="date" name="exp_date" class="form-control bg-white border-left-0 border-md" required>
                        <input type="hidden" id="user_id" name="user_id" value="<%= session.getAttribute("user_id") %>"/>
                    </div>

                    <!-- Email Address -->
                    <p class="font-weight-bold">Merchant</p>
                    <div class="input-group col-lg-12 mb-4">
                       
                       <select id="merchant" class="form-control " name="merchant" required>
                        <option value="" selected disabled hidden>Select Merchant</option>
                   
                     </select>
                    </div>  
                   
     
                    <p class="font-weight-bold">Currency</p>
                    <div class="input-group col-lg-6 mb-4">
                       
                    <select id="currency" class="form-control" name="currency" required>
                    <option value="" selected disabled hidden>Select Currency</option>
                  
                     </select>
                    </div>

                    
                    <p class="font-weight-bold">Items</p>
                    <div class="input-group col-lg-12 mb-4 " id="dynamic-fields" >
                    
	                       <div class="row m-2">  
	                        <div class="col-5">
	                         <select id="category" class="form-control" name="category" required>
	                         <option value="" selected disabled hidden>Categories</option>
	                 
	                        </select>
	                        </div>
	                        <div class="col-4">
	                        <input id="expense_description" type="text" name="expense_description" placeholder="Description" class="form-control">
	                        </div>
	                        <div class="col-3">
	                        <input id="expense_amount" type="text" name="expense_amount" placeholder="Amount" class="form-control" required>
	                        </div>
	                       </div>
	                       
	                    
                      </div>  
                      
                      <div class="row">
                       <a class="col-6" href="#" style="text-decoration:none;" onclick="addItem()">+ Add Another Item</a>
                      <a class="col-6" href="#" style="text-decoration:none;" onclick="removeItem()">- Remove Item</a>
                      </div>
                     
                   
    	  <div class="form-group  mt-4">
          <button  type="submit" name="category_create" class="btn btn-primary login-btn btn-block" >Create</button>
          </div>
      
    </form>
   
</div>
        </div>
    </div>
</div>

<script src="../js/expense.js"></script>
<script src="../js/expenseForm.js"></script>



</body>
</html>