<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
<link rel="stylesheet" href="resources/css/stylesSU.css">
  </head>
  <body>
    <div class="login-box">
      <h2>Create Your Account</h2>
     	<span></span>
      <p style="color:#03e9f4;font-weight: bolder; ">${create_properly_warning}</p>
      <form id="createAcc" action="createAcc" method="post">
      
      <div class="user-box">
          <input type="text" name="emailId" required="">
          <label for="">Email Id</label>
        </div>
        <div class="user-box">
          <input type="text" name="uname" required="">
          <label for="">Username</label>
        </div>
        <div class="user-box">
          <input type="password" name="pass" required="">
          <label for="">Password</label>
        </div>
        <div class="user-box">
          <input type="password" name="cpass" required="">
          <label for="">Confirm Password</label>
        </div>
<!--         <input type="submit"> -->
        <a href="#" onclick="myFunctioncreateAcc()">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Create
        </a>
         </form>
         <form id="backToIndex" action="/" method="post">
         </form>
      <script>function myFunctioncreateAcc() {
    	    document.getElementById("createAcc").submit();
      }</script>
    </div>
  </body>
</html>
