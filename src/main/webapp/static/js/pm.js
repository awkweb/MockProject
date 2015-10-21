$('#newTab').click(function(event) {
	console.log("Pressed")
});

$('#cancelorderbutton').click(function(event) {
	event.preventDefault();
	var selectedOrders = $(".ordercheckbox:checked").map(function() {
		return this.id;
	}).toArray();
	console.log(selectedOrders);
	
	if (selectedOrders.length > 0) {
		if (confirm('Are you sure you would like to cancel order(s)?')) {
			$.ajax({
				type : "POST",
				url : "/mock/cancel-order",
				contentType : "application/json",
				data : JSON.stringify(selectedOrders),
				success : function(result) {
					console.log("success");
					location.reload();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR.status);
					console.log(textStatus);
					console.log(errorThrown);
				}
			})
		}
	}
});

$('#sendorderbutton').click(function(event) {
	event.preventDefault();
	var selectedOrders = $(".ordercheckbox:checked").map(function() {
		return this.id;
	}).toArray();

	console.log(selectedOrders);
	if (selectedOrders.length > 0) {
		$.ajax({
			type : "POST",
			url : "/mock/sendorderbutton",  //controller mapping
			contentType : "application/json",
			data : JSON.stringify(selectedOrders),
			success : function(result) {
				console.log("success");
				location.reload();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
				console.log(textStatus);
				console.log(errorThrown);
			}
		})
	}
});

$(document).on("click", "#editOrder", function () {
	var id = $(this).data('id');
	console.log(id);

	$.ajax({
		type : "POST",
		url : "/mock/editorderbutton",  
		contentType : "application/json",
		data : JSON.stringify(id),
		success : function(result) {
			console.log("success");

			location.href = "/mock/PMEditOrder_form";
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.status);
			console.log(textStatus);
			console.log(errorThrown);
		}
	})
});

$(document).on("click", "#amendOrder", function () {
	var id = $(this).data('id');
	console.log(id);

	$.ajax({
		type : "POST",
		url : "/mock/amendorderbutton",  
		contentType : "application/json",
		data : JSON.stringify(id),
		success : function(result) {
			console.log("success");

			location.href = "/mock/PMAmendOrder_form";
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.status);
			console.log(textStatus);
			console.log(errorThrown);
		}
	})
});

$('#editorderbutton2').click(function(event) {
	event.preventDefault();
	var selectedOrders = $(".ordercheckbox:checked").map(function() {
		return this.id;
	}).toArray();

	console.log(selectedOrders.length);
	if(selectedOrders.length > 1 ){
		alert("Only edit one order at a time!!");

	}
	if (selectedOrders.length = 1) {
		$.ajax({
			type : "POST",
			url : "/mock/editorderbutton2",  
			contentType : "application/json",
			data : JSON.stringify(selectedOrders),
			success : function(result) {
				console.log("success");
				location.reload();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
				console.log(textStatus);
				console.log(errorThrown);
			}
		})
	}
});
