$("#removeOrders").click(function(event){
	event.preventDefault();
	var orderIds = $("input:checkbox:checked").map(function(){
		return this.value;
	}).toArray();

	console.log(orderIds);
	
	if (orderIds.length > 0) {
		$.ajax({
			type:"POST",
			url: "/mock/remove-orders",
			contentType: "application/json",
			data: JSON.stringify(orderIds),
			success: function(result){
				console.log("success");
				location.reload();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
				console.log(textStatus);
				console.log(errorThrown);
			}
		}) 
	}
})
