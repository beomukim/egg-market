<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div>
		<section id="article-images" class="mt-3">
			<img
				src="https://dnvefa72aowie.cloudfront.net/hoian/category/thumbnails/v2/img_thumb_book.png" />
		</section>

		<section class="mt-3">
			<div class="d-flex">
				<img
					src="https://d1unjqcospf8gs.cloudfront.net/assets/users/default_profile_80-7e50c459a71e0e88c474406a45bbbdce8a3bf2ed4f2efcae59a064e39ea9ff30.png" />
				<div class="p-3">
					<div id="nickname">안녕</div>
					<div id="region-name">중구 성내1동</div>
				</div>
			</div>

		</section>
		<hr>

		<section class="mt-3">
			<h1>신세계 상품권</h1>
			<p>쓸때가 없어서 싸게 팔아요 연락주세요</p>
			<p>조회 156</p>
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