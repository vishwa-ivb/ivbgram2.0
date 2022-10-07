<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isELIgnored="false" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <title>Home</title>
  <script src="https://kit.fontawesome.com/8132e3a9c0.js" crossorigin="anonymous"></script>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="resources/css/styleshome.css">
  <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>

<body>
  <div class="usergreeting row">
  	<div class="propicdiv col-lg-1">
				<img id="propic" alt="No image available for this Post!" 
				src="/profile/picture/${profile.pid}" 
				onerror="this.src='resources/images/profileicon.png'" />
			</div>
			<div class="col-lg-9" style="padding: 0;display: flex;align-items: center;">
			<div class="row">
  			<div class="col-lg-12">
				<h5>Welcome </h5>
			</div>
			<div class="col-lg-12">
				<h3><b><i>${user}!</i></b></h3>
			</div></div>
   				 <form id="profileform" action="/myprofile" method="post">
   					 <input type="hidden" class="form-control" id="account" name="account" value="${user}" readonly>
   				 </form>
   
  </div></div>
  <div class="home container-fluid">
    <div id="successupload" class="text-center" style="color:white; font-weight: bolder; background-color: green"></div>
    <div id="error" class="text-center" style="color:red;"></div>
    <div class="row">

      <div class="col-lg-2 tools">
        <h2 id="tools-heading">Tools</h2>
        <br><br>
        <a type="button" id="refresh" onClick="window.location.reload();">Refresh</a><br><br>
        <a type="button" id="getpop">Upload</a><br><br>
        
<%--         	<input type="hidden" id="userprofile" value="${user}" readonly> --%>
        
        <a type="submit" id="profile" onClick="showprofile();">Profile</a><br><br>
        <a href="/" type="button" id="logout" onClick="window.history.forward(1);">Logout</a><br><br>
       
      </div>

      <div class="col-lg-7 content">
        <h2 id="content-heading">Content</h2>

        <div id="post" style="max-height: 550px;">
          <%int i=1;%>
          <j:forEach items="${images}" var="s">
            <j:set var="i" value="<%=i%>"></j:set>
            <div id="post${i}" class="container-fluid">
              <div id="success${i}" class="text-center" style="color:white; font-weight: bolder; background-color: green"></div>
              <div class="row">
                <div class="col-lg-6 imgcol">

                  <img alt="No image available for this Post!" src="/image/display/${s.id}" onerror="this.src='resources/images/no-image.png'" />
<%-- 				<img alt="No image available for this Post!" src="${s.image}"/> --%>
                </div>
                <div class="col-lg-4">
                  <p>${s.id}</p><br>
                  <p>${s.account}</p><br>
                  <p>${s.content}</p><br>
                  <p>${s.createDate}</p><br>
                </div>

                <j:choose>
                  <j:when test="${s.account == user}">
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

      <div class="col-lg-3 search">
        <h2 id="search-heading">Search</h2>
        <br><br>
        <form id="sp"class="searchPostbtn" action="/search" method="post">
        <div class="row" style="align-items: center">
          <div class="col-lg-10" style="padding-left: 10px;padding-right: 0;">
            <input type="text" class="" placeholder="search" name="searchkey" id="searchkey">
            <input type="hidden" class="" name="searchinguser" id="searchinguser" value="${user}">
          </div>
          <div class="col-lg-2" style="padding-left: 0;">
<!--             <a type="button" id="searchpopup" onClick="searchpop();"><i class="fa-solid fa-magnifying-glass searchicon"></i></a>
 -->            <a onclick="spop()" type="button" id="searchpopup"><i class="fa-solid fa-magnifying-glass searchicon"></i></a>
          </div>
        </div>
        </form>
        <br><br>
<!--         <a type="hidden" id="usersearch" onClick="window.location.reload();">User</a><br><br> -->
<!--         <a type="hidden" id="postsearch" onClick="window.location.reload();">Post</a><br><br> -->
      </div>

    </div>
    <div id="modal-searchresult-background">
    	<div id="modal-searchresult" >
        <div class="close-search">+</div>
        <h3>Search Result</h3>
        <p>hii ${testing}</p>
        
<%--          <j:forEach items="${sr}" var="r"> --%>
<%--               <p>${r.account}</p>         --%>
<%-- 		</j:forEach> --%>
      </div>
    </div>
      
      
    <!--  input receiving model  -->
    <div class="input-modal">
      <div class="modal-contents">
        <div class="close">+</div>
        <h3>Upload</h3>
        <form id="form" action="">
          <input type="text" class="form-control" id="account" name="account" value="${user}" readonly>
          <textarea class="form-control" placeholder="Write Something" id="content" rows="3" cols="45" name="content" required="required"></textarea>
          <input type="file" class="form-control" placeholder="" name="image" id="image" required="required">
          <p id="error_for_empty"></p>
          <button id="submit" class="button" type="submit">Post</button>
        </form>
      </div>
    </div>
  </div>

  <script src="resources/js/home.js"></script>
</body>

</html>