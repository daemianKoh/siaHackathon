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
	populateRequirement(data.personInfos[0]);
}

function populateRequirement(personInfo) {
	
	document.getElementById("name").textContent = personInfo.name;
	
	if (personInfo.visa != 'N') {
		document.getElementById('cbVisa').checked = true;
		document.getElementById('cbVisa').disabled = true;
	}
	
	if (personInfo.passport != 'N') {
		document.getElementById('cbPassport').checked = true;
		document.getElementById('cbPassport').disabled = true;
	}
	if (personInfo.pcr != 'N') {
		document.getElementById('cbPcr').checked = true;
		document.getElementById('cbPcr').disabled = true;
	}
	if (personInfo.vaccination != 'N') {
		document.getElementById('cbVaccination').checked = true;
		document.getElementById('cbVaccination').disabled = true;
	}
}
