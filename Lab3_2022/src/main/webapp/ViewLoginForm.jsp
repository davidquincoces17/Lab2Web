<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div style="background-color: #fff5ee; border-radius: 5px; margin-right:25%;margin-top:20px">
	<a href="MainController"> <img src="imgs/FunnierSmall.png"
		alt="logo" style="height: 80px; margin-bottom: 20px; margin-top: 20px; margin-left:20px"></a><br>

	<c:if test="${failed}">
		<div style="background-color: #F4D45E"
			class="w3-panel w3-animate-opacity w3-theme-l4 w3-display-container">
			<span onclick="this.parentElement.style.display='none'"
				class="w3-button w3-large w3-display-topright">&times;</span>
			<h3>Identification error</h3>
			<p>Provided credentials do not match our database.</p>
		</div>
	</c:if>

	<form style="padding: 20px" action="LoginController" method="POST">
		<label class="w3-text-red" for="mail"> Mail:</label><br>
		<input class="w3-input w3-border w3-light-grey" type="email" id="mail" name="mail" placeholder="Mail" value="${user.mail}" required pattern="^.{8,50}$"><br>
		<label class="w3-text-red" for="pwd1"> Password: </label>
			<br> 
		<input class="w3-input w3-border w3-light-grey" type="password" id="pwd1" name="pwd1" placeholder="Password" value="${user.pwd1}" required pattern="^[A-Za-z\d@$!%?&_+\-*\/#]{8,50}$"><br>
		<input class="w3-btn w3-red" style="border-radius: 8px" type="submit" name="sumbit" value="Login">
		<label class="w3-text-red"style="padding-left: 10px"> Don't have an account? You can register </label>
		<a class="w3-text-red menu" id="RegisterController" href=#><b>here</b></a><br><br>
		<label class="w3-text-red"><small>Don't want to register? You can check the </small></label>
		<a class="w3-text-red menu" id="RegisterController" href=#><b><small>global timeline</small></b></a>
		<label class="w3-text-red"><small> with the most recent funnies!</small></label>

		

		<br>



	</form>
</div>

<div id="errors">
	<ul>
		<c:if test="${user.error[3] || user.error[4]}">
			<li>Invalid credentials format</li>
		</c:if>
		<c:if test="${user.error[9]}">
			<li>Invalid credentials</li>
		</c:if>
	</ul>
</div>

<script>
$(document).ready(function(){
    $('#navigation').load('MenuController');
 });
$('#lrow1').html("<p></p>");
$('#lrow2').html("<p></p>");

var errors = function() {
	for(e of "${user.error}") {
		if (e == true)
			return true;
	}
	return false;
}

if (!errors()) {
	document.getElementById("errors").style.display = 'none';
} else {
	document.getElementById("errors").style.display = 'block';
}
</script>
