<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Login-IVB</title>
    <link rel="stylesheet" href="resources/css/stylesSI.css">
  </head>
  <body>
    <div class="login-box">
      <h2>Login</h2>
     	<span></span>
      <p style="color:#03e9f4;font-weight: bolder; ">${no_acc}</p>
      <form id="contents" action="contents" method="post">
      		
      </form>
      <form id="si" action="home" method="post">
        <div class="user-box">
          <input type="text" name="uname" required="" autocomplete="off">
          <label for="">Username</label>
        </div>
        <div class="user-box">
          <input type="password" name="pass" required="">
          <label for="">Password</label>
        </div>
<!--         <input type="submit"> -->
        <a onclick="myFunctionSI()">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          Submit
        </a>
         </form>
         
        <form id="su" action="signup" method="post">
        <a onclick="myFunctionSU()">Create New Account</a>
     	</form>
     	
      <script src="resources/js/index.js"></script>
    </div>
  </body>
</html>
