<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="${funny.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="${user.profilePhoto}" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${fn:substring(funny.timestamp,0,19)} </span>
   <div style=display:flex>   
   <h4> ${user.nickname} </h4>
   <h6><span class="w3-opacity" style="font-size: 20px; opacity: 0.4"> &nbsp @${user.username} </span></h6>
   </div>
   <hr class="w3-clear">
   <p style="overflow-wrap: break-word"> ${funny.content} </p>


	<div class="w3-container w3-card w3-round w3-white w3-section">
   <form action="EditFunnyController" id="regform" method="POST">
	<h6 class="w3-opacity"> Replace your funny: </h6>
	<input type="hidden" id="id" name="id" placeholder="funny id" value="${funny.id}" >
	<input class="w3-input w3-border w3-light-grey" id="content" name="content" placeholder="Update Funny" value="${funny.content}" required></p>
    <p>
<!-- 	<input class="w3-button w3-theme w3-green w3-section" style="border-radius:8px" type="submit" name="sumbit" value="Edit"></p> -->
	<button id="" type="submit" class="w3-button w3-theme w3-green w3-section" name="submit" style="border-radius:8px"><i class="fa fa-pencil"></i> &nbsp;Edit</button>
	</form>
</div>


 </div>

