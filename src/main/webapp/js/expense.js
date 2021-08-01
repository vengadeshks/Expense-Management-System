/**
 * 
 */
const tableBody = document.getElementById("user-main");
const UserForm =  document.querySelector(".user_add");
var isDelete = false;
var isNew = false;
var items = [];
var ids = [];
var itemLength=0;
//const 
const urlItemDelete = "http://localhost:8001/Expense/api/lineItem/deleteItem";
function redirect(id,location){
	window.location.href=location+"?expense_id="+id;
}

//list the user 
// method : GET

function listExpense(){

	const url = "http://localhost:8001/Expense/api/expenses";
    var id = document.getElementById("user_id").value;
    console.log(id);
	fetch(`${url}?user_id=${id}`)
	.then(response => response.json())
	.then(data => renderData(data));
	
}
var renderData = (data)=>{
	var role = document.getElementById("user_role").value;
    var output = ' ';
if(role=="Admin"){
		data.expenses.forEach(exp =>{
	 
       output += `<tr>
                    <td>${exp.user_name}</td>
                    <td>${exp.user_role}</td>
                    <td>${exp.expense_date}</td>
                    <td>${exp.merchant_name}</td>
                    <td>${exp.expense_total}</td>
                    <td><a href="#" class="btn btn-primary" name="edit_user" onclick="redirect(${exp.expense_id},'cu/editExpense.jsp')">Edit</a>
                        <a href="#" class="btn btn-danger"  name="delete_user" onclick="deleteExpense(${exp.expense_id})">Delete</a></td></tr>`;
                    
		})
		tableBody.innerHTML=output;
	}else{
		data.expenses.forEach(exp =>{
	 
       output += `<tr>
                   
                    <td>${exp.expense_date}</td>
                    <td>${exp.merchant_name}</td>
                    <td>${exp.expense_total}</td>
                     <td><a href="#" class="btn btn-primary" name="edit_user" onclick="redirect(${exp.expense_id},'cu/editExpense.jsp')">Edit</a>
                        <a href="#" class="btn btn-danger"  name="delete_user" onclick="deleteExpense(${exp.expense_id})">Delete</a></td></tr>`;
                    
		})
		tableBody.innerHTML=output;
	}
	}
		


//create user
//method : POST


function addExpense(){
	var url = "http://localhost:8001/Expense/api/expenses/addExpense";
    var id = document.getElementById("user_id").value;
   
		fetch(`${url}?user_id=${id}`,{
			method:'POST',
			headers:{
				'Content-Type':'application/json',
				'Accept':'application/json'
			},
			body:JSON.stringify(readFormData())
		}).then(response => response.json())
		.then(data =>{
			 window.location.href="../expense.jsp";
		});	
}


function readFormData(){
	var expense = new Object();
	expense.expense_date=document.getElementById("exp_date").value;
    expense.merchant_id=document.getElementById("merchant").value;
    expense.currency_id=document.getElementById("currency").value;
    var cat = document.getElementsByName("category");
    var desc = document.getElementsByName("expense_description");
    var amt = document.getElementsByName("expense_amount");
    var item = [];
    for(i=0;i<desc.length;i++){
	  var exp = new Object();
      exp.category_id = cat[i].value;
      exp.description = desc[i].value;
      exp.amount = amt[i].value;
      item.push(exp);
    }
    expense.item = item;
    console.log(expense);
    return expense;

}


//delete user
// method: DELETE

function deleteExpense(id){
	var url='http://localhost:8001/Expense/api/expenses/deleteExpense';
	var result = confirm("Are you sure? Do you want to delete this expense");
	var u_id = document.getElementById("user_id").value;

	if(result){
		fetch(`${url}/${id}?user_id=${u_id}`,{
			method :'DELETE',
		    headers: {
			'Accept': 'application/json'
			}
			
			})
	    .then(res => res.json())
        .then(data=>{
	       listExpense();
	    });	
	}
}

//update

function isUpdate(){
	const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('expense_id');
    if(id){
	   setForm(id);
  
    }
};


