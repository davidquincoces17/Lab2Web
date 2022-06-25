<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="imgs/favicon_funnier.png">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> Lab 3 FUNNIER </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./css/style.css">

<script type="text/javascript">
$(document).ready(function(){
	$.ajaxSetup({ cache: false }); //Avoids Internet Explorer caching!	
	$(document).on("click",".menu", async function(event) {
		//$('#content').load('ContentController',{content: $(this).attr('id')});
		const response = await fetch($(this).attr('id'));
		$('#content').html(await response.text());
		//$('#content').load($(this).attr('id'));
		event.preventDefault();
	});
	
	$(document).on("submit","form", function(event) {
		$('#content').load($(this).attr('action'),$(this).serialize());
	    event.preventDefault();
	});
	
	/* Add funny */
	$(document).on("click","#addFunny",function(event){
		$.post( "AddFunny", { content: $("#funnyContent").text()}, function(event) {
			$("#content").load("GetOwnTimeline");
		});
		event.preventDefault();
	});
	
	/* Delete funny */
	$(document).on("click",".delTweet",function(event){
		var funny = $(this).parent();
		$.post( "DelFunny", { id: funny.attr("id") } , function(event) {
			$("#content").load("GetOwnTimeline");				
		});
		event.preventDefault();
	});
	
	/* Like funny */
	$(document).on("click",".likeTweet",function(event){
		var funny = $(this).parent();
		$.post( "LikeFunny", { id: funny.attr("id") } , function(event) {
			$("#content").load("GetOwnTimeline");				
		});
		event.preventDefault();
	});
	
	
	/* Follow user */
	$(document).on("click",".followUser",function(event){
		var user = $(this).parent();
		$.post( "FollowUser", { id: user.attr("id") }, function(event) { 
			$("#content").load("GetFollowedUsers");
		    $('#lrow2').load('GetNotFollowedUsers');
		});
		event.preventDefault();
	});
	$(document).on("click",".unfollowUser",function(event) {
		var user = $(this).parent();
		$.post( "UnFollowUser", { id: user.attr("id") }, function(event) {
			$("#content").load("GetFollowedUsers");
		    $('#lrow2').load('GetNotFollowedUsers');
		});
		event.preventDefault();
	});
});
</script>
</head>
<body>

 	<!-- Begin Navigation -->
 	<div class="w3-bar primary-color" id="navigation">
    <jsp:include page="${menu}" />
 	</div>
 	<!-- End Navigation -->
 
	<!-- Begin Content -->
	<div class="w3-row-padding">
	 	<!-- Left Column -->
		<div class="w3-container w3-col m3 w3-hide-small">
			<div id="lcolumn">
				<div id="lrow1">
					<p></p>
				</div>
				<div id="lrow2">
					<p></p>
				</div>
			</div>
			
		</div>
		<!-- Middle Column -->	
		<div class="e3-container w3-col m8">
			<div id="content">
			<jsp:include page="${content}" />
			</div>
		</div>
	</div>
	<!-- End Content -->
	<!-- Footer -->
	<footer class="w3-container w3-theme">
	  <p> Universitat Pompeu Fabra </p>
	</footer>
	
	<script>
		function stack() {
  			var x = document.getElementById("stack");
  			if (x.className.indexOf("w3-show") == -1) {
    			x.className += " w3-show";
  			} else { 
    		x.className = x.className.replace(" w3-show", "");
  			}
		}
	</script>

  </body>
</html>