<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w3-container  w3-white w3-section">
<div style="display:flex;margin-top:20px" >
  <div > <img src="${user.profilePhoto}" class="w3-circle" style="height:100px;width:100px;max-width:120px;margin-right:10px" alt="Avatar"></div>
  <div style="margin-top:20px;display:block">
  	<div style="font-size:24px">
  		<b> &nbsp &nbsp ${user.nickname}</b>
  	</div>
  	<div>
  		<span class="w3-opacity"> &nbsp &nbsp &nbsp @${user.username} </span>
  	</div><br>
  	<div style=padding-left:20px>
  		<button type="button" style="height:35px;width:90px;font-size:12px;padding-top:0px;padding-bottom:0px;padding-left:0px;padding-right:0px" class="editUser w3-button w3-white w3-border  w3-round-large"><i class="fa fa-user fa-2x"></i> &nbsp;Edit</button>
  		<button type="button" style="height:35px;width:40px;font-size:12px;padding-top:0px;padding-bottom:0px;padding-left:0px;padding-right:0px" class="editUser w3-button w3-white w3-border  w3-round-large"><i class="fa fa-user-plus fa-2x"></i></button>
  	</div>
  </div>
  </div>
</div>

<br>