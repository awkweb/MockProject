$("#removeOrders").click(function(event){
	event.preventDefault();
	var orderIds = $(".ordercheckbox:checked").map(function(){
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
	} else {
		alert("No orders selected.")
	}
});

$("#cancelBlock").click(function(event){
	event.preventDefault();
	var blockId = $("input:radio[name=block]:checked").map(function(){
		return this.value;
	}).toArray();

	if (blockId.length > 0) {
		$.ajax({
			type:"POST",
			url: "/mock/cancel-block",
			contentType: "application/json",
			data: JSON.stringify(blockId),
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
	} else {
		alert("No blocks selected.")
	}
});

$("#sendBlock").click(function(event){
	event.preventDefault();
	var blockId = $("input:radio[name=block]:checked").map(function(){
		return this.value;
	}).toArray();

	if (blockId.length > 0) {
		$.ajax({
			type:"POST",
			url: "/mock/send-block",
			contentType: "application/json",
			data: JSON.stringify(blockId),
			success: function(result){
				console.log("success");
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
				console.log(textStatus);
				console.log(errorThrown);
			}
		}) 
	} else {
		alert("No blocks selected.")
	}
});

$('.blockcheckbox').click(function() {
	var blockvalue = $(this).attr("id");
	var checkedStatus = $("input:checkbox[name=block]:checked").is(':checked');

	$("input[data-checkid=" + blockvalue + "]").each(function() {
		console.log("Checking boxes as "+checkedStatus);
		$(this).prop('checked', checkedStatus);
	});
});

$(document).on("click", "#editOrder", function () {
	var editbuttonid = $(this).data('id');
	var orderType = $(this).data('options');
	$("h4#modalTitle").html("Edit Order # " + editbuttonid);
	$("label#inputLabel").html(orderType + " Price");
	$("input#inputPrice").val('');
	$(".modalid").attr("value", editbuttonid);
	$(".modaltype").attr("value", orderType);
});
