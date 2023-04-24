var adminChat = 0;
var userChat = 0;

$(document).ready(function() {
	getDetail();
});

function getAdminBubble(idNo, message){
	const divClassBubble = '<div class="chat-bubble msg-self">' + message + '</div>';
	const divClassChatContainer= '<div class="chat-bubble-container ">' + divClassBubble + '</div>';
	const divClassRow = '<div class="row no-gutters" id="admin-'+ idNo + '">' + divClassChatContainer + '</div>';	
	return divClassRow;
}

function getUserBubble(idNo, message){
	const divClassBubble = '<div class="chat-bubble msg-friend">' + message + '</div>';
	const divClassChatContainer= '<div class="chat-bubble-container ">' + divClassBubble + '</div>';
	const divClassRow = '<div class="row no-gutters" id="user-'+ idNo + '">' + divClassChatContainer + '</div>';	
	return divClassRow;
}

function sendGptMsg (){
	const msg = document.getElementById('tbMessageToGpt').value;
	
	var path = './callChatGpt?message=' + encodeURIComponent(msg);
	document.getElementById('tbMessageToGpt').value = '';
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: path,
		success: function(data) {
			if (data.returnCode == 0) {
				var message = data.o.message.content;
				console.log(data.o);
				var count = adminChat - 1;
				const latestAdminDiv = document.getElementById('admin-' + count);
				latestAdminDiv.insertAdjacentHTML('afterend', getUserBubble(userChat, msg));
				const userDiv = document.getElementById('user-' + userChat);
				userDiv.insertAdjacentHTML('afterend', getAdminBubble(adminChat, message));
				userChat = userChat + 1;
				adminChat = adminChat + 1;
				
			}
			else {
				console.log("Error");
			}
		},
		error: function(e) {
			console.log("error");
		}
	});
}

function initGpt(){
	var path = './initChatGpt';
	const parentDiv = document.getElementById('mainMessageBox');
	
	var childDivs = parentDiv.getElementsByTagName("div");
	while(childDivs.length > 0){
		parentDiv.removeChild(childDivs[0]);
	}
	
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: path,
		success: function(data) {
			if (data.returnCode == 0) {
				var message = data.o.message.content;
				console.log(data.o);
				
				parentDiv.innerHTML = getAdminBubble(adminChat, message);
				adminChat = adminChat + 1;
			}
			else {
				console.log("Error");
			}
		},
		error: function(e) {
			console.log("error");
		}
	});
}

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
