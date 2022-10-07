<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Sign Up</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/stylesSU.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  </head>
  <body>
    <div class="login-box">
      <h2>Create Your Account</h2>
     	<span></span>
     	
      <p id="ace" style="color:#03e9f4;font-weight: bolder; "></p>
      <p id="acec" style="color:#03e9f4;font-weight: bolder; ">${create_properly_warning}</p>
      <form id="createAcc" action="createAcc" method="post">
      <div class="row">
      	<div class="col-lg-6">
      		<div class="user-box">
          <input type="text" name="emailId" id="emailId" required autocomplete="off">
          <label for="">Email Id</label>
        </div>
        <div class="user-box">
          <input type="text" name="fname" id="fname" required autocomplete="off">
          <label for="">First Name</label>
        </div>
        <div class="user-box">
          <input type="text" name="lname" id="lname" required autocomplete="off">
          <label for="">Last Name</label>
        </div>
      
	      </div>
	      <div class="col-lg-6">
	      	<div class="user-box">
          <input type="text" name="uname" id="uname" required autocomplete="off">
          <label for="">Username</label>
        </div>
        <div class="user-box">
          <input type="password" name="pass" id="pass" required autocomplete="off">
          <label for="">Password</label>
        </div>
        <div class="user-box">
          <input type="password" name="cpass" id="cpass" required autocomplete="off">
          <label for="">Confirm Password</label>
        </div>
	      </div>
      </div>
      
        
<!--         <input type="submit"> -->
        <a class="createbtn" onclick="myFunctioncreateAcc()">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Create
        </a>
         </form>
         <form id="backToIndex" action="/" method="post">
         </form>
    </div>
    <script src="resources/js/index.js"></script>
  </body>
</html>
