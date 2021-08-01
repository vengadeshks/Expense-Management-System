<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html><html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Online Expense Management</title>


    

    <!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >

    <style>
	
    

body {
  display: flex;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
  height: 100%;
}

.form-signin {
  width: 100%;
  max-width: 500px;
  padding: 15px;
  margin: auto;
}
.text{
    width: 100%;
  max-width: 450px;
 
}


    </style>

  </head>
  <body class="text-center">
    
    <div class="container-fluid col-4 mt-5 text">
    <img src="images/new.jpeg" />
     <h2 class="mt-3">Travel and expense management for growing businesses</h2></br>
     <p class="lead">Streamline T&E management from end to end. This Expense has powerful features to handle travel and expenses, control spending, and customize and automate business tasks.</p>
  </div>
<main class="form-signin mt-5 col-12">
  
  <form name="login_form" autocomplete="off">
    <img class="mb-4" src="images/1.png" alt="" width="90" height="70">
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

    <div class="form-floating">
      <input type="email" name="email" class="form-control" id="email" placeholder="name@example.com">
      <label for="floatingInput">Email address</label>
    </div>
    <br> <br>
    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
   <!-- <p class="mt-5 mb-3 text-muted text-center small">Don't have an account? <a href="user/addUser.jsp">Sign up here!</a></p>
  --> 
    </form>
</main>

<script type="text/javascript" src="js/validate.js">

</script>
    
  </body>
</html>