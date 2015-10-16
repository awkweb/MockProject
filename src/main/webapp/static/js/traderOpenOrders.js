$(document).ready(function() {
	var selectedOrders = {};
	$("#createblockbutton").click(function() {
		selectedOrders = [];
		$(".ordercheckbox").each(function() {
			if ($(this).is(":checked")) {
				selectedOrders.push($(this).attr("id"));
			}
		});

		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			type : "POST",
			url : "/mock/create-block",
			contentType: "application/json",
			dataType : 'json',
			data : JSON.stringify(selectedOrders),
			contentType: 'application/json',
			success: function(data) { 
		        alert(data.id + " " + data.name);
		    },
		    error:function(data,status,er) { 
		        alert("error: "+data+" status: "+status+" er:"+er);
		    }
		
		});
	});
});
