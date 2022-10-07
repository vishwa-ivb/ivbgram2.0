function profileupdate() {
    	$("#submitpropic").prop("disabled", true);
        var propic = $("#propicupload").val(); 
        var user = $("#user").val(); 
        var emailinput = $("#emailinput").val(); 
        var aboutinput = $("#aboutinput").val(); 
    	 var form = $("#changepropic").serialize();
    	var propicdata = new FormData($("#changepropic")[0]);
 //   	propicdata.append('propic', propic);
//    	propicdata.append('emailinput', emailinput);
//    	propicdata.append('aboutinput', aboutinput);
//	   	propicdata.append('user', user);
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
	//history.back();
	document.getElementById("userhome").submit();
}

function editprofilepop(){
	$('.proedit-modal').css('display','flex');
}

document.querySelector('.close').addEventListener("click", function() {
	document.querySelector('.proedit-modal').style.display = "none";
                         
});

var num=0;
function findi(clicked) {
num= clicked;
$("#delete"+num).prop("disabled", true);

    	var deleteId = $("#deleteId"+num).val();
    	var deletedata = new FormData();
    	deletedata.append('deleteId', deleteId);
    	
    	//alert(data);
                    $.ajax({
                        type: 'POST',
                        enctype: 'text/deletePost-deletedata',
                        data: deletedata,
                        url: "/post/delete", 
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(deletedata, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
                        	$('#success'+num).css('display','block');
                            $("#error").text("");
                            $("#success"+num).html("Post Deleted Succsessfully.");
                            $("#post"+num).css('border','3px solid green');
                            $('#success'+num).delay(3000).fadeOut('slow');
                            var delayInMilliseconds = 2000; //3 second
						    setTimeout(function() {
							window.location.reload();
							}, delayInMilliseconds);
                            
                         }	   
                        },
                        error: function(e) {
                        	$('#loader').hide();
                        	$('#error').css('display','block');
                            $("#error").html("Oops! something went wrong.");
                            $('#error').delay(5000).fadeOut('slow');
                            var delayInMilliseconds = 3000; //3 second
						    setTimeout(function() {
							window.location.reload();
							}, delayInMilliseconds);
                        }
            });
}
   
var nume=0;
function findiedit(clickede) {
nume= clickede;
$("#edit"+nume).prop("disabled", true);
     $('#edit-modal'+nume).css('display','flex');
     
}
function findieditclose(clickede) {
nume= clickede;
     $('#edit-modal'+nume).css('display','none');
     $('#edit'+nume).prop("disabled", false);
}

var numu=0;
function update(clickedu) {
numu= clickedu;
$("#update"+numu).prop("disabled", true);
    	var updateId = $("#updateId"+numu).val();
    	var updateContent = $("#updateContent"+numu).val();
    	var updatedata = new FormData();
    	updatedata.append('updateId', updateId);
    	updatedata.append('updateContent', updateContent);
    	//alert(data);
                    $.ajax({
                        type: 'POST',
                        enctype: updatedata,
                        data: updatedata,
                        url: "/post/update", 
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(updatedata, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
						$('#loader').hide(); 
                        	$('#success'+numu).css('display','block');
                            $("#error").text("");
                            $("#success"+numu).html("Post Updated Succsessfully.");
                            $("#post"+numu).css('border','3px solid green');
                            $('#success'+numu).delay(3000).fadeOut('slow');
                            var delayInMilliseconds = 2000; //3 second
						    setTimeout(function() {
							window.location.reload();
							//$("#post").load(window.location.href + " #post");
							//$("#post").load(" #post > *");
							}, delayInMilliseconds);
							$('#edit-modal'+numu).css('display','none');
   							$('#edit'+numu).prop("disabled", false);
   							
                         }	   
                        },
                        error: function(e) {
                        	$('#loader').hide();
                        	$('#error').css('display','block');
                            $("#error").html("Oops! something went wrong.");
                            $('#error').delay(5000).fadeOut('slow');
                            var delayInMilliseconds = 3000; //3 second
						    setTimeout(function() {
							window.location.reload();
							}, delayInMilliseconds);
                        }
            });
}

var numc=0;
function findicomment(clickedc) {
numc= clickedc;
	$('#commentpop'+numc).prop("disabled", true);
     $('#comment-modal'+numc).css('display','flex');    
     var postIdo = $("#commentId"+numc).val();
    	var commentdatao = new FormData();
    	commentdatao.append('postIdo', postIdo);
     $.ajax({
                        type: 'POST',
                        enctype: commentdatao,
                        data: commentdatao,
                        url: "/post/allComments", 
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(commentdatao, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
						$('#loader').hide(); 
                         }	   
                        },
                        error: function(e) {
                        	$('#loader').hide();
                        }
            });
}

function findicommentclose(clickedc) {
numc= clickedc;
     $('#comment-modal'+numc).css('display','none');
      $('#commentpop'+numc).prop("disabled", false);
}

function comment(clickedc) {
numc= clickedc;
$("#commentbtn"+numc).prop("disabled", true);
    	var postId = $("#commentId"+numc).val();
    	var comment = $("#comment"+numc).val();
    	var commentator = $("#account").val();
    	var commentdata = new FormData();
    	commentdata.append('postId', postId);
    	commentdata.append('comment', comment);
    	commentdata.append('commentator', commentator);
    	if (comment === "") {
            $("#comment"+numc).css("border-color", "red");
            $("#error_for_empty_comment"+numc).html("Please write something...").css("color","red");
            $('#commentbtn'+numc).prop("disabled", false);
        } else {
   
    	//alert(data);
                    $.ajax({
                        type: 'POST',
                        enctype: commentdata,
                        data: commentdata,
                        url: "/post/comment", 
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(commentdata, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
						$('#loader').hide(); 
                        	$('#success'+numc).css('display','block');
                            $("#error").text("");
                            $("#success"+numc).html("Commented Succsessfully.");
                            $("#post"+numc).css('border','3px solid green');
                            $('#success'+numc).delay(3000).fadeOut('slow');
                            var delayInMilliseconds = 2000; //3 second
						    setTimeout(function() {
							window.location.reload();
							//$("#post").load(window.location.href + " #post");
							//$("#post").load(" #post > *");
							}, delayInMilliseconds);
							$('#comment-modal'+numc).css('display','none');
      						$('#commentpop'+numc).prop("disabled", false);
   							
                         }	   
                        },
                        error: function(e) {
                        	$('#loader').hide();
                        	$('#error').css('display','block');
                            $("#error").html("Oops! something went wrong.");
                            $('#error').delay(5000).fadeOut('slow');
                            var delayInMilliseconds = 3000; //3 second
						    setTimeout(function() {
							window.location.reload();
							}, delayInMilliseconds);
                        }
            });
        }
}