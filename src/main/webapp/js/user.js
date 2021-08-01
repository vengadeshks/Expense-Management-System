/**
 * 
 */
const tableBody = document.getElementById("user-main");
const UserForm =  document.querySelector(".user_add");
const UserID =parseInt(document.getElementById("loggedUserId").value);
var UserRole;
//const 

function redirect(id,location){
	window.location.href=location+"?user_id="+id;
}

//list the user 
// method : GET

function listUsers(){
	
   

	const url = "http://localhost:8001/Expense/api/users/";
	
	
	fetch(url)
	.then(response => response.json())
	.then(data => renderData(data));
	
}
var renderData = (data)=>{
	
    var output = ' ';

		data.users.forEach(user =>{
			
	
       output += `<tr><td>${user.user_name}</td>
                    <td>${user.user_email}</td>
                    <td>${user.user_role}</td>
                     <td><a href="#" class="btn btn-primary" name="edit_user" onclick="redirect(${user.user_id},'cu/addUser.jsp')">Edit</a>
                        <a href="#" class="btn btn-danger"  name="delete_user" onclick="deleteUser(${user.user_id})">Delete</a></td></tr>`;
           
		})
		
		tableBody.innerHTML=output;
	};


//create user
//method : POST

function addUser(){
	var url = "http://localhost:8001/Expense/api/users/addUser";
	


window.fetch(url,{
	method:'POST',
	headers:{
		'Content-Type':'application/json',
		'Accept':'application/json'
	},
	body:JSON.stringify(readFormData())
}).then(response => response.json())
.then(data => {
	window.location.href="../user.jsp";
});

};


function readFormData(){
	var userData = new Object();
	userData.user_name=document.getElementById("name").value;
    userData.user_email=document.getElementById("email").value;
	userData.user_role=document.getElementById("role").value;
	return userData;
}


//delete user
// method: DELETE

function deleteUser(id){
	var url='http://localhost:8001/Expense/api/users/deleteUser';
	var result = confirm("Are you sure? Do you want to delete this user");
	if(result){
		if(UserID!=id){
				fetch(`${url}/${id}`,{
			method :'DELETE',
		    headers: {
			'Accept': 'application/json'
			}
			
			})
	    .then(res => res.json())
        .then(data =>{
	       listUsers();
           if(data.message=="As user has expense ,can't delete"){
	           alert("As user has expense ,can't delete");
            }
	    });
		
		}else{
			alert("You cannot change your own status or role");
		}
		
	
	}
}

//update

function isUpdate(){
	const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('user_id');
    if(id){
	   setForm(id);
  
    }
}

function setForm(id){
	
	var url1 = 'http://localhost:8001/Expense/api/users';
	
	fetch(`${url1}/${id}`)
	.then(response => response.json())
	.then(data => {
		data.users.forEach(user =>{
			
	  document.getElementById("name").value=user.user_name;
      document.getElementById("email").value=user.user_email;
	  document.getElementById("role").selectedIndex =getRoleIndex(user.user_role);
      UserRole = user.user_role;
		})
	});
	 
}

function getRoleIndex(role){
	if(role.toLowerCase()==="admin"){
		return 0;
	}else if(role.toLowerCase()==="approver"){
		return 1;
	}else if(role.toLowerCase()==="submitter"){
		return 2;
	}
}


function updateUser(){
	
	const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('user_id');

	var userData = readFormData();
	userData.user_id= id;
	
	
	if(id==UserID && UserRole!=userData.user_role){
		alert("You cannot change your own status or role");
	}else{
		var url2='http://localhost:8001/Expense/api/users/updateUser';


		fetch(url2,{
			method:'PUT',
			headers:{
				'Content-Type':'application/json',
				'Accept':'application/json'
			},
			body:JSON.stringify(userData)
		}).then(response => response.json())
		.then(data => {
			window.location.href="../user.jsp";
		});	
	
	}
	
		
}

