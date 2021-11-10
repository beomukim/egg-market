<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div>
		<section id="article-images" class="mt-3">
			<img src="${article.post.imagePath}" width="400" />
		</section>

		<section class="mt-3">
			<div class="d-flex">
				<img src="${article.user.imagePath}" width="50" />
				<div class="p-3">
					<div class="font-weight-bold">${article.user.nickName}</div>
					<div id="region-name" class="font-weight-bold">${article.user.address}</div>
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
			<c:forEach var="comment" items="${article.commentList}">
				<div class="m-1">
					<div class="d-flex commentBox"
						data-nickname="${comment.userNickName}">
						<img src="${article.user.imagePath}" width="35" />
						<div class="font-weight-bold p-1">${comment.userNickName}</div>
						<div class="d-flex align-items-center">
							<div class="ml-5">${comment.content}</div>
						</div>
					</div>
					<c:if test="${userId eq comment.userId}">
						<a href="#" class="commentDelBtn" data-comment-id="${comment.id}">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png"
							width="10px" height="10px">
						</a>
					</c:if>
				</div>
				<hr>
			</c:forEach>
		</div>
		<textarea id="comment" class="form-control mb-2"
			placeholder="댓글을 입력해주세요."></textarea>
		<div class="d-flex justify-content-end">
			<div class="mt-2">
				<button id="postListBtn" class="btn btn-success float-left">목록</button>
				<button id="saveBtn" class="btn btn-success ml-2"
					data-post-id="${article.post.id}">입력</button>
				<c:if test="${userId eq article.user.id}">
					<button id="deleteBtn" class="btn btn-success ml-2"
						data-post-id="${article.post.id}" data-post-id="${article.post.id}">삭제</button>
				</c:if>

			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#postListBtn').on('click', function() {
			location.href = "/post";
		});

		$('#saveBtn').on('click', function(e) {
			e.preventDefault();
			var postId = $(this).data('post-id');

			var commentText = $('#comment').val().trim();
			console.log(commentText);
			if (commentText.length < 1) {
				alert("댓글 내용을 입력해주세요.");
				return;
			}

			$.ajax({
				type : 'POST',
				url : '/comment/write',
				data : {
					"postId" : postId,
					"content" : commentText
				},
				success : function(data) {
					if (data.result == 'success') {
						location.reload(); // 새로고침
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});

		});

		$('.commentBox').on('click', function(e) {
			e.preventDefault();

			var nickname = $(this).data("nickname");
			$('#comment').val('@' + nickname + ' ');
		})

		$('.commentDelBtn').on('click', function(e) {
			e.preventDefault();

			var commentId = $(this).data("comment-id");

			$.ajax({
				type : 'POST',
				url : '/comment/delete',
				data : {
					"commentId" : commentId
				},
				success : function(data) {
					if (data.result == 'success') {
						location.reload(); // 새로고침
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
		});
		$('#deleteBtn').on('click', function() {
			var postId = $(this).data('post-id');
			
			// AJAX 통신으로 삭제 요청
			$.ajax({
				url: '/post/delete',
				method: 'POST',
				data: {'postId':postId},
				success: function(data) {
					if (data.result == 'success') {
						alert("삭제되었습니다.");
						location.href = "/post";
					}
				},
				error: function(e) {
					alert("메모를 삭제하는데 실패했습니다. 관리자에게 문의해주세요.");
				}
			});
		});
	});
</script>