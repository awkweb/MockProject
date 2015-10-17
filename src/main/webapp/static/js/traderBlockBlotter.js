$("#removeOrders").click(function(event){
	event.preventDefault();
	var orderIds = $("input:checkbox:checked").map(function(){
		return this.value;
	}).toArray();
	
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
});

$("#cancelBlocks").click(function(event){
	event.preventDefault();
	var blockIds = $("input:checkbox:checked").map(function(){
		return this.value;
	}).toArray();
	
	if (blockIds.length > 0) {
		$.ajax({
			type:"POST",
			url: "/mock/cancel-blocks",
			contentType: "application/json",
			data: JSON.stringify(blockIds),
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
});
