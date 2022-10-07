<%@ page language="java" contentType="text/html; charset=utf-8"
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
	<input type="hidden" class="form-control" id="account" name="account" value="${currentuser}" readonly>
	<div id="modal-profile">
	<form id="userhome" action="/userhome" method="post">
		<input type="hidden" class="" name="searchinguser" id="searchinguser" value="${currentuser}">
	</form>
	<a class="homebtn" onClick="userhome();"><i class="fa-solid fa-house-user"></i></a>
	<j:choose>
                  <j:when test="${profile.account == currentuser}">
		
		<a type="button" class="editbtn" onClick="editprofilepop();"><i class="fas fa-edit" aria-hidden="true"
					 style="font-size: 35px;"></i></a>
				</j:when>	
				<j:otherwise>
				
				</j:otherwise> </j:choose>
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
                          
        <div class="posts">
        <h2 id="content-heading">Posts</h2>

        <div id="post">
          <%int i=1;%>
          <j:forEach items="${userposts}" var="s">
            <j:set var="i" value="<%=i%>"></j:set>
            <div id="post${i}" class="container-fluid">
              <div id="success${i}" class="text-center" style="color:white; font-weight: bolder; background-color: green"></div>
              <div class="row">
                <div class="col-lg-6 imgcol">

                  <img alt="No image available for this Post!" src="/image/display/${s.id}" onerror="this.src='resources/images/no-image.png'" />

                </div>
                <div class="col-lg-4">
                  <p>${s.id}</p><br>
                  <p>${s.account}</p><br>
                  <p>${s.content}</p><br>
                  <p>${s.createDate}</p><br>
                </div>


                      <j:choose>
                  <j:when test="${s.account == currentuser}">
                    <div class="options col-lg-2">

                      <form class="commentPostbtn" action="">
                        <input type="hidden" class="comment-form-control" id="commentacc${i}" value="${s.id}" readonly>
                        <button id="commentpop${i}" class="button" value="${i}" onClick="findicomment(this.value)" type="submit">Comment</button>
                      </form>
                      <br>
                      <form class="editPost" action="">
                        <input type="hidden" class="edit-form-control" id="editId${i}" value="${s.id}" readonly>
                        <button id="edit${i}" class="button" value="${i}" onClick="findiedit(this.value)" type="submit">Edit</button>
                      </form>
                      <br>
                      <form class="deletePost" action="">
                        <input type="hidden" class="delete-form-control" id="deleteId${i}" name="" value="${s.id}" readonly>
                        <button id="delete${i}" class="button" value="${i} " onClick="findi(this.value)" type="submit">Delete</button>
                      </form>

                    </div>
                    <div id="edit-modal${i}" style="background-color: rgba(0, 0, 0, 0.8);width: 100%;height: 100%;
					          	position: fixed;left: 0px;top: 0px;;display: none;justify-content: center;	align-items: center;">
                      <div class="modal-contents-edit">

                        <h3>Update Post Content</h3>
                        <form class="updatePost edit-form" action="">
                          <input type="text" class="form-control" name="" value="${s.account}" readonly>
                          <input type="hidden" class="form-control" id="updateId${i}" name="" value="${s.id}" readonly>
                          <div class="row">
                            <div class="col-lg-5">
                              <input type="text" class="form-control" name="" value="${s.createDate}" readonly>
                              <textarea id="updateContent${i}" class="form-control" placeholder="Write Something" rows="8" cols="40" name="" required="required">${s.content}</textarea>
                            </div>
                            <div class="col-lg-7" style="align-items: center;">
                              <img style="max-width: 75%;max-height: 350px;" alt="No image available for this Post!" src="image/display/${s.id}" onerror="this.src='resources/images/no-image.png'" />
                              <p class="edit-error_for_empty"></p>
                            </div>
                          </div>
                        </form>
                        <div style="word-spacing: 150px;">
                          <button id="update${i}" class="button" value="${i}" onClick="update(this.value)" type="submit">Update</button>
                          <button class="button" value="${i}" onClick="findieditclose(this.value)">Cancel</button>
                        </div>
                      </div>
                    </div>



                    <div id="comment-modal${i}" style="background-color: rgba(0, 0, 0, 0.8);width: 100%;height: 100%;position: fixed;
							       left: 0px; top: 0px;display: none;justify-content: center;	align-items: center;">
                      <div class="row" style="width: 100%">
                        <h3 style="color:white;">Comments</h3>
                        <div class="modal-comment-edit col-lg-6">


                          <form class="commentPost comment-form">
                            <input type="text" class="form-control" name="" value="${s.account}" readonly>
                            <input type="hidden" class="form-control" id="commentId${i}" name="" value="${s.id}" readonly>
                            <input type="hidden" class="form-control" id="commentatorId${i}" name="" value="${s.id}" readonly>
                            <img style="max-width: 75%;max-height: 350px;" alt="No image available for this Post!" src="image/display/${s.id}" onerror="this.src='resources/images/no-image.png'" />
                            <div>
                              <input id="comment${i}" class="form-control" placeholder="Comment here" required="required" autocomplete="off">
                              <div id="error_for_empty_comment${i}" class="text-center" style="color:red;"></div>
                            </div>
                          </form>
                          <div style="word-spacing: 150px;">
                            <button id="commentbtn${i}" class="button" value="${i}" onClick="comment(this.value)" type="submit">Comment</button>
                            <button class="button" value="${i}" onClick="findicommentclose(this.value)">Close</button>
                          </div>
                        </div>
                        <div class="modal-comments col-lg-6">
                          <j:forEach items="${s.comments}" var="c">
                            <div class="eachcom">
                              <div class="row" style="margin-bottom:20px;">
                                <div class="col-lg-6" style="text-align: left;font-weight: bolder;">
                                  ${c.commentator}
                                </div>
                                <div class="col-lg-6">
                                  ${c.commentDate}
                                </div>
                              </div>
                              <p>${c.comment}</p>
                            </div>
                          </j:forEach>

                        </div>
                      </div>
                    </div>


                  </j:when>
                  <j:otherwise>
                    <div class="options col-lg-2">
                      <div class=comment>
                        <form class="commentPostbtn" action="">
                          <input type="hidden" class="comment-form-control" id="commentacc${i}" value="${s.id}" readonly>
                          <button id="commentpop${i}" class="button" value="${i}" onClick="findicomment(this.value)" type="submit">Comment</button>
                        </form>
                      </div>
                    </div>
                    <div id="comment-modal${i}" style="background-color: rgba(0, 0, 0, 0.8);width: 100%;height: 100%;position: fixed;
							       left: 0px; top: 0px;display: none;justify-content: center;	align-items: center;">
                      <div class="row" style="width: 100%">
                        <h3 style="color:white;">Comments</h3>
                        <div class="modal-comment-edit col-lg-6">


                          <form class="commentPost comment-form">
                            <input type="text" class="form-control" name="" value="${s.account}" readonly>
                            <input type="hidden" class="form-control" id="commentId${i}" name="" value="${s.id}" readonly>
                            <img style="max-width: 75%;max-height: 350px;" alt="No image available for this Post!" src="image/display/${s.id}" onerror="this.src='resources/images/no-image.png'" />
                            <div>
                              <input id="comment${i}" class="form-control" placeholder="Comment here" required="required" autocomplete="off">
                              <div id="error_for_empty_comment${i}" class="text-center" style="color:red;"></div>
                            </div>
                          </form>
                          <div style="word-spacing: 150px;">
                            <button id="commentbtn${i}" class="button" value="${i}" onClick="comment(this.value)" type="submit">Comment</button>
                            <button class="button" value="${i}" onClick="findicommentclose(this.value)">Close</button>
                          </div>
                        </div>
                        <div class="modal-comments col-lg-6">
                          <j:forEach items="${s.comments}" var="c">
                            <div class="eachcom">
                              <div class="row" style="margin-bottom:20px;">
                                <div class="col-lg-6" style="text-align: left;font-weight: bolder;">
                                  ${c.commentator}
                                </div>
                                <div class="col-lg-6">
                                  ${c.commentDate}
                                </div>
                              </div>
                              <p>${c.comment}</p>
                            </div>
                          </j:forEach>

                        </div>
                      </div>
                    </div>

                  </j:otherwise>
                </j:choose>

                   
              </div>

            </div>
            <%++i;%>
            <hr>
          </j:forEach>
        </div>
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