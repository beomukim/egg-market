<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex justify-content-center">
	<div class="col-6">
		<div class="d-flex">
			<input type="text" class="form-control">
			<button type="button" id="search" class="btn btn-success">검색</button>
		</div>
		<br>
		<div class="d-flex justify-content-end">
			<div class="col-4 pr-0">
				<select class="form-control">
					<option>인기순</option>
					<option>최신순</option>
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
				<c:forEach items="${postList}" var="post">
					<tr>
						<td>
							<div class="d-flex">
								<div>
									<img src="${}" width=50>
								</div>
								<div class="ml-2">
									${}<br>${}
								</div>
							</div>
						</td>
						<td>${}</td>
						<td>2000</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

