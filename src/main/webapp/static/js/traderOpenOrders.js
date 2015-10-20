$("#createblockbutton").click(function(event) {
	event.preventDefault();
	var selectedOrders = $(".ordercheckbox:checked").map(function() {
		return this.value;
	}).toArray();
	if (selectedOrders.length > 0) {
		$.ajax({
			type : "POST",
			url : "/mock/create-block",
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


$("#addtoblockbutton").click(function(event) {
	event.preventDefault();
	var selectedOrders = $(".ordercheckbox:checked").map(function() {
		return this.value;
	}).toArray();

	if (selectedOrders.length > 0) {
		$.ajax({
			type : "POST",
			url : "/mock/add-block",
			contentType : "application/json",
			data : JSON.stringify(selectedOrders),
			success : function(result) {
				console.log("success");
				location.reload();
				location.href = "/mock/select-block";

			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
				console.log(textStatus);
				console.log(errorThrown);
				alert("Error: There are no blocks to add this to");
			}
		})
	}
});
$("input:radio[name=radioOption]").click(function() {
	var value = $(this).attr("id");
	alert(value);
})
$("#selectaddblockbutton").click(function(event) {

	event.preventDefault();
	var selectedblock = $("input:radio[name=selectblock]:checked").attr("id");
	if (selectedblock != null) {
		$.ajax({
			type : "POST",
			url : "/mock/block-selected",
			contentType : "application/json",
			data : JSON.stringify(selectedblock),
			success : function(result) {
				console.log("success");
				location.reload();
				location.href = "/mock/open-orders";

			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status);
				console.log(textStatus);
				console.log(errorThrown);
				location.reload();
			}
		})
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
