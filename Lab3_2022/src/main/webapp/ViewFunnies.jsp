<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="t" items="${funnies}">       
 <div id="${t.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="${t.image}" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
      <span class="w3-right w3-opacity"> ${t.timestamp} </span>
   <div style=display:flex>   
   <h4> ${t.authorNickname} </h4>
   <h6><span class="w3-opacity" style="font-size: 20px; opacity: 0.4"> &nbsp @${t.authorUsername} </span></h6>
   </div>
   <hr class="w3-clear">
   <p> ${t.content} </p>
   <button type="button" class="likeTweet w3-button w3-theme w3-margin-bottom"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button>
   <button type="button" class="dislikeTweet w3-button w3-theme w3-margin-bottom"><i class="fa fa-thumbs-down"></i> &nbsp;Dislike</button>
   <button type="button" class="replyTweet w3-button w3-theme w3-margin-bottom"><i class="fa fa-reply"></i> &nbsp;Reply</button>
   <button type="button" class="delTweet w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button>
 </div>
</c:forEach>
