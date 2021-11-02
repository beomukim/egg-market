<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="post-create w-50">

		<input type="text" name="title" class="form-control"
			placeholder="제목을 입력해주세요.">
		<hr>
		<textarea name="content" class="form-control" rows="15" cols="100"
			placeholder="내용을 입력해주세요."></textarea>
		<hr>
		<input type="text" name="price" class="form-control col-5"
			placeholder="가격을 입력해주세요.">
		<hr>
		<div class="file-upload d-flex justify-content-end">
			<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
			<input type="file" id="file" class="d-none"
				accept=".gif, .jpg, .png, .jpeg" />
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

<script>
$(document).ready(function() {
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); // 기본 작동 중지
		$('#file').click(); // input file을 클릭한 것과 같은 동작
	});
	// 목록 버튼 클릭
	$('#postListBtn').on('click', function() {
		location.href="/post";
	});
	
	
	// 글 내용 저장
	$('#saveBtn').on('click', function(e) {
		e.preventDefault(); // 기본 이벤트 중단 - 화면이 상단으로 올라가는 현상 제거
		
		var title = $('input[name=title]').val().trim(); // 제목만 필수
		console.log(title);
		if (title == '') {
			alert("제목을 입력해주세요.");
			return;
		}
		
		var content = $('textarea[name=content]').val();
		console.log(content);
		if (content == '') {
			alert("내용을 입력해주세요.");
			return;
		}
		
		var price = $('input[name=price]').val();
		console.log(price);
		if (price == '') {
			alert("가격을 입력해주세요.");
			return;
		}
		
		// 파일이 업로드 된 경우 확장자 체크
		var file = $('#file').val();
		if (file != "") {
			console.log(file.split('.')); // 파일명을 . 기준으로 나눈다.
			var ext = file.split('.').pop().toLowerCase(); // 파일명을 .기준으로 나누고, 확장자가 있는 마지막 문자를 가져온 후 모두 소문자로 변경
			if($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
				 alert('gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.');
				 $('#file').val(''); // 파일을 비운다.
				 return;
			}
		}
		// 폼 태그를 자바스크립트에서 만든다.
		var formData = new FormData();
		formData.append("title", title);
		formData.append("content", content);
		formData.append("price", price);
		//var fileInput = document.getElementById("file");
		//var files = fileInput.files;
        //for (var i = 0; i < files.length; i++) {
            
			//formData.append("fileList[]", files[i]);			

            //alert(fileList[i].name);
        //}
        console.log(formData);
		formData.append("file", $('#file')[0].files[0]);
		// AJAX 통신으로 form에 있는 데이터를 전송한다.
		$.ajax({
			url: '/post/write',
			method: 'POST',
			data: formData,
			enctype: 'multipart/form-data', // 파일 업로드를 위한 필수 설정
			processData: false, 			// 파일 업로드를 위한 필수 설정
            contentType: false, 			// 파일 업로드를 위한 필수 설정
			success: function(data) {
				if (data.result == 'success') {
					alert("글쓰기가 완료되었습니다!");
					location.href = "/post";
				}
			},
			error: function(e) {
				alert("글쓰기가 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
});
</script>