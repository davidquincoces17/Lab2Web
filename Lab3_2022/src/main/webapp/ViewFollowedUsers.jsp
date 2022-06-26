<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
	<h2>My friends</h2>
</div>
<c:forEach var="u" items="${users}">       
<div id="${u.id}" class="w3-container w3-card w3-round w3-white w3-left w3-section" style="margin-right:20px; width:150px; height:250px">
<!-- 	<p>My Friends</p> -->
    <img class="w3-circle" src="${u.profilePhoto}" style="max-width:75px;width:70%;margin-top:35px;margin-left:25px" alt="Avatar"><br><br>
    <div style="text-align:center">@${u.username}</div>
   	<button type="button" class="unfollowUser w3-row w3-button w3-green w3-section" style="border-radius:8px"><i class="fa fa-user-plus"></i> &nbsp;Unfollow</button> 
</div>
</c:forEach>
