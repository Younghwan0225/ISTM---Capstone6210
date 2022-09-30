function storeTimeGarage() {
    var garage = document.getElementById("garageSelect").value;
	var dateString = document.getElementById("startTime").value;
	var dateString2 = document.getElementById("endTime").value;

	var DateStart = new Date(dateString);
	var DateEnd = new Date(dateString2);
	var startDate =  DateStart.toLocaleDateString();
	var startTime =  DateStart.toLocaleTimeString();
	var endDate =  DateEnd.toLocaleDateString();
	var endTime =  DateEnd.toLocaleTimeString();
  
    var startDateTime = startDate + " " + startTime;
    var endDateTime = endDate + " " + endTime;

	localStorage.setItem("garage", garage);
	localStorage.setItem("startDateTime", startDateTime);
	localStorage.setItem("endDateTime", endDateTime);
	
	
};

function checkDate() {
	var dateString = document.getElementById("startTime").value;
	var dateString2 = document.getElementById("endTime").value;
	var DateStart = new Date(dateString);
	var DateEnd = new Date(dateString2);
	if (DateEnd < DateStart) {
		alert("End date cannot be less than Start date.");
		document.getElementById('#endTime').value = "";
		return false;
	}
	return true;
};
