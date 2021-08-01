const tableBody = document.getElementById("user-main");

//list the user 
// method : GET

 var lab = [];
 var amt = [];
function listAnalytics(){
	
	const url = "http://localhost:8001/Expense/api/currencies/getAnalytics";
	var id = document.getElementById("user_id").value;
	
	fetch(`${url}?user_id=${id}`)
	.then(response => response.json())
	.then(data => {
		renderData(data);
		chart();
	});
	
	 
	
}
var renderData = (data)=>{
	
    var output = ' ';

		data.currencies.forEach(cur =>{
	
     
       output += `<tr><td>${cur.currency_code}</td>
                      <td>${cur.expense_count}</td>
                      <td>${cur.currency_symbol}</td>
                      <td>${cur.expense_total}</td>
                  </tr>`;
       
       lab.push(cur.currency_code);
	   amt.push(parseFloat(cur.expense_total.replace(/[^0-9.]/g,'')));
		})
		
		tableBody.innerHTML=output;
	};


const decimation = {
  enabled: false,
  algorithm: 'min-max',
};

function chart()
{
	
console.log(amt);
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: lab,
        datasets: [{
            label: 'Total Expense Amount',
            data:amt,
            backgroundColor: [
               
                'rgba(54, 162, 235, 0.2)'
                
            ],
            borderColor: [
               
                'rgba(54, 162, 235, 1)',
                
            ],
            borderWidth: 1
        }]
    },
    options: {
		plugins: {
	      decimation: decimation,
	    },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
}

