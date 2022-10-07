document.getElementById('getpop').addEventListener("click", function() {
  document.querySelector('.input-modal').style.display = "flex";
});

document.querySelector('.close').addEventListener("click", function() {
  document.querySelector('.input-modal').style.display = "none";
  

});

document.querySelector('.close-noti').addEventListener("click", function() {

$.ajax({
    type: 'POST',
    url: "/notificationReaded",
    processData: false,
    contentType: false,
    cache: false,
  });
  document.querySelector('#modal-noti-background').style.display = "none";
  document.getElementById("notification").style.textDecoration = "none";
});



$(document).ready(function() {
  $('#loader').hide();
  $("#submit").on("click", function() {
    $("#submit").prop("disabled", true);
    var account = $("#account").val();
    var file = $("#image").val();
    var content = $("#content").val();
    var form = $("#form").serialize();
    var data = new FormData($("#form")[0]);
    data.append('account', account);
    data.append('content', content);
    //alert(data);
    $('#loader').show();
    if (content === "") {
      $("#submit").prop("disabled", false);
      $("#content").css("border-color", "red");
      $("#error_for_empty").html("Please write something...").css("color", "red");
    } else {
      $("#account").css("border-color", "");
      $("#image").css("border-color", "");
      $("#content").css("border-color", "");
      $.ajax({
        type: 'POST',
        enctype: 'multipart/form-data',
        data: data,
        url: "/post/savePostDetails",
        processData: false,
        contentType: false,
        cache: false,
        success: function(data, statusText, xhr) {
          console.log(xhr.status);
          if (xhr.status == "200") {
            $('#loader').hide();
            $("#form")[0].reset();
            $('#success').css('display', 'block');
            $("#error").text("");
            $("#successupload").html("Posted Succsessfully.");
            $('#success').delay(3000).fadeOut('slow');
            document.querySelector('.input-modal').style.display = "none";
            var delayInMilliseconds = 2000; //3 second
            setTimeout(function() {
              window.location.reload();
            }, delayInMilliseconds);

          }
        },
        error: function(e) {
          $('#loader').hide();
          $('#error').css('display', 'block');
          $("#error").html("Oops! something went wrong.");
          $('#error').delay(5000).fadeOut('slow');
          location.reload();
        }
      });
    }
  });
});

var num = 0;

function findi(clicked) {
  num = clicked;
  $("#delete" + num).prop("disabled", true);

  var deleteId = $("#deleteId" + num).val();
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
      if (xhr.status == "200") {
        $('#loader').hide();
        $("#form")[0].reset();
        $('#success' + num).css('display', 'block');
        $("#error").text("");
        $("#success" + num).html("Post Deleted Succsessfully.");
        $("#post" + num).css('border', '3px solid green');
        $('#success' + num).delay(3000).fadeOut('slow');
        var delayInMilliseconds = 2000; //3 second
        setTimeout(function() {
          location.reload();
        }, delayInMilliseconds);

      }
    },
    error: function(e) {
      $('#loader').hide();
      $('#error').css('display', 'block');
      $("#error").html("Oops! something went wrong.");
      $('#error').delay(5000).fadeOut('slow');
      var delayInMilliseconds = 3000; //3 second
      setTimeout(function() {
        window.location.reload();
      }, delayInMilliseconds);
    }
  });
}

var nume = 0;

function findiedit(clickede) {
  nume = clickede;
  $("#edit" + nume).prop("disabled", true);
  $('#edit-modal' + nume).css('display', 'flex');

}

function findieditclose(clickede) {
  nume = clickede;
  $('#edit-modal' + nume).css('display', 'none');
  $('#edit' + nume).prop("disabled", false);
}

var numu = 0;

function update(clickedu) {
  numu = clickedu;
  $("#update" + numu).prop("disabled", true);
  var updateId = $("#updateId" + numu).val();
  var updateContent = $("#updateContent" + numu).val();
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
      if (xhr.status == "200") {
        $('#loader').hide();
        $('#success' + numu).css('display', 'block');
        $("#error").text("");
        $("#success" + numu).html("Post Updated Succsessfully.");
        $("#post" + numu).css('border', '3px solid green');
        $('#success' + numu).delay(3000).fadeOut('slow');
        var delayInMilliseconds = 2000; //3 second
        setTimeout(function() {
          window.location.reload();
          //$("#post").load(window.location.href + " #post");
          //$("#post").load(" #post > *");
        }, delayInMilliseconds);
        $('#edit-modal' + numu).css('display', 'none');
        $('#edit' + numu).prop("disabled", false);

      }
    },
    error: function(e) {
      $('#loader').hide();
      $('#error').css('display', 'block');
      $("#error").html("Oops! something went wrong.");
      $('#error').delay(5000).fadeOut('slow');
      var delayInMilliseconds = 3000; //3 second
      setTimeout(function() {
        window.location.reload();
      }, delayInMilliseconds);
    }
  });
}

var numc = 0;

function findicomment(clickedc) {
  numc = clickedc;
  $('#commentpop' + numc).prop("disabled", true);
  $('#comment-modal' + numc).css('display', 'flex');
  var postIdo = $("#commentId" + numc).val();
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
      if (xhr.status == "200") {
        $('#loader').hide();
      }
    },
    error: function(e) {
      $('#loader').hide();
    }
  });
}

