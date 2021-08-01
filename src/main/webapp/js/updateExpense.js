
function fetchData2(){
	
   //merchant
	const url = "http://localhost:8001/Expense/api/merchants";
	fetch(url)
	.then(response => response.json())
	.then(data => renderMerchant(data));
	
	
	//currency
	const url1 = "http://localhost:8001/Expense/api/currencies";
	
	fetch(url1)
	.then(response => response.json())
	.then(data => renderCurrency(data));
	
	
}
var renderMerchant = (data)=>{
	
       var sel_mer = document.getElementById("merchant");

		data.merchants.forEach(mer =>{
	            var z = document.createElement("option");
				z.setAttribute("value",mer.merchant_id );
				z.innerHTML=mer.merchant_name;
				
			    sel_mer.appendChild(z);
                    
		})
	
	};
var renderCurrency = (data)=>{
	
       var sel_cur = document.getElementById("currency");

		data.currencies.forEach(cur =>{
	            var z = document.createElement("option");
				z.setAttribute("value",cur.currency_id);
				z.innerHTML=cur.currency_code;
				
			    sel_cur.appendChild(z);
                    
		})
	
	};
	

function readFormData2(){
	
	var expense = new Object();
	expense.expense_date=document.getElementById("exp_date").value;
    expense.merchant_id=document.getElementById("merchant").value;
    expense.currency_id=document.getElementById("currency").value;
    var cat = document.getElementsByName("category");
    var desc = document.getElementsByName("expense_description");
    var amt = document.getElementsByName("expense_amount");
    var item_id = document.getElementsByName("expense_item_id");
    var item = [];
    for(i=0;i<desc.length;i++){
	  var exp = new Object();
      exp.category_id = cat[i].value;
      exp.description = desc[i].value;
      exp.amount = amt[i].value.replace(" RS",""); 
      exp.expense_item_id = item_id[i].value;
      item.push(exp);
    }
    expense.item = item;
    return expense;

}

function addItemId(exp_item_id){
	 var mainDiv = document.getElementById("dynamic-fields");
     var item_id = document.createElement("input");
           
	 item_id.setAttribute("type", "hidden");
     item_id.setAttribute("value", `${exp_item_id}`);
      item_id.setAttribute("class", "item_id");
	 item_id.setAttribute("name", "expense_item_id");
     
     mainDiv.appendChild(item_id);
			
}
