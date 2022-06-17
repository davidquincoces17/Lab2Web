<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<div class="w3-bar primary-color">
	<a class="w3-bar-item w3-button" href="MainController"> <i class="fa fa-home" aria-hidden="true"></i> </a>
	<a class="menu w3-bar-item w3-button w3-hide-small" id="GetTopFunnies" href=#> Top Funnies </a>
	<a class="menu w3-bar-item w3-button w3-hide-small" id="GetFollowedUsers" href=#> Friends </a>
	<input class="w3-bar-item menu-input" placeholder="Search Funnies" type="text" style="width:20%">
	<a class="menu w3-bar-item w3-button w3-hide-small" id="SearchController" href=#><i class="fa fa-search"></i> </a>
	<a class="menu w3-bar-item w3-button w3-hide-small" id="LogoutController" href=#> <span>Logout</span><i class="fa fa-sign-out" style="margin-left:5px;"></i> </a>
	<a class= "w3-right" href="MainController"> <img src="imgs/SimpleLogo.png" alt= "logo" style="height:34px; margin-right:8px"></a><br>
	<a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="stack()">&#9776;</a>
</div>

<div id="stack" class="w3-bar-block w3-red w3-hide w3-hide-large w3-hide-medium">
	<a class="menu w3-bar-item w3-button" id="GetOwnTimeline" href=#> Home </a>
	<a class="menu w3-bar-item w3-button" id="GetFollowedUsers" href=#> Friends </a>
	<a class="menu w3-bar-item w3-button" id="LogoutController" href=#> Logout </a>
</div>
