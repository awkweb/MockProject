$('#newTab').click(function(event) {
	$("#sendorderbutton").removeClass("disabled");
	$("#cancelorderbutton").removeClass("disabled");
});

$('#openTab').click(function(event) {
	$("#sendorderbutton").addClass("disabled");
	$("#cancelorderbutton").removeClass("disabled");
});

$('#cancelledTab').click(function(event) {
	$("#sendorderbutton").addClass("disabled");
	$("#cancelorderbutton").addClass("disabled");
});

$('#checkboxNew').click(function() {
	var ordervalue = $(this).attr("id");
	var checkedStatus = $("input:checkbox[name=checkboxNew]:checked").is(':checked');

	$("input[data-checkid=subNewOrders]").each(function() {
		$(this).prop('checked', checkedStatus);
	});
});

$('#checkboxOpen').click(function() {
	var ordervalue = $(this).attr("id");
	var checkedStatus = $("input:checkbox[name=checkboxOpen]:checked").is(':checked');

	$("input[data-checkid=subOpenOrders]").each(function() {
		$(this).prop('checked', checkedStatus);
	});
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
		url : "/mock/edit-order",  
		contentType : "application/json",
		data : JSON.stringify(id),
		success : function(result) {
			console.log("success");
			location.href = "/mock/edit-order";
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
		url : "/mock/amend-order",  
		contentType : "application/json",
		data : JSON.stringify(id),
		success : function(result) {
			console.log("success");
			location.href = "/mock/amend-order";
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.status);
			console.log(textStatus);
			console.log(errorThrown);
		}
	})
});
