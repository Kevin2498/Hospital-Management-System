function mutiply(qty,rate,curr)
{	
	var a = qty;
	var b = rate
	var res = a*b;
	document.getElementById("amount"+curr).value = res;
}

function dateDifference ()
{
	var dateFirst = new Date("05/05/2020");
    var dateSecond = new Date("05/25/2020");

    var timeDiff = Math.abs(dateSecond.getTime() - dateFirst.getTime());

    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
    
    document.getElementById("dayDiff").value = diffDays;
}