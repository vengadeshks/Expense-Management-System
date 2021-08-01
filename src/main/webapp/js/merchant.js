/**
 * 
 */
const tableBody = document.getElementById("user-main");
const UserForm =  document.querySelector(".user_add");
//const 

function redirect(id,location){
	window.location.href=location+"?merchant_id="+id;
}

//list the user 
// method : GET

function listMerchant(){
	
   

	const url = "http://localhost:8001/Expense/api/merchants";
	
	
	fetch(url)
	.then(response => response.json())
	.then(data => renderData(data));
	
}
var renderData = (data)=>{
	
    var output = ' ';

		data.merchants.forEach(mer =>{
	
       output += `<tr><td>${mer.merchant_name}</td>
                    <td>${mer.merchant_description}</td>
                    <td><a href="#" class="btn btn-primary" name="edit_user" onclick="redirect(${mer.merchant_id},'cu/addMerchant.jsp')">Edit</a>
                        <a href="#" class="btn btn-danger"  name="delete_user" onclick="deleteMerchant(${mer.merchant_id})">Delete</a></td></tr>`;
                    
		})
		tableBody.innerHTML=output;
	};


//create user
//method : POST

function addMerchant(){
	var url = "http://localhost:8001/Expense/api/merchants/addMerchant";

window.fetch(url,{
	method:'POST',
	headers:{
		'Content-Type':'application/json',
		'Accept':'application/json'
	},
	body:JSON.stringify(readFormData())
}).then(response => response.json())
.then(data =>{
	 window.location.href="../merchant.jsp";
});

};


function readFormData(){
	var merchant = new Object();
	merchant.merchant_name=document.getElementById("merchant_name").value;
    merchant.merchant_description=document.getElementById("merchant_desc").value;
	return merchant;
}


//delete user
// method: DELETE

function deleteMerchant(id){
	var url = "http://localhost:8001/Expense/api/merchants/deleteMerchant";
	var result = confirm("Are you sure? Do you want to delete this merchant");
	if(result){
		fetch(`${url}/${id}`,{
			method :'DELETE',
		    headers: {
			'Accept': 'application/json'
			}
			
			})
	    .then(res => res.json())
        .then(data=>{
	       listMerchant();
           if(data.message=="As merchant has expense ,can't delete"){
	            alert("As merchant has expense ,can't delete");
            }
	    });
		
	}
}

//update

function isUpdate(){
	const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('merchant_id');
    if(id){
	   setForm(id);
  
    }
}

function setForm(id){
	
	const url = "http://localhost:8001/Expense/api/merchants";

	fetch(`${url}/${id}`)
	.then(response => response.json())
	.then(data => {
		data.merchants.forEach(merchant =>{
	  document.getElementById("merchant_name").value=merchant.merchant_name;
      document.getElementById("merchant_desc").value= merchant.merchant_description;

		})
	});
	
}


function updateMerchant(){
	
	const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('merchant_id');

	var merchant = readFormData();
	merchant.merchant_id= id;
	
	var url2='http://localhost:8001/Expense/api/merchants/updateMerchant';


window.fetch(url2,{
	method:'PUT',
	headers:{
		'Content-Type':'application/json',
		'Accept':'application/json',
	},
	body:JSON.stringify(merchant)
}).then(response => response.json())
.then(data =>  {
	window.location.href="../merchant.jsp";
});
	
}
