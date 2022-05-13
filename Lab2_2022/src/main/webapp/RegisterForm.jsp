<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>

<link rel=”stylesheet” href=”https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css”rel=”nofollow” integrity=”sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm” crossorigin=”anonymous”>


<meta charset="UTF-8">
<title> Form </title>
<style>
* {
	margin-left: 10px;
}

input, select {
	margin-top: 5px;
	margin-bottom: 20px;
	margin-left: 20px;
}

input:valid {
	border-left: 6px solid green;
}

input:invalid {
	border-left: 6px solid red;
}

label {
	font-weight: bold;
	color: #444444;
}

button {
	border-radius: 6px;
	margin-left: 50px;
	margin-top: 10px;
	background-color: #3CAF40; /* Green */
	border: none;
	color: white;
	padding: 10px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	transition-duration: 0.4s;
  	margin-bottom:30px;
}

button:hover {
  	background-color: #6CCF70; /* Green */
  	color: white;
}

select {
	border-radius: 6px;
}

select option{
  	color: black;
}

#errors{
	color: white;
	font-weight:bold;
  	background-color: #EC2A57;
  	padding: 5px;
	border-radius: 10px;
	margin-bottom: 20px;
	border:3px solid black;
}

</style>
</head>
<body>

<img src="imgs/FunnierSmall.png" alt= "logo" style="height:100px;">
<h2>Registration:</h2>


<div id="errors">
	<ul>
	<c:if test = "${model.error[0]}">
		<li> Entered user name has been already registered </li>
	</c:if>
	<c:if test = "${model.error[1]}">
		<li> Entered user name must contain only alphanumeric or underscore “_” special character and be as maximum 10 characters long </li>
	</c:if>
	<c:if test = "${model.error[2]}">
		<li> Entered email has been already registered </li>
	</c:if>
	<c:if test = "${model.error[3]}">
		<li> Entered email must be valid and less than 50 characters long </li>
	</c:if>
	<c:if test = "${model.error[4]}">
		<li> Entered password must have between 8 and 50 characters, containing at least one lowercase, one uppercase, a number and a special character </li>
	</c:if>
	<c:if test = "${model.error[5]}">
		<li> The password must match </li>
	</c:if>
	<c:if test = "${model.error[6]}">
		<li> Nickname must be between 1 and 30 characters long </li>
	</c:if>
	<c:if test = "${model.error[7]}">
		<li> Gender must be one of the options </li>
	</c:if>
	<c:if test = "${model.error[8]}">
		<li> You must be +16 years-old to be able to register </li>
	</c:if>
	</ul>

</div>

<form action="RegisterController" method="post" id="myform">
  <label for="username"> User name:</label><br>
  <input type="text" id="username" name="username" placeholder="Name" onfocusout="checkUniqueness('username')" value="${model.username}" required pattern="^[a-zA-Z\d_]{1,10}$"><br>
  <label for="mail"> Mail:</label><br>
  <input type="email" id="mail" name="mail" placeholder="Mail" onfocusout="checkUniqueness('mail')" value="${model.mail}" required pattern="^.{8,50}$"><br>
  <label for="pwd1"> Password: </label><br>
  <input type="password" id="pwd1" name="pwd1" placeholder="Password" value="${model.pwd1}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%?&_+\-*\/#])[A-Za-z\d@$!%?&_+\-*\/#]{8,50}$"><br>
  <label for="pwd2"> Confirm Password: </label><br>
  <input type="password" id="pwd2" name="pwd2" placeholder="Confirm Password" value="${model.pwd2}" required><br>
  <label for="nickname"> Nickname:</label><br>
  <input type="text" id="nickname" name="nickname" placeholder="Nickname" value="${model.nickname}" required pattern="^.{1,30}$"><br>
  <label for="gender"> Gender:</label><br>
  <select id="gender" name="gender">
  	<option value="male">Male</option>
  	<option value="female">Female</option>
  	<option value="notsay">Prefer not to say</option>
  	<option value="other">Other</option>
  </select><br>
  <label for="birth"> Date of birth:</label><br>
  <input type="date" id="birth" name="birth" placeholder="Birth" value="${model.birth}" min="1900-01-01" max="2500-01-01" required pattern="^\d{4}-\d{2}-\d{2}$"><br>
  <button> Submit </button>
</form>
<script>


const form = document.getElementById("myform");
const username = document.getElementById("username");
const email = document.getElementById("mail");
const pwd1 = document.getElementById("pwd1");
const pwd2 = document.getElementById("pwd2");
const gender = document.getElementById("gender");
const birth = document.getElementById("birth");

if ("${model.gender}".length > 0) {
	gender.value = "${model.gender}";
}

if ("${model.birth}".length > 0) {
	birth.value = (new Date("${model.birth}")).toISOString().substring(0,10);
}

var errors = function() {
	for(e of "${model.error}") {
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

var checkPasswordValidity = function() {
	 if (pwd2.value !== pwd1.value ) {
		pwd2.setCustomValidity("Passwords must match!");
	} else {
		pwd2.setCustomValidity("");
	}
}

var checkUniqueness = function(inputID) {
	var input;
	if(inputID == "username")
		input = username;
	else if(inputID == "mail")
		input = email;
	else
		return;
	
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            uniqueness = this.response;
           	if(uniqueness == "unique"){
				input.setCustomValidity("");           		
           		return;
           	}
           	if(uniqueness == "not-unique") {
           		input.setCustomValidity("Value already registered, use another " + inputID);
           		return;
           	}
        }
    };
    xhttp.open("GET", "CheckUniquenessController?columnName="+inputID+"&columnValue="+input.value, true);
    //xhttp.send(JSON.stringify({"columnName": inputID, "columnValue": input.value}));
    xhttp.send();
}

email.addEventListener("input", function (event) {
  if (email.validity.typeMismatch) {
    email.setCustomValidity("I am expecting an e-mail address!");
  } else {
    email.setCustomValidity("");
  }
});

pwd2.addEventListener("input", checkPasswordValidity, false);

form.addEventListener("submit", function (event) {
	checkPasswordValidity();
	if (!this.checkValidity()) {
		this.reportValidity();
		event.preventDefault();
	} 
}, false);


</script>
</body>
</html>