function findicommentclose(clickedc) {
  numc = clickedc;
  $('#comment-modal' + numc).css('display', 'none');
  $('#commentpop' + numc).prop("disabled", false);
  $('#modal-noti-background').css('z-index', 0);
}

function comment(clickedc) {
  numc = clickedc;
  $("#commentbtn" + numc).prop("disabled", true);
  var postId = $("#commentId" + numc).val();
  var comment = $("#comment" + numc).val();
  var commentator = $("#account").val();
  var commentdata = new FormData();
  commentdata.append('postId', postId);
  commentdata.append('comment', comment);
  commentdata.append('commentator', commentator);
  if (comment === "") {
    $("#comment" + numc).css("border-color", "red");
    $("#error_for_empty_comment" + numc).html("Please write something...").css("color", "red");
    $('#commentbtn' + numc).prop("disabled", false);
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
        if (xhr.status == "200") {
          $('#loader').hide();
          $('#success' + numc).css('display', 'block');
          $("#error").text("");
          $("#success" + numc).html("Commented Succsessfully.");
          $("#post" + numc).css('border', '3px solid green');
          $('#success' + numc).delay(3000).fadeOut('slow');
          var delayInMilliseconds = 2000; //3 second
          setTimeout(function() {
            window.location.reload();
            //$("#post").load(window.location.href + " #post");
            //$("#post").load(" #post > *");
          }, delayInMilliseconds);
          $('#comment-modal' + numc).css('display', 'none');
          $('#commentpop' + numc).prop("disabled", false);

        }
      },
      error: function(e) {
        $('#loader').hide();
        $('#error').css('display', 'block');
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

function spop() {
  var searchkey = $("#searchkey").val();
  if (searchkey === "") {
    $("#searchkey").css("border", "solid");
    $("#searchkey").css("border-color", "red");
  } else {
    document.getElementById("sp").submit();
  }
}
//
//function searchpop() {
//$("#searchpopup").prop("disabled", true);
//    	var searchkey = $("#searchkey").val();
//    	var searchdata = new FormData();
//    	searchdata.append('searchkey', searchkey);
//    	if (searchkey === "") {
//            $("#searchkey").css("border-color", "red");
//            $("#searchpopup").prop("disabled", false);
//        } else {
//   
//    	//alert(data);
//                    $.ajax({
//                        type: 'POST',
//                        enctype: searchdata,
//                        data: searchdata,
//                        url: "/post/search", 
//                        processData: false,
//                        contentType: false,
//                        cache: false,
//                        success: function(searchdata, statusText, xhr) {
//                        console.log(xhr.status);
//                        if(xhr.status == "200") {
//						
//							//window.location.reload();
//							$('#modal-searchresult-background').css('display','flex');
//   							
//                         }	   
//                        },
//                        error: function(e) {
//                        	
//                        }
//            });
//        }
//}

function userhome() {
  document.getElementById("userhome").submit();
	//location.href = '/userhome'

}

var nump = 0;

function showprofile(clickedp) {
  nump = clickedp;
  document.getElementById("profileform" + nump).submit();
}

function myprofile() {
	//history.replaceState({account:'vishwa',currentuser:'vishwa'},'Profile','/showprofile')
  document.getElementById("profileform").submit();
}

function notifications() {
  $('#modal-noti-background').css('display','flex');
  
}

function showpostresult() {
  $(".toggleposts").css("background-color", "white");
  $(".toggleposts").css("color", "rgba(0,0,0,.5)");
  $(".toggleposts").css("font-size", "x-large");
  $(".toggleposts").css("padding-top", "0");
  $(".toggleaccounts").css("background-color", "rgba(0,0,0,.5)");
  $(".toggleaccounts").css("font-size", "15px");
  $(".toggleaccounts").css("color", "#03e9f4");
  $(".toggleaccounts").css("padding-top", "5px");
  $(".accountresults").css('display', 'none');
  $(".content").css('display', 'block');
}

function showaccountresult() {
  $(".toggleposts").css("background-color", "rgba(0,0,0,.5)");
  $(".toggleposts").css("color", "#03e9f4");
  $(".toggleposts").css("font-size", "15px");
  $(".toggleposts").css("padding-top", "5px");
  $(".toggleaccounts").css("background-color", "white");
  $(".toggleaccounts").css("color", "rgba(0,0,0,.5)");
  $(".toggleaccounts").css("font-size", "x-large");
  $(".toggleaccounts").css("padding-top", "0");
  $(".accountresults").css('display', 'block');
  $(".content").css('display', 'none');
}

function connectnoti(numofnoti){
  //$('#noti-post').css('display', 'flex');
  var connectid = $("#connectid" + numofnoti).val();
  var postnum = $("#" + connectid).val();
  $('#commentpop' + postnum).prop("disabled", true);
  $('#comment-modal' + postnum).css('display', 'flex');
  $('#modal-noti-background').css('z-index', -1);
  var commentdatao = new FormData();
  commentdatao.append('postIdo', connectid);
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
      if (xhr.status == "200") {
        $('#loader').hide();
      }
    },
    error: function(e) {
      $('#loader').hide();
    }
  });
  
}