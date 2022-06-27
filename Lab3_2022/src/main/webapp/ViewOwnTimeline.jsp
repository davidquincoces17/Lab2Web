<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	$('#lrow1').load('GetUserInfo');
	$('#lrow2').load('GetNotFollowedUsers');
	$('#iterator').load('GetFollowedFunnies');
 });
 
	/* Update remaining characters */
	$(document).ready(function() {
 	var text_max = 200;

 	var countText = function(e) {
     	var txt = $('#funnyContent').text();
     	var text_length = txt.length;
   
     	if (text_length >= text_max){
         $('#funnyContent').text(txt.substr(0, text_max));
         text_length = text_max;
         $('#remaining_chars').css('font-weight', 'bold');
         $('#remaining_chars').css('color', 'red');
     	}else{
     		$('#remaining_chars').css('font-weight', 'normal');
            $('#remaining_chars').css('color', 'black');
     	}
        
     	//var text_remaining = text_max - text_length;
   
     $('#remaining_chars').html(text_length + '/' + text_max);
 	};

 	$('#funnyContent').keyup(countText);

 	countText();
	});
 
</script>

<div class="w3-container w3-card w3-round w3-white w3-section">
	<h3 class="w3-opacity" style="font-size:20px"> Hello ${user.username}, would you like to share some Funny? </h3>
	<p id="funnyContent" contenteditable="true" class="w3-border w3-padding"></p>
	<button id="addFunny" type="button" class="w3-button w3-theme w3-green w3-section" style="border-radius:8px"><i class="fa fa-pencil"></i> &nbsp;Post</button>
	<a id="remaining_chars" style="vertical-align:middle;margin-left:10px"></a>
</div>
 
<div id="iterator">
</div>
 


