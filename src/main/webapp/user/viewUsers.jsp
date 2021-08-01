<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ===== BOX ICONS ===== -->
        <link href='https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css' rel='stylesheet'>

        <!-- ===== CSS ===== -->
        <link rel="stylesheet" href="../css/style.css">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
   

        <title>Online Expense Management</title>
    </head>
    <body id="body-pd" onload="listUsers()">
        <header class="header" id="header">
            <div class="header__toggle">
                <i class='bx bx-menu' id="header-toggle"></i>
            </div>

        
        </header>

        <div class="l-navbar navbar-dark bg-dark" id="nav-bar">
            <nav class="nav">
                <div>
                    <a href="#" class="nav__logo">
                        <i class='bx bx-layer nav__logo-icon'></i>
                        <span class="nav__logo-name">Zoho Expense</span>
                    </a>

                    <div class="nav__list">
                        <a href="#" class="nav__link active">
                        <i class='bx bx-user nav__icon' ></i>
                            <span class="nav__name">Users</span>
                        </a>

                       
                    </div>
                </div>

                <a href="javascript:void(0)" onclick="window.history.back()" class="nav__link">
                    <i class='bx bx-log-out nav__icon' ></i>
                    <span class="nav__name">Back</span>

                </a>
            </nav>
        </div>

        <div class="content_area">
           <a class="btn btn-primary mt-2 mb-4" style="margin-left:85%" href="addUser.jsp">+ Add User</a>
            <table class="table">
                <thead>
                  <tr>
                    <th scope="col">UserId</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Role</th>
                    <th scope="col">Action</th>
                  </tr>
                </thead>
                <tbody id="user-main">
               
                </tbody>
              </table>
        </div>
        <!--===== MAIN JS =====-->
        <script src="../js/main.js"></script>
        <script src="../js/user.js"></script>
 </body>
</html>