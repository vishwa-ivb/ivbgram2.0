<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Profile</title>

  <script src="https://kit.fontawesome.com/8132e3a9c0.js" crossorigin="anonymous"></script>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="resources/css/stylesprofile.css">
  <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>
<body>

	<div id="modal-profile">
	<form id="userhome" action="/userhome" method="post">
		<input type="hidden" class="" name="searchinguser" id="searchinguser" value="${profile.account}">
	</form>
		<a type="submit" class="homebtn" onClick="userhome();"><i class="fa-solid fa-house-user"></i></a>
		<a type="button" class="editbtn" onClick="editprofilepop();"><i class="fas fa-edit" aria-hidden="true"
					 style="font-size: 35px;"></i></a>
		<div class="profile-tab">
			
			<div class="propicdiv">
				<img id="propic" alt="No image available for this Post!" 
				src="/profile/picture/${profile.pid}" 
				onerror="this.src='resources/images/profileicon.png'" />
			</div>
			<div style="font-weight: 1000;padding: 20px;font-size: x-large;text-transform: uppercase;">
				${profile.account}
			</div>
			<div>
				${profile.about}
			</div>
			<hr>
			<div>
				${profile.postscount}
			</div>
			
			<div class="proedit-modal">
		      <div class="modal-proedit">
		        <h3 style="font-weight: 1000;padding: 20px;font-size: x-large;">EDIT PROFILE</h3>
		        <form id="changepropic" action="/profile/update" method="post">
		        <div class="row">
		  			<div class="col-lg-6">
						<div style="clip-path: circle();margin-bottom: 30px;">
							<img style="max-height: 300px;" id="propic" alt="No image available for this Post!" 
							src="/profile/picture/${profile.pid}" 
							onerror="this.src='resources/images/profileicon.png'" />
						</div>
						<input type="file" class="form-control" placeholder="" name="propicupload" id="propicupload" required="required">
					</div>
					<div class="col-lg-6">
					<div>
						<input type="text" class="form-control" name="user" id="user" value="${profile.account}" readonly>
		         		<textarea class="form-control" placeholder="About" name="aboutinput"  style="height: 200px;" id="aboutinput">${profile.about}</textarea>
		         		<input type="text" class="form-control" name="emailinput" id="emailinput" value="${profile.email}">
					</div>
					<div class="row">
		  				<div class="col-lg-6">
							<a id="submitpropic" style="text-decoration: none;" class="glowbtn" type="button" onClick="profileupdate();">Save</a>
						</div>
						<div class="col-lg-6">
							<a class="close glowbtn" style="text-decoration: none;" type="button">Cancel</a>
						</div>
					</div>
					</div>
				</div>	
				</form>
				

				<p id="successupload"></p>
		      </div>
		    </div>
			
			
		</div>
	</div>
	<script src="resources/js/profile.js"></script>
</body>
</html>