<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w3-container w3-card w3-round w3-white w3-section w3-center">
  <h4>My Profile</h4>
  <p><img src="imgs/me.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>

  <p class="w3-left-align"> <i class="fa fa-fw w3-margin-right"></i> ${user.nickname} </p>
  <p class="w3-left-align"> <i class="fa fa-fw w3-margin-right"></i> @${user.username} </p>
  <hr>
  <button type="button" class="editUser w3-row w3-button w3-green w3-section"><i class="fa fa-user-plus"></i> &nbsp;Edit</button> 
 </div>
<br>