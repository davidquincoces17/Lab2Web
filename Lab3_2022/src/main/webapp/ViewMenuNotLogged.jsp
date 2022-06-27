<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
    
<div class="w3-bar primary-color">
	<a class="w3-bar-item w3-button" id="LogoutController" href="MainController"> <i class="fa fa-home" aria-hidden="true"></i> </a>
	<a class="menu w3-bar-item w3-button w3-hide-small" id="RegisterController" href=#> Registration </a> 
	<a class="menu w3-bar-item w3-button w3-hide-small" id="LoginController" href="MainController"> Login </a> 
	<a class= "w3-right" href="MainController"> <img src="imgs/SimpleLogo.png" alt= "logo" style="height:34px;padding-bottom:4px"></a><br>
	<a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
</div>

<div id="stack" class="w3-bar-block w3-red w3-hide w3-hide-large w3-hide-medium">
	<a class="menu w3-bar-item w3-button" id="RegisterController" href=#> Registration </a> 
	<a class="menu w3-bar-item w3-button" id="LoginController" href=#> Login </a> 
</div>
