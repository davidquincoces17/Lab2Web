<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>

 <script type="text/javascript">
 $(document).ready(function(){
	$('#navigation').load('MenuController');
	//$('#lrow1').load('GetUserInfo');
	//$('#lrow2').load('GetNotFollowedUsers');
	$('#iterator').load('GetAllFunnies');
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
 
<div id="iterator">
</div>
 


