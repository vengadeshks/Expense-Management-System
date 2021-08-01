<% 
   if(session.getAttribute("user_id")==null){
       response.sendRedirect("login.jsp");
    }   
 %>

<jsp:include page="include/header.jsp" />
 <body  id="body-pd" class="body-pd" onload="listExpense()">
     <input type="hidden" id="user_id" name="user_id" value="<%= session.getAttribute("user_id") %>"/>
      <input type="hidden" id="user_role" name="user_id" value="<%= session.getAttribute("user_role") %>"/>

 
        <header class="header body-pd" id="header">
            <div class="header__toggle">
                <i class='bx bx-menu' id="header-toggle"></i>
            </div>
            <div class="header_title">
                <h5>Expense Management System</h5>
             </div>
             <div class="">
               
            </div>
        </header>

        <div class="l-navbar show  navbar-dark bg-dark" id="nav-bar">
            <nav class="nav">
                <div>
                    <a href="#" class="nav__logo">
                        <i class='bx bx-layer nav__logo-icon'></i>
                        <span class="nav__logo-name"><%= session.getAttribute("user_name") %></span>
                    </a>

                    <div class="nav__list">
                          <% if(session.getAttribute("user_role").equals("Admin")) {  %>
                    
                    	<a href="merchant.jsp" class="nav__link">
                        <i class='bx bx-grid-alt nav__icon' ></i>
                            <span class="nav__name">Merchants</span>
                        </a>

                        <a href="currency.jsp" class="nav__link">
                            <i class='bx bx-user nav__icon' ></i>
                            <span class="nav__name">Currencies</span>
                        </a>
                        
                        <a href="category.jsp" class="nav__link">
                            <i class='bx bx-folder nav__icon' ></i>
                            <span class="nav__name">Categories</span>
                        </a>
                        <a href="user.jsp" class="nav__link" onclick="listUsers()">
                        <i class='bx bx-user nav__icon' ></i>
                        <span class="nav__name">Users</span>
                        </a>
                        
                        <% } %>
                        
                        <a href="expense.jsp" class="nav__link">
                            <i class='bx bx-bookmark nav__icon' ></i>
                            <span class="nav__name">Expenses</span>
                        </a>
                       
                         <a href="analytics.jsp" class="nav__link">
                            <i class='bx bx-bar-chart-alt-2 nav__icon' ></i>
                            <span class="nav__name">Analytics</span>
                        </a>

                       
                    </div>
                </div>
                   <a href="javascript:void(0)" onclick="logout()" class="nav__link">
                    <i class='bx bx-log-out nav__icon' ></i>
                    <span class="nav__name">logout</span>
                   </a>
            </nav>
        </div>

<!-- ------------------------------------------------------------------------- -->
<div class="content_area">
           <a class="btn btn-primary mt-2 mb-4" style="margin-left:85%" href="cu/addExpense.jsp">+ Add Expense</a>
            <table class="table">
                <thead>
                  <tr>
                  <% if(session.getAttribute("user_role").equals("Admin")){  %>
                	  <th scope="col">User Name</th>
                      <th scope="col">User Role</th>
                 <% }  %>
                   
                    <th scope="col">Date</th>
                    <th scope="col">Merchant Name</th>
                    <th scope="col">Total</th>
                    <th scope="col">Action</th>
                  </tr>
                </thead>
                <tbody id="user-main">
               
                </tbody>
              </table>
        </div>
        <!--=====  JS =====-->
       
        <script src="js/expense.js"></script>
<jsp:include page="include/footer.jsp" />


