<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div>
		<section id="article-images" class="mt-3">
			<img src="${article.post.imagePath}" width="300"/>
		</section>

		<section class="mt-3">
			<div class="d-flex">
				<img src="${article.user.imagePath}" width="50"/>
				<div class="p-3">
					<div>${article.user.nickName}</div>
					<div id="region-name">${article.user.address}</div>
				</div>
			</div>

		</section>
		<hr>

		<section class="mt-3">
			<h1>${article.post.title }</h1>
			<p>${article.post.content }</p>
			<p>조회수 ${article.post.viewCount }</p>
		</section>
		</article>
		<div>
			<div class="ml-3 mb-1 font-weight-bold">댓글</div>
		</div>
		<hr>
		<div class="m-2">
			<c:forEach var="comment" items="${content.commentList}">
				<div class="m-1">
					<span class="font-weight-bold">${comment.userName} : </span> <span>${comment.content}</span>

					<c:if test="${userName eq comment.userName}">
						<a href="#" class="commentDelBtn" data-comment-id="${comment.id}">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png"
							width="10px" height="10px">
						</a>
					</c:if>
				</div>
			</c:forEach>
		</div>
	</div>
</div>