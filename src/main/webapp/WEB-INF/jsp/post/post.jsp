<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex justify-content-center">
	<div class="col-6">
		<div class="d-flex">
			<input type="text" class="form-control" id="search">
			<button type="button" id="search-btn" class="btn btn-success">검색</button>
		</div>
		<br>
		<div class="d-flex justify-content-end">
			<div class="col-4 pr-0">
				<select id="sort" class="form-control">
					<option>최신순</option>
					<option>인기순</option>
				</select>
			</div>
		</div>
		<br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>작성자</th>
					<th>물건</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${articleList}" var="article">
					<tr>
						<td>
							<div class="d-flex">
								<div>
									<img src="${article.user.imagePath}" width=50>
								</div>
								<div class="ml-2">
									${article.user.nickName}<br>${article.user.address}
								</div>
							</div>
						</td>
						<td>${article.post.title}</td>
						<td>${article.post.price}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr>
		<div class="d-flex justify-content-end">
			<div>
				<a href="/post/write" class="btn btn-success" id="write-btn">글쓰기</a>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#search-btn").on('click', function() {
			let search = $('#search').val().trim();
			location.href = '/post?search=' + search;
			
		});
		$('#sort').on('change', function() {
			let sort = $('#sort option:selected').val();
			location.href = '/post?sort=' + sort;
			// $('#sort').val(sort).prop('selected', true); -> 작동 안함
		})
		$('#sort').val('${sort}').prop('selected', true); // GET방식으로 queryParameter를 받아서 구현
		if ('${sort}' == '') {
			$('#sort').val('인기순');
		}
		$("#write-btn").on('click', function() {
			location.href = '/post/write';
			
		});
		
	});
</script>