function setForm(id) {
	fetchData2();
	const url = "http://localhost:8001/Expense/api/expenses";
	var u_id = document.getElementById("user_id").value;
	 fetch(`${url}/${id}?user_id=${u_id}`)
	.then(response => response.json())
	.then(data => {
		data.expenses.forEach(exp =>{
	document.getElementById("exp_date").value= exp.expense_date;
    document.getElementById("merchant").value= exp.merchant_id;
    document.getElementById("currency").value= exp.currency_id;
	console.log(exp.currency_id+"Currency");
    exp.item.forEach(it=>{
	    addItem2(it.category_id);
        addItemId(it.expense_item_id); 
        itemLength++;
    })

    var cat = document.getElementsByName("category");
    var desc = document.getElementsByName("expense_description");
    var amt = document.getElementsByName("expense_amount");
    
    
   i=0;
   exp.item.forEach(it=>{
	  console.log(it.category_id+"is Category_id");
      desc[i].value =it.description;  
      amt[i].value =it.amount.replace(/[^0-9.]/g,'');
      i++;

    })
		})
	});
	
}
  function readNewData(j){
	    var item = new Object();
	    var catNew = document.getElementsByName("categoryNew");
        var descNew = document.getElementsByName("expense_descriptionNew");
        var amtNew = document.getElementsByName("expense_amountNew"); 
        item.category_id=catNew[j].value;
        item.amount=amtNew[j].value;
        item.description=descNew[j].value;
        console.log(item);
        return item;
}
 var storeData = (data)=>{
	const urlParams = new URLSearchParams(window.location.search);
	    var id = urlParams.get('expense_id');
	
	data.item.forEach(it=>{
		items.push(it.expense_item_id);
	})
	console.log(items);
	console.log("called delete item");
	 var item_id = document.getElementsByName("expense_item_id");    

	
	item_id.forEach(id=>{
		ids.push(parseInt(id.value));
	})
	console.log("ids "+ids);
	
	items.forEach(item=>{
		var bool = ids.includes(item);
		console.log(bool);
		if(!bool){
		fetch(`${urlItemDelete}/${item}?expense_id=${id}`,{
			method :'DELETE',
		    headers: {
			'Accept': 'application/json'
			}
			
			})
	    .then(res => res.json())
        .then(data=>{
	       console.log("data of delete item"+data.message);
	    })
		};
		console.log("item id from db "+item);
		
	})
}


  function updateExpense(){
		const urlParams = new URLSearchParams(window.location.search);
	    var id = urlParams.get('expense_id');
        var item_id = document.getElementsByName("expense_item_id");    
            itLength=0;
			item_id.forEach(id=>{
				itLength++;
			})
			
	if(isNew){
		
		var catNew = document.getElementsByName("categoryNew");
       
//adding new items
        for(var i=0;i<catNew.length;i++){
		var url = "http://localhost:8001/Expense/api/lineItem/addItem";
   
   
		fetch(`${url}?expense_id=${id}`,{
			method:'POST',
			headers:{
				'Content-Type':'application/json',
				'Accept':'application/json'
			},
			body:JSON.stringify(readNewData(i))
		}).then(response => response.json())
		
		
		}
	}
	console.log(itLength);
	console.log(itemLength);
	if(isDelete && itLength<itemLength){
    const urlItem = "http://localhost:8001/Expense/api/lineItem";
   
	fetch(`${urlItem}?expense_id=${id}`)
	.then(response => response.json())
	.then(data => {
		storeData(data);
		setTimeout(function(){
			window.location.href="../expense.jsp";
		},500);
	})
	
	}
	
	var u_id = document.getElementById("user_id").value;
	var expense = readFormData2();
	expense.expense_id= id;
	
	console.log(expense.expense_date);
	console.log(expense.merchant_id);
	console.log(expense.currency_id);
	console.log(expense.item);
	
	var url2='http://localhost:8001/Expense/api/expenses/updateExpense';

	fetch(`${url2}?user_id=${u_id}`,{
		method:'PUT',
		headers:{
			'Content-Type':'application/json',
			'Accept':'application/json'
		},
		body:JSON.stringify(expense)
	}).then(response => response.json())
	.then(data => {
		setTimeout(function(){
			window.location.href="../expense.jsp";
		},500);
	});

		
}
