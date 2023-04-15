$(document).ready(function() {
	getDetail();
});

function getDetail() {
	var path = './getDetail';
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: path,
		success: function(data) {
			if (data.returnCode == 0) {
				loadSummary(data.o);
				const contextPath = window.location.pathname.split('/')[2];
				console.log('Context path:', contextPath);
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
	//populateProfiles(data.personInfos);
}
