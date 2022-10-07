function myFunctionSI() {
	document.getElementById("si").submit();
}
function myFunctionSU() {
    document.getElementById("su").submit();
}
function myFunctioncreateAcc() {
	var uname = $("#uname").val();
    var pass = $("#pass").val(); 
    var cpass = $("#cpass").val();
	var fname = $("#fname").val(); 
    var lname = $("#lname").val();
    var emailId = $("#emailId").val(); 
    
    if(uname==="" || pass==="" || cpass==="" || emailId==="" || fname==="" || lname==="")
    {
		$("#ace").html("Every Fields are Mandatory!");
		$("#ace").css('padding-bottom','30px');
	}
	else if(pass.length >4) 
	{
		$("#ace").html("Password can contain only Four Characters!");
		$("#ace").css('padding-bottom','30px');
	}
	else if(pass.length <4) 
	{
		$("#ace").html("Password must contain Four Characters!");
		$("#ace").css('padding-bottom','30px');
	}
	else if(pass != cpass){
		$("#ace").html("Confirm Password is Different!");
		$("#ace").css('padding-bottom','30px');
	}
	else{
		document.getElementById("createAcc").submit();
		$("#acec").css('padding-bottom','30px');
	}
	
}
function myFunctionHome() {
    document.getElementById("home").submit();
}