$(document).ready(function() {
	getDetail();
});

const btnYes = document.getElementById('btnYesSave');
btnYes.addEventListener('click', function(event) {
	event.preventDefault();
	
	document.getElementById("tbPssport").value = document.getElementById("cbPassport").checked ? 'Y' : 'N';
	document.getElementById("tbVisa").value = document.getElementById("cbVisa").checked ? 'Y' : 'N';
	document.getElementById("tbPcr").value = document.getElementById("cbPcr").checked ? 'Y' : 'N';
	document.getElementById("tbVax").value = document.getElementById("cbVaccination").checked ? 'Y' : 'N';
	
	const updateDetail = document.querySelector('#updateDetail');
	updateDetail.submit();
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

	if ('CK' == personInfo.name) {
		myImage = document.getElementById('idProfilePic').src = 'images/ck-full.png';
	}
	else if ('Balaji' == personInfo.name) {
		myImage = document.getElementById('idProfilePic').src = 'images/balaji-full.png';
	}
	else if ('Mike' == personInfo.name) {
		myImage = document.getElementById('idProfilePic').src = 'images/mike.png';
	}
	else {
		myImage = document.getElementById('idProfilePic').src = 'images/dam-full.png';
	}

	if (personInfo.visa != 'N') {
		document.getElementById('cbVisa').checked = true;
	}

	if (personInfo.passport != 'N') {
		document.getElementById('cbPassport').checked = true;
	}
	if (personInfo.pcr != 'N') {
		document.getElementById('cbPcr').checked = true;
	}
	if (personInfo.vaccination != 'N') {
		document.getElementById('cbVaccination').checked = true;
	}
}
