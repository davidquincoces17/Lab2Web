<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="LoginController" method="POST">
    <label class="w3-text-red" for="mail"> Mail:</label><br>
  	<input class="w3-input w3-border w3-light-grey" type="email" id="mail" name="mail" placeholder="Mail" onfocusout="checkUniqueness('mail')" value="${login.mail}" required pattern="^.{8,50}$"><br>
    <label class="w3-text-red" for="pwd1"> Password: </label><br>
  	<input class="w3-input w3-border w3-light-grey" type="password" id="pwd1" name="pwd1" placeholder="Password" value="${login.pwd1}" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%?&_+\-*\/#])[A-Za-z\d@$!%?&_+\-*\/#]{8,50}$"><br>
    <input class="w3-btn w3-red" type="submit" name="sumbit" value="Submit">
</form>
