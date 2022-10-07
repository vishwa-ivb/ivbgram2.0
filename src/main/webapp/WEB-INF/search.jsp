<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
<script src="https://kit.fontawesome.com/8132e3a9c0.js" crossorigin="anonymous"></script>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
  
<link rel="stylesheet" href="resources/css/stylessearch.css">
  <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>
<body>
	<div id="modal-search-background">
	<form id="userhome" action="/userhome" method="post">
		<input type="hidden" class="" name="searchinguser" id="searchinguser" value="${searchinguser}">
	</form>
	<a type="submit" class="homebtn" onClick="userhome();"><i class="fa-solid fa-house-user"></i></a>
	<div class="row" style="align-items: center;text-align: -webkit-center;margin-top: 0;margin-right: 0;margin-left: 0;">
		<div class="col-lg-12">
		<div class="searchbar">
			<form id="sp"class="searchPostbtn" action="/search" method="post">
		        <div class="row" style="align-items: center">
		          <div class="col-lg-10" style="padding-left: 10px;padding-right: 0;">
		          		<input type="hidden" class="" name="searchinguser" id="searchinguser" value="${searchinguser}">
		            	<input type="text" class="" placeholder="search" name="searchkey" id="searchkey" style="width: 100%;">
		          </div>
		          <div class="col-lg-2" style="">
		                 <a onclick="spop()" type="button" id="searchpopup" style="padding: 6px;"><i class="fa-solid fa-magnifying-glass searchicon"></i></a>
		          </div>
		        </div>
		
        </form>
		</div></div>
		<div class="col-lg-12">
   		<div class="search">
			
        
        <div class="content">
        <h2 id="content-heading">Search Results for "${searchkey}" <a style="font-size: 20px;">(${totalnum} results)</a> </h2>

        <div id="post">
        	${noresults}
<%--         	<a id="noresults">${noresults}</a> --%>
          <%int i=1;%>
          <j:forEach items="${searchresult}" var="s">
            <j:set var="i" value="<%=i%>"></j:set>
            <div id="post${i}" class="container-fluid">
              <div id="success${i}" class="text-center" style="color:white; font-weight: bolder; background-color: green"></div>
              <div class="row">
                <div class="col-lg-6 imgcol">

                  <img alt="No image available for this Post!" src="image/display/${s.id}" onerror="this.src='resources/images/no-image.png'" />
                  <%-- 				        	<img alt="No image available for this Post!" src="/IvbBoot2/src/main/webapp/resources/images//${imageaddress}"/> --%>
                </div>
                <div class="col-lg-4">
                  <p>${s.id}</p><br>
                  <p>${s.account}</p><br>
                  <p>${s.content}</p><br>
                  <p>${s.createDate}</p><br>
                </div>

                    <div class="col-lg-2" style="display: flex;align-items: center;">
                      <div class=comment>
                        <form class="commentPostbtn" action="">
                          <input type="hidden" class="comment-form-control" id="commentacc${i}" value="${s.id}" readonly>
                          <button id="commentpop${i}" class="button" value="${i}" onClick="findicomment(this.value)" type="submit">Comment</button>
                        </form>
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
                            <img style="max-width: 75%;max-height: 350px;" alt="No image available for this Post!" src="http://localhost:8080/image/display/${s.id}?" onerror="this.src='resources/images/no-image.png'" />
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

     
                

            

            </div>
            <%++i;%>
            <hr>
          </j:forEach>
        </div>
      </div>
      </div>
      </div></div>
   </div>
    <script src="resources/js/home.js"></script>
</body>
</html>