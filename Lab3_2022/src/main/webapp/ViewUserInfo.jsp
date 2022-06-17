<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w3-container  w3-white w3-section">
<div style="display:flex;margin-top:30px" >
  <div > <img src="imgs/me.png;" class="w3-circle" style="height:106px;width:106px;margin-right:10px" alt="Avatar"></div>
  <div style="margin-top:30px;display:block"> <b> ${user.nickname}</b><br>
  <span class="w3-opacity"> @${user.username} </span><br>
  <button type="button" style="height:25px;width:55px;font-size:12px;padding-top:0px;padding-bottom:0px;padding-left:0px;padding-right:0px" class="editUser w3-button w3-white w3-border  w3-round-large"><i class="fa fa-user"></i> &nbsp;Edit</button>
  <button type="button" style="height:25px;width:25px;font-size:12px;padding-top:0px;padding-bottom:0px;padding-left:0px;padding-right:0px" class="editUser w3-button w3-white w3-border  w3-round-large"><i class="fa fa-user-plus"></i></button>
  </div>
  </div>
</div>

<br>