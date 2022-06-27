<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<div style="background-color: #fff5ee; border-radius: 5px; margin-right:25%;margin-top:20px;margin-bottom:30px; border:dashed 1px #F5B354">

<a href="MainController"> <img src="imgs/FunnierSmall.png" alt= "logo" style="height: 80px; margin-bottom: 20px; margin-top: 20px; margin-left:20px"></a>


<form style="padding: 20px" action="RegisterController" method="post" id="myform">
  <label class="w3-text-red" style="font-size:18px" for="username"> User name:</label><br>
  <input class="w3-input w3-border w3-light-grey" type="text" id="username" name="username" placeholder="Name" onfocusout="checkUniqueness('username')" value="${model.username}" required pattern="^[a-zA-Z\d_]{1,10}$"><br>
  <label class="w3-text-red" style="font-size:18px" for="mail"> Mail:</label><br>
  <input class="w3-input w3-border w3-light-grey" type="email" id="mail" name="mail" placeholder="Mail" onfocusout="checkUniqueness('email')" value="${model.mail}" required pattern="^.{8,50}$"><br>
  <label class="w3-text-red" style="font-size:18px" for="pwd1"> Password: </label><br>
  <input class="w3-input w3-border w3-light-grey" type="password" id="pwd1" name="pwd1" placeholder="Password" value="${model.pwd1}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%?&_+\-*\/#])[A-Za-z\d@$!%?&_+\-*\/#]{8,50}$"><br>
  <label class="w3-text-red" style="font-size:18px" for="pwd2"> Confirm Password: </label><br>
  <input class="w3-input w3-border w3-light-grey" type="password" id="pwd2" name="pwd2" placeholder="Confirm Password" value="${model.pwd2}" required><br>
  <label class="w3-text-red" style="font-size:18px" for="nickname"> Nickname:</label><br>
  <input class="w3-input w3-border w3-light-grey" type="text" id="nickname" name="nickname" placeholder="Nickname" value="${model.nickname}" required pattern="^.{1,30}$"><br>
  <label class="w3-text-red" style="font-size:18px" for="gender"> Gender:</label><br>
  <select class="w3-select" id="gender" name="gender">
  	<option value="1">Male</option>
  	<option value="2">Female</option>
  	<option value="3">Prefer not to say</option>
  	<option value="4">Other</option>
  </select><br><br>
  <label class="w3-text-red" style="font-size:18px" for="birth"> Date of birth:</label><br>
  <input class="w3-input w3-border w3-light-grey" type="date" id="birth" name="birth" placeholder="Birth" value="${model.birth}" min="1900-01-01" max="2500-01-01" required pattern="^\d{4}-\d{2}-\d{2}$"><br>
  <button class="w3-btn w3-red" style="border-radius:8px"> Sign up </button>
</form>


</div>

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

<script>
const form = document.getElementById("myform");
const username = document.getElementById("username");
const email = document.getElementById("mail");
const pwd1 = document.getElementById("pwd1");
const pwd2 = document.getElementById("pwd2");
const gender = document.getElementById("gender");
const birth = document.getElementById("birth");
const nickname = document.getElementById("nickname");

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
	else if(inputID == "email")
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