

$(document).ready(function() {
	getBookingRefDetail();
});

const btnCk = document.getElementById('btnCk');
btnCk.addEventListener('click', function(event) {
	event.preventDefault();
	routeToDetailMain("CK");
});

const btnBalaji = document.getElementById('btnBalaji');
btnBalaji.addEventListener('click', function(event) {
	event.preventDefault();
	routeToDetailMain("Balaji");
});

const btnMike = document.getElementById('btnMike');
btnMike.addEventListener('click', function(event) {
	event.preventDefault();
	routeToDetailMain("Mike");
});

const btnPaul = document.getElementById('btnPaul');
btnPaul.addEventListener('click', function(event) {
	event.preventDefault();
	routeToDetailMain("Paul");	
});

function routeToDetailMain(nameValue) {
	
	const detailForm = document.querySelector('#detailForm');
	document.getElementById("nameValue").value = nameValue;
	detailForm.submit();
}

function getBookingRefDetail() {

	var bookingRefNo = document.getElementById("bookingRefNo").value;

	var path = './getBookingSummary?bookingRefNo=' + encodeURIComponent(bookingRefNo);
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: path,
		success: function(data) {
			if (data.returnCode == 0) {
				loadSummary(data.o);
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

function loadSummary(data) {
	caluclateLeftOverDate(data.departureFromSgDate);
	groupOrIndividual(data.numberOfPerson);
	populateTravelDetail(data);
	populateProfiles(data.personInfos);
}
