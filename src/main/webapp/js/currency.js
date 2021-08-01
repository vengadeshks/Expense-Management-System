/**
 * 
 */
const tableBody = document.getElementById("user-main");
const UserForm =  document.querySelector(".user_add");
//const 

function redirect(id,location){
	window.location.href=location+"?currency_id="+id;
}

//list the user 
// method : GET

function listCurrency(){

	const url = "http://localhost:8001/Expense/api/currencies";
	
	fetch(url)
	.then(response => response.json())
	.then(data => renderData(data));
	
}
var renderData = (data)=>{
	
    var output = ' ';

		data.currencies.forEach(cur =>{
	
       output += `<tr><td>${cur.currency_code}</td>
                    <td>${cur.currency_symbol}</td>
                     <td><a href="#" class="btn btn-primary" name="edit_user" onclick="redirect(${cur.currency_id},'cu/addCurrency.jsp')">Edit</a>
                        <a href="#" class="btn btn-danger"  name="delete_user" onclick="deleteCurrency(${cur.currency_id})">Delete</a></td></tr>`;
                    
		})
		tableBody.innerHTML=output;
	};


//create user
//method : POST

function addCurrency(){
	var url = "http://localhost:8001/Expense/api/currencies/addCurrency";

window.fetch(url,{
	method:'POST',
	headers:{
		'Content-Type':'application/json',
		'Accept':'application/json'
	},
	body:JSON.stringify(readFormData())
}).then(response => response.json())
.then(data =>{
	 window.location.href="../currency.jsp";
});

};


function readFormData(){
	var currency = new Object();
	currency.currency_code=document.getElementById("currency_code").value;
    currency.currency_symbol=document.getElementById("currency_symbol").value;
	return currency;
}


//delete user
// method: DELETE

function deleteCurrency(id){
	var url='http://localhost:8001/Expense/api/currencies/deleteCurrency';
	var result = confirm("Are you sure? Do you want to delete this currency");
	if(result){
		fetch(`${url}/${id}`,{
			method :'DELETE',
		    headers: {
			'Accept': 'application/json'
			}
			
			})
	    .then(res => res.json())
        .then(data=>{
	       listCurrency() ;
           if(data.message=="As currency has expense ,can't delete"){
	           alert("As currency has expense ,can't delete");
           }
	    });
		
	}
}

//update

function isUpdate(){
	const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('currency_id');
    if(id){
	   setForm(id);
  
    }
}

function setForm(id){
	
	const url = "http://localhost:8001/Expense/api/currencies";
	console.log(id);
	
	fetch(`${url}/${id}`)
	.then(response => response.json())
	.then(data => {
		data.currencies.forEach(currency =>{
	document.getElementById("currency_code").value=currency.currency_code;
    document.getElementById("currency_symbol").value=currency.currency_symbol;
	 

		})
	});
	
}


function updateCurrency(){
	
	const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('currency_id');

	var currency = readFormData();
	currency.currency_id= id;
	
	
	var url2='http://localhost:8001/Expense/api/currencies/updateCurrency';


window.fetch(url2,{
	method:'PUT',
	headers:{
		'Content-Type':'application/json',
		'Accept':'application/json'
	},
	body:JSON.stringify(currency)
}).then(response => response.json())
.then(data =>  {
	window.location.href="../currency.jsp";
});
		
	
}

