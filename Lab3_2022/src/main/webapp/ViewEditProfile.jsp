<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
	<h2>Edit profile</h2>
</div>

<form action="EditProfileController" method="post" id="editProfilePicture">
  <label for="img">Select profile picture:</label>
  <input type="file" id="img" name="${user.profilePhoto}" accept="image/*">
  <input type="submit">
</form>
<br>
<form action="EditProfileController" method="post" id="editProfilePicture">
  <label class="w3-text-red" for="pwd1"> Password: </label><br>
  <input class="w3-input w3-border w3-light-grey" type="password" id="pwd1" name="pwd1" placeholder="Password" value="${model.pwd1}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%?&_+\-*\/#])[A-Za-z\d@$!%?&_+\-*\/#]{8,50}$"><br>
  <label class="w3-text-red" for="pwd2"> Confirm Password: </label><br>
  <input class="w3-input w3-border w3-light-grey" type="password" id="pwd2" name="pwd2" placeholder="Confirm Password" value="${model.pwd2}" required><br>
  <label class="w3-text-red" for="nickname"> Nickname:</label><br>
  <input class="w3-input w3-border w3-light-grey" type="text" id="nickname" name="nickname" placeholder="Nickname" value="${model.nickname}" required pattern="^.{1,30}$"><br>
  <label class="w3-text-red" for="gender"> Gender:</label><br>
  <select class="w3-select" id="gender" name="gender">
  	<option value="1">Male</option>
  	<option value="2">Female</option>
  	<option value="3">Prefer not to say</option>
  	<option value="4">Other</option>
  </select><br><br>
  <label class="w3-text-red" for="birth"> Date of birth:</label><br>
  <input class="w3-input w3-border w3-light-grey" type="date" id="birth" name="birth" placeholder="Birth" value="${model.birth}" min="1900-01-01" max="2500-01-01" required pattern="^\d{4}-\d{2}-\d{2}$"><br>
  <button class="w3-btn w3-red" style="border-radius:8px"> Update Profile </button>
</form>