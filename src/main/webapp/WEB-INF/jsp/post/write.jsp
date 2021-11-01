<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="post-create w-50">

		<input type="text" name="subject" class="form-control"
			placeholder="제목을 입력해주세요.">
		<hr>
		<textarea name="content" class="form-control" rows="15" cols="100"
			placeholder="내용을 입력해주세요."></textarea>
		<hr>
		<input type="text" name="price" class="form-control"
			placeholder="가격을 입력해주세요.">
		<hr>
		<div class="file-upload d-flex justify-content-end">
			<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
			<input type="file" id="file" class="d-none"
				accept=".gif, .jpg, .png, .jpeg">
			<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
				<a href="#" id="fileUploadBtn"><img width="75" class="ml-1"
					src="https://cdn-icons-png.flaticon.com/512/5982/5982239.png"></a>


		</div>

		<div class="btn-area clearfix mt-5">
			<div class="d-flex justify-content-end">
				<button id="postListBtn" class="btn btn-success float-left">목록</button>

				<button id="saveBtn" class="btn btn-success ml-2">저장</button>
			</div>
		</div>

	</div>
</div>