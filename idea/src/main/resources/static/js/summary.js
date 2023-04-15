

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

function calculatePercentage(id, idPie, personInfo){
	
	const totalItems = 4;
	var itemsDone = 0;
	
	if(personInfo.visa != 'N'){
		itemsDone ++;
	}
	if(personInfo.passport != 'N'){
		itemsDone ++;
	}
	if(personInfo.pcr != 'N'){
		itemsDone ++;
	}
	if(personInfo.vaccination != 'N'){
		itemsDone ++;
	}
	
	const percentageDone = (itemsDone / totalItems) * 100;
	document.getElementById(id).textContent = percentageDone + '%';
	
	const pieElement = document.getElementById(idPie);
	if(itemsDone == 1){
		pieElement.className = pieElement.className.replace('wrapper-pie', 'wrapper-pie twenty-five');
	}
	else if(itemsDone == 2){
		pieElement.className = pieElement.className.replace('wrapper-pie', 'wrapper-pie fifty');
	}
	else if(itemsDone == 3){
		pieElement.className = pieElement.className.replace('wrapper-pie', 'wrapper-pie seventy-five');
	}
	else{
		pieElement.className = pieElement.className.replace('wrapper-pie', 'wrapper-pie hundred');
	}

}

function populateProfiles(personInfos){
	document.getElementById("ckTitle").textContent = personInfos[0].name;
	calculatePercentage("ckSubTitle", "ckPie", personInfos[0]);
	
	if(personInfos.length == 1){
		document.getElementById("balajiDiv").style.display = "none";
		document.getElementById("mikeDiv").style.display = "none";
		document.getElementById("paulDiv").style.display = "none";
	}
	else{
		document.getElementById("balajiTitle").textContent = personInfos[1].name;
		calculatePercentage("balajiSubTitle", "balajiPie", personInfos[1]);
		document.getElementById("mikeTitle").textContent = personInfos[2].name;
		calculatePercentage("mikeSubTitle", "mikePie", personInfos[2]);
		document.getElementById("paulTitle").textContent = personInfos[3].name;
		calculatePercentage("paulSubTitle", "paulPie", personInfos[3]);
	}
	
}

function populateTravelDetail(data) {
	const idLeaveFrm = document.getElementById("idLeaveFrm");
	const idGoto = document.getElementById("idGoto");
	const idLeaveDt = document.getElementById("idLeaveDt");
	const idReturnDt = document.getElementById("idReturnDt");

	idLeaveFrm.textContent = data.originFullName;
	idGoto.textContent = data.destinationFullName;


	const departDate = new Date(data.departureFromSgDate); // Replace with your date
	const returnDate = new Date(data.returnToSgDate); // Replace with your date
	
	const options = { day: "2-digit", month: "short", weekday: "short" };
	const formattedDepartDate = departDate.toLocaleDateString("en-US", options);
	const formattedReturnDate = returnDate.toLocaleDateString("en-US", options);
	
	

	idLeaveDt.textContent = formattedDepartDate;
	idReturnDt.textContent = formattedReturnDate;
}

function groupOrIndividual(numberOfPerson) {

	const myDiv = document.getElementById("summaryStsText");

	if (numberOfPerson == 1) {
		myDiv.textContent = "Your booking summary and status";
		document.getElementById("individualId").checked = true;
	}
	else {
		myDiv.textContent = "Your group booking summary and status";
		document.getElementById("grpId").checked = true;
	}

	const radios = document.getElementsByName("radioGrpIndividual");

	for (let i = 0; i < radios.length; i++) {
		radios[i].disabled = true;
	}

}

function caluclateLeftOverDate(dateString) {

	const idDaysDiv = document.getElementById("idDaysDiv");
	const idHoursDiv = document.getElementById("idHoursDiv");
	const idMinsDiv = document.getElementById("idMinsDiv");
	const idSecDiv = document.getElementById("idSecDiv");

	const departFrmSGDate = new Date(dateString);
	const intervalId = setInterval(() => {
		const now = new Date();
		const timeDiff = departFrmSGDate.getTime() - now.getTime();

		if (timeDiff <= 0) {
			clearInterval(intervalId);
			timerElement.textContent = "Timer has ended";
		} else {
			const seconds = Math.floor(timeDiff / 1000) % 60;
			const minutes = Math.floor(timeDiff / (1000 * 60)) % 60;
			const hours = Math.floor(timeDiff / (1000 * 60 * 60)) % 24;
			const days = Math.floor(timeDiff / (1000 * 60 * 60 * 24));

			idDaysDiv.textContent = days;
			idHoursDiv.textContent = hours;
			idMinsDiv.textContent = minutes;
			idSecDiv.textContent = seconds;
		}
	}, 1000);
}