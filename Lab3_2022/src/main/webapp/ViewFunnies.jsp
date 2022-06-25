<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="t" items="${funnies}" varStatus="loop">
 <div id="${t.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="${t.image}" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
      <span class="w3-right w3-opacity"> ${t.timestamp} </span>
   <div style=display:flex>
   <h4> ${t.authorNickname} </h4>
   <h6><span class="w3-opacity" style="font-size: 20px; opacity: 0.4"> &nbsp @${t.authorUsername} </span></h6>
   </div>
   <hr class="w3-clear">
   <p> ${t.content} </p>
<%--    ${funs[loop.index]} --%>
   <button  type="button" class="likeFunny w3-button w3-theme w3-margin-bottom w3-circle"><img src="imgs/fun0.png" alt= "fun" style="height:24px; width:24px"></button>
<%--    ${unfuns[loop.index]} --%>
   <button type="button" class="dislikeFunny w3-button w3-theme w3-margin-bottom w3-circle"><img src="imgs/unfun0.png" alt= "unfun" style="height:24px; width:24px"></button>
   <button type="button" class="replyFunny w3-button w3-theme w3-margin-bottom"><i class="fa fa-reply"></i> &nbsp;Reply</button>
   <c:if test="${user.username.equals(t.authorUsername)}">
   		<button type="button" class="delTweet w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button>
	</c:if>
	
   
 </div>
</c:forEach>
