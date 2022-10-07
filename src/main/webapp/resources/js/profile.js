function profileupdate() {
    	$("#submitpropic").prop("disabled", true);
        var propic = $("#propicupload").val(); 
        var user = $("#user").val(); 
        var emailinput = $("#emailinput").val(); 
        var aboutinput = $("#aboutinput").val(); 
    	 var form = $("#changepropic").serialize();
    	var propicdata = new FormData($("#changepropic")[0]);
    	propicdata.append('propic', propic);
//    	propicdata.append('emailinput', emailinput);
//    	propicdata.append('aboutinput', aboutinput);
    	propicdata.append('user', user);
    	//alert(data);
    	
	$.ajax({
                        type: 'POST',
                        enctype: 'multipart/propicdata',
                        data: propicdata,
                        url: "/profile/update", 
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(propicdata, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
                            $("#successupload").html("Profile Updated Succsessfully.");
                            var delayInMilliseconds = 2000; //3 second
						    setTimeout(function() {
							window.location.reload();
							}, delayInMilliseconds);
                            
                         }	   
                        },
                        error: function(e) {
                        	$('#error').css('display','block');
                            $("#error").html("Oops! something went wrong.");
                            $('#error').delay(5000).fadeOut('slow');
                            location.reload();
                        }
                    });
	
}
           
                    
        		
        		
function userhome(){
	document.getElementById("userhome").submit();
}

function editprofilepop(){
	$('.proedit-modal').css('display','flex');
}

document.querySelector('.close').addEventListener("click", function() {
	document.querySelector('.proedit-modal').style.display = "none";
                         
});