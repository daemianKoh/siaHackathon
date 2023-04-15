

$(document).ready(function() {
	getBookingRefDetail();
});


function getBookingRefDetail() {

	var bookingRefNo = document.getElementById("bookingRefNo").value;

	var path = './getBookingSummary?bookingRefNo=' + encodeURIComponent(bookingRefNo);
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: path,
		success: function(data) {
			if (data.returnCode == 0) {
				console.log(data);
			}
			else {
				console.log("no record");
			}
		},
		error: function(e) {
			console.log("error");
		}
	});
}
