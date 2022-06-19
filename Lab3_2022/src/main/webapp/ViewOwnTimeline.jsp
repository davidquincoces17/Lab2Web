<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	$('#lrow1').load('GetUserInfo');
	$('#lrow2').load('GetNotFollowedUsers');
	$('#iterator').load('GetFollowedFunnies');
 });
</script>

<div class="w3-container w3-card w3-round w3-white w3-section">
	<h3 class="w3-opacity" style="font-size:20px"> Hello ${user.username}, would you like to share some Funny? </h3>
	<p id="funnyContent" contenteditable="true" class="w3-border w3-padding"> </p>
	<button id="addFunny" type="button" class="w3-button w3-theme w3-section"><i class="fa fa-pencil"></i> &nbsp;Post</button> 
</div>
 
<div id="iterator">
</div>
 


