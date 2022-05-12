<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Form </title>
<style>
input, select {
	margin-bottom: 10px;
}

input:valid {
	border-left: 4px solid green;
}
input:invalid {
	border-left: 4px solid red;
}

label {
	font-weight:bold;
	color:darkgrey;
}

</style>
</head>
<body>

<h1>Funnier</h1>
<h2>Registration:</h2>

<ul>
<c:if test = "${model.error[0]}">
	<li> Entered user name has been already registered </li>
</c:if>
<c:if test = "${model.error[1]}">
	<li> Entered email has been already registered </li>
</c:if>
</ul>

<form action="RegisterController" method="post" id="myform">
  <label for="username"> User name:</label><br>
  <input type="text" id="username" name="username" placeholder="Name" value="${model.username}" required pattern="^[a-zA-Z\d_]{1,10}$"><br>
  <label for="mail"> Mail:</label><br>
  <input type="email" id="mail" name="mail" placeholder="Mail" value="${model.mail}" required pattern="^.{8,50}$"><br>
  <label for="pwd1"> Password: </label><br>
  <input type="password" id="pwd1" name="pwd1" placeholder="Password" value="${model.pwd1}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%?&_+\-*\/ñÑçÇ#])[A-Za-z\d@$!%?&_+\-*\/ñÑçÇ#]{8,50}$"><br>
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
  <input type="date" id="birth" name="birth" placeholder="Birth" value="${model.birth}" min="1900-01-01" max="2500-01-01"><br>
  <button> Submit </button>
</form>
<script>


const form = document.getElementById("myform");
const email = document.getElementById("mail");
const pwd1 = document.getElementById("pwd1");
const pwd2 = document.getElementById("pwd2");
const gender = document.getElementById("gender");
const birth = document.getElementById("birth");

if ("${model.gender}".length > 0) {
	gender.value = "${model.gender}";
}

if ("${model.birth}".length > 0) {
	//var date = new Date("${model.birth}");
	birth.value = (new Date("${model.birth}")).toISOString().substring(0,10);
}

var checkPasswordValidity = function() {
	 if (pwd2.value !== pwd1.value ) {
		pwd2.setCustomValidity("Passwords must match!");
	} else {
		pwd2.setCustomValidity("");
	}
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