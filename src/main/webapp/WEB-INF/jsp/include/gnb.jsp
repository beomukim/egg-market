<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- d-flex와 justify-content-between: logo와 로그인 정보를 양쪽 끝으로 떨어뜨린다. --%>

<div class="d-flex p-4 justify-content-between">
	<div id="logo" class="d-flex">
		<img src="https://img.icons8.com/ios-filled/50/26e07f/eggs.png"
			width=50 height=50 />
		<div>계란마켓</div>
	</div>
	<div class="d-flex">
		<div>
			<c:if test="${not empty userNickName}">
				<div class="login-info d-flex align-items-center">
					<div>
						<a href="/user/logout" class="m-2 text-success font-weight-bold">로그아웃</a>
					</div>
				</div>
			</c:if>
		</div>

	</div>
</div>

<script>
	$(document).ready(function() {
		$('#logo').on('click', function() {
			location.href = '/';
		});
	});
</script>
