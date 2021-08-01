/**
 * 
 */
const tableBody = document.getElementById("user-main");
const UserForm =  document.querySelector(".user_add");
//const 

function redirect(id,location){
	window.location.href=location+"?categoryt_id="+id;
}

//list the user 
// method : GET

function listCategory(){
	
   

	const url = "http://localhost:8001/Expense/api/categories";
	
	
	fetch(url)
	.then(response => response.json())
	.then(data => renderData(data));
	
}
var renderData = (data)=>{
	
    var output = ' ';

		data.categories.forEach(cat =>{
	
       output += `<tr><td>${cat.category_name}</td>
                    <td>${cat.category_description}</td>
                     <td><a href="#" class="btn btn-primary" name="edit_user" onclick="redirect(${cat.categoryt_id},'cu/addCategory.jsp')">Edit</a>
                        <a href="#" class="btn btn-danger"  name="delete_user" onclick="deleteCategory(${cat.categoryt_id})">Delete</a></td></tr>`;
                    
		})
		tableBody.innerHTML=output;
	};


//create user
//method : POST

function addCategory(){
	var url = "http://localhost:8001/Expense/api/categories/addCategory";

window.fetch(url,{
	method:'POST',
	headers:{
		'Content-Type':'application/json',
		'Accept':'application/json'
	},
	body:JSON.stringify(readFormData())
}).then(response => response.json())
.then(data =>{
	 window.location.href="../category.jsp";
});

};


function readFormData(){
	var category = new Object();
	category.category_name=document.getElementById("catgory_name").value;
    category.category_description=document.getElementById("category_desc").value;
	return category;
}


//delete user
// method: DELETE

function deleteCategory(id){
	var url='http://localhost:8001/Expense/api/categories/deleteCategory';
	var result = confirm("Are you sure? Do you want to delete this category");
	if(result){
		fetch(`${url}/${id}`,{
			method :'DELETE',
		    headers: {
			'Accept': 'application/json'
			}
			
			})
	    .then(res => res.json())
        .then(data =>{
	       listCategory();
				if(data.message=="As Category has expense ,can't delete"){
					alert("As Category has expense ,can't delete");
				}
	    });
		
	}
}

//update

function isUpdate(){
	const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('categoryt_id');
    if(id){
	   setForm(id);
  
    }
}

function setForm(id){
	
	const url = "http://localhost:8001/Expense/api/categories";
	console.log(id);
	
	fetch(`${url}/${id}`)
	.then(response => response.json())
	.then(data => {
		data.categories.forEach(category =>{
	  document.getElementById("catgory_name").value=category.category_name;
      document.getElementById("category_desc").value= category.category_description;

		})
	});
	
}


function updateCategory(){
	
	const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('categoryt_id');

	var category = readFormData();
	category.categoryt_id= id;
	
	
	var url2='http://localhost:8001/Expense/api/categories/updateCategory';


window.fetch(url2,{
	method:'PUT',
	headers:{
		'Content-Type':'application/json',
		'Accept':'application/json'
	},
	body:JSON.stringify(category)
}).then(response => response.json())
.then(data =>{
	 window.location.href="../category.jsp";
});

		
	
}

