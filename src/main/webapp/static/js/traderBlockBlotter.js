$("#removeOrders").click(function(event){
	event.preventDefault();
	var orderIds = $("input:checkbox:checked").map(function(){
		return this.value;
	}).toArray();

	console.log(orderIds);

	$.ajax({
		type:"POST",
		url: "/mock/removeOrders",
		contentType: "application/json",
		data: JSON.stringify(orderIds),
		success: function(result){
			console.log("success");
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.status);
			console.log(textStatus);
			console.log(errorThrown);
		}
	}) 
})
