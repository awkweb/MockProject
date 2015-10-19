$("#createblockbutton").click(function(event){
	event.preventDefault();
	var selectedOrders = $("input:checkbox:checked").map(function(){
		return this.value;
	}).toArray();
	
	if (selectedOrders.length > 0) {
		$.ajax({
			type:"POST",
			url: "/mock/create-block",
			contentType: "application/json",
			data: JSON.stringify(selectedOrders),
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
$("#addtoblockbutton").click(function(event){
	

});

$("#addtoblockbutton").click(function(event){
	event.preventDefault();
	var selectedOrders = $("input:checkbox:checked").map(function(){
		return this.value;
	}).toArray();
	
	if (selectedOrders.length > 0) {
		$.ajax({
			type:"POST",
			url: "/mock/add-block",
			contentType: "application/json",
			data: JSON.stringify(selectedOrders),
			success: function(result){
				console.log("success");
				location.reload();
				location.href = "http://localhost:8080/mock/select-block";
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
				console.log(textStatus);
				console.log(errorThrown);
				alert("Error: There are no blocks to add this to");
				location.reload();
			}
		}) 
	}
});
$("input:radio[name=radioOption]").click(function(){
    var value = $(this).attr("id");
    alert(value);
})
$("#selectaddblockbutton").click(function(event){
	
	event.preventDefault();
	var selectedOrders = $("input:radio[name=radioOption]:checked").attr("id");
	alert(selectedOrders);
	
	if (selectedOrders.length > 0) {
		$.ajax({
			type:"POST",
			url: "/mock/add-block",
			contentType: "application/json",
			data: JSON.stringify(selectedOrders),
			success: function(result){
				console.log("success");
				location.reload();
				location.href = "http://localhost:8080/mock/select-block";
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
				console.log(textStatus);
				console.log(errorThrown);
				alert("Error: There are no blocks to add this to");
				location.reload();
			}
		}) 
	}
});
