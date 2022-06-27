<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	//$('#lrow1').load('GetUserInfo');
	//$('#lrow2').load('GetNotFollowedUsers');
	$('#iterator').load('GetAllFunnies');
 });
 
</script>
 
<div id="iterator">
</div>
 


