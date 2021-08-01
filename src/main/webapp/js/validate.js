
login_form.addEventListener('submit',(e)=>{
	e.preventDefault();
	const url='http://localhost:8001/Expense/api/users/validate';
	var email = document.getElementById('email').value;
	fetch(`${url}/${email}`,{
		method:'POST'
	}).then(response => response.text())
	.then(data => {
		if(data=="Admin" || data=="Submitter"|| data=="Approver"){
			if(data=="Admin"){
				window.location.href="http://localhost:8001/Expense/merchant.jsp";
			}else{
				window.location.href="http://localhost:8001/Expense/expense.jsp";
			}
           
			
		}else{
			alert("User email is not exist");
			window.location.reload();
		}
	});
})