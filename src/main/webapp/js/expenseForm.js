function addItem(){

                var mainDiv = document.getElementById("dynamic-fields");
                var div = document.createElement("div");
                div.setAttribute("class", "row m-2");
                var div1 = document.createElement("div");
                div1.setAttribute("class", "col-5");
                var div2 = document.createElement("div");
                div2.setAttribute("class", "col-4");
                var div3 = document.createElement("div");
                div3.setAttribute("class", "col-3");

                //element 1
                var cat = document.createElement("select");
				cat.setAttribute("name", "category");
				cat.setAttribute("class", "form-control category");
				
				//option 1
							
				var z = document.createElement("option");
				z.setAttribute("value", "");
				z.setAttribute("selected","true");
				z.setAttribute("disabled","true");
				z.setAttribute("hidden","true");
				var t = document.createTextNode("Categories");
				z.appendChild(t);
				
				cat.appendChild(z);

                //element 2
                var desc = document.createElement("input");
                desc.setAttribute("id", "expense_description");
				desc.setAttribute("type", "text");
				desc.setAttribute("name", "expense_description");
				desc.setAttribute("placeholder", "Description");
				desc.setAttribute("class", "form-control");
				//element 3
				var amt = document.createElement("input");
                amt.setAttribute("id", "expense_amount");
				amt.setAttribute("type", "text");
				amt.setAttribute("name", "expense_amount");
				amt.setAttribute("placeholder", "Amount");
				amt.setAttribute("class", "form-control");
				
				
				div1.appendChild(cat);
				div2.appendChild(desc);
				div3.appendChild(amt);
				
                
                div.appendChild(div1);
				div.appendChild(div2);
			    div.appendChild(div3);
                mainDiv.appendChild(div);
                appendCategory(cat);

}

function addItem3(){
	           isNew =true;

                var mainDiv = document.getElementById("dynamic-fields");
                var div = document.createElement("div");
                div.setAttribute("class", "row m-2");
                var div1 = document.createElement("div");
                div1.setAttribute("class", "col-5");
                var div2 = document.createElement("div");
                div2.setAttribute("class", "col-4");
                var div3 = document.createElement("div");
                div3.setAttribute("class", "col-3");

                //element 1
                var cat = document.createElement("select");
				cat.setAttribute("name", "categoryNew");
				cat.setAttribute("class", "form-control category");
				
				//option 1
				
				var z = document.createElement("option");
				z.setAttribute("value", "");
				z.setAttribute("selected","true");
				z.setAttribute("disabled","true");
				z.setAttribute("hidden","true");
				var t = document.createTextNode("Categories");
				z.appendChild(t);
				
				cat.appendChild(z);

                //element 2
                var desc = document.createElement("input");
                desc.setAttribute("id", "expense_descriptionNew");
				desc.setAttribute("type", "text");
				desc.setAttribute("name", "expense_descriptionNew");
				desc.setAttribute("placeholder", "Description");
				desc.setAttribute("class", "form-control");
				//element 3
				var amt = document.createElement("input");
                amt.setAttribute("id", "expense_amountNew");
				amt.setAttribute("type", "text");
				amt.setAttribute("name", "expense_amountNew");
				amt.setAttribute("placeholder", "Amount");
				amt.setAttribute("class", "form-control");
				
				
				div1.appendChild(cat);
				div2.appendChild(desc);
				div3.appendChild(amt);
				
                
                div.appendChild(div1);
				div.appendChild(div2);
			    div.appendChild(div3);
                mainDiv.appendChild(div);
                appendCategory(cat);

}


          

function removeItem(){
	 var mainDiv = document.getElementById("dynamic-fields");
     var divs = mainDiv.getElementsByClassName("row m-2");
     if(divs.length>1){
	         mainDiv.removeChild(divs[(divs.length)-1]);
     }
}
function removeItem2(){
	 var mainDiv = document.getElementById("dynamic-fields");
     var divs = mainDiv.getElementsByClassName("row m-2");
     var input = mainDiv.getElementsByClassName("item_id");
     if(divs.length>1){
	if(divs.length==input.length){
             mainDiv.removeChild(input[(input.length)-1]);
             isDelete = true;
     }
	        console.log(divs.length);
            console.log(input.length);
	         mainDiv.removeChild(divs[(divs.length)-1]);
      }
}

function addItem2(id){

                var mainDiv = document.getElementById("dynamic-fields");
                var div = document.createElement("div");
                div.setAttribute("class", "row m-2");
                var div1 = document.createElement("div");
                div1.setAttribute("class", "col-5");
                var div2 = document.createElement("div");
                div2.setAttribute("class", "col-4");
                var div3 = document.createElement("div");
                div3.setAttribute("class", "col-3");

                //element 1
                var cat = document.createElement("select");
				cat.setAttribute("name", "category");
				cat.setAttribute("class", "form-control category");
			
			    
				//option 1
							
				var z = document.createElement("option");
				z.setAttribute("value", "");
				z.setAttribute("selected","true");
				z.setAttribute("disabled","true");
				z.setAttribute("hidden","true");
				var t = document.createTextNode("Categories");
				z.appendChild(t);
				
				cat.appendChild(z);

                //element 2
                var desc = document.createElement("input");
                desc.setAttribute("id", "expense_description");
				desc.setAttribute("type", "text");
				desc.setAttribute("name", "expense_description");
				desc.setAttribute("placeholder", "Description");
				desc.setAttribute("class", "form-control");
				//element 3
				var amt = document.createElement("input");
                amt.setAttribute("id", "expense_amount");
				amt.setAttribute("type", "text");
				amt.setAttribute("name", "expense_amount");
				amt.setAttribute("placeholder", "Amount");
				amt.setAttribute("class", "form-control");
				
				
				div1.appendChild(cat);
				div2.appendChild(desc);
				div3.appendChild(amt);
				
                
                div.appendChild(div1);
				div.appendChild(div2);
			    div.appendChild(div3);
                mainDiv.appendChild(div);
                appendCategory2(cat,id);

}

 



function fetchData(){
	
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
	
	//category
	const url2 = "http://localhost:8001/Expense/api/categories";
	
	fetch(url2)
	.then(response => response.json())
	.then(data => renderCategory(data));
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
var renderCategory = (data)=>{
	
       var sel_cat = document.getElementById("category");

		data.categories.forEach(cat =>{
			
				  var z = document.createElement("option");
				z.setAttribute("value",cat.categoryt_id );
				z.setAttribute("name","category" );
				z.innerHTML=cat.category_name ;
				
			    sel_cat.appendChild(z);
		
			
                    
		})
	
	};
	
function appendCategory(cate){
	
	//category
	const url2 = "http://localhost:8001/Expense/api/categories";
	
	fetch(url2)
	.then(response => response.json())
	.then(data => {
	
		data.categories.forEach(cat =>{
			
				  var z = document.createElement("option");
				z.setAttribute("value",cat.categoryt_id );
				z.innerHTML=cat.category_name ;
			    cate.appendChild(z);
		
		})
	});
}


	  	
function appendCategory2(cate,id){
	
	//category
	const url2 = "http://localhost:8001/Expense/api/categories";
	
	fetch(url2)
	.then(response => response.json())
	.then(data => {
	
		data.categories.forEach(cat =>{
			
				  var z = document.createElement("option");
				z.setAttribute("value",cat.categoryt_id );
				z.innerHTML=cat.category_name ;
			    cate.appendChild(z);
		
		})
	cate.value= id;
	});
}


	                    