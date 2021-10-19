<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center text-success">
	<div class="sign-up-box">
		<h1 class="mb-4">회원가입</h1>
		<form id="signUpForm" method="post" action="/user/signup">
			<table class="sign-up-table table table-bordered">
				<tr>
					<th>* 아이디(4자 이상)<br></th>
					<td>
						<%-- 인풋박스 옆에 중복확인을 붙이기 위해 div를 하나 더 만들고 d-flex --%>
						<div class="d-flex">
							<input type="text" id="loginId" name="loginId" class="form-control">
							<button type="button" id="loginIdCheckBtn" class="btn btn-success">중복확인</button><br>
						</div>
						
						<%-- 아이디 체크 결과 --%>
						<%-- d-none 클래스: display none (보이지 않게) --%>
						<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
						<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
						<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
					</td>
				</tr>
				<tr>
					<th>* 비밀번호</th>
					<td><input type="password" id="password" name="password" class="form-control"></td>
				</tr>
				<tr>
					<th>* 비밀번호 확인</th>
					<td><input type="password" id="confirmPassword" class="form-control"></td>
				</tr>
				<tr>
					<th>* 닉네임</th>
					<td><input type="text" id="nickname" name="nickname" class="form-control"></td>
				</tr>
				<tr>
					<th>* 주소</th>
					<td><select id="address" name="address" class="form-control">
							<option value="default">주소를 선택해주세요.</option>
							<option value="horizon">------------------------</option>
                            <option>역삼동</option>
                            <option>논현동</option>
                            <option>신사동</option>
                            <option>압구정동</option>
                        </select></td>
				</tr>
				<tr>
					<th>* 이메일</th>
					<td><input type="text" id="email" name="email" class="form-control"></td>
				</tr>
				<tr>
					<th>* 프로필사진</th>
					<td><div class="file-upload d-flex">
						<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
						<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
						<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
						<a href="#" id="fileUploadBtn"><img width="35"
						class="ml-1" src="data:image/png;base64,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"></a>

						
					</div></td>
				</tr>
			</table>
			<br>
		
			<button type="submit" id="signUpBtn" class="btn btn-success float-right">회원가입</button>
		</form>
	</div>
</div>
<script>
$(document).ready(function() {
	
	// 파일 업로드 이미지 버튼 클릭 - 사용자가 업로드를 할 수 있게 해줌
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); // 기본 작동 중지
		$('#file').click(); // input file을 클릭한 것과 같은 동작
	});
	
	// 사용자가 파일 업로드를 했을 때 유효성 확인 및 업로드 된 파일 이름을 노출
	$('#file').on('change', function(e) {
		var name = e.target.files[0].name;
		
		// 확장자 유효성 확인
		var extension = name.split('.');
		if (extension.length < 2 || 
		 	(extension[extension.length - 1] != 'gif' 
		 	&& extension[extension.length - 1] != 'png' 
		 	&& extension[extension.length - 1] != 'jpg'
		 	&& extension[extension.length - 1] != 'jpeg')) {
		 	
		 	alert("이미지 파일만 업로드 할 수 있습니다.");
		 	$(this).val(""); // 이미 올라간 것을 확인한 것이기 때문에 비워주어야 한다.
		 	return;
		 }
		 
	});
	$('#loginIdCheckBtn').on('click', function(e) {
		// validation
		var loginId = $('#loginId').val().trim();
		// id가 4자 이상이 아니면 경고문구 노출하고 끝낸다.
		if (loginId.length < 4) {
			$('#idCheckLength').removeClass('d-none'); // 경고문구 노출
			$('#idCheckDuplicated').addClass('d-none'); // 숨김
			$('#idCheckOk').addClass('d-none'); // 숨김
			return;
		}
		$.ajax({
			url: "/user/idcheck",
			data: {"loginId": loginId},
			success: function(data) {
				if (data.result == true) { // 중복인 경우
					$('#idCheckDuplicated').removeClass('d-none'); // 중복 경고문구 노출
					$('#idCheckLength').addClass('d-none'); // 숨김
					$('#idCheckOk').addClass('d-none'); // 숨김
				} else {
					$('#idCheckOk').removeClass('d-none'); // 사용가능 문구 노출
					$('#idCheckLength').addClass('d-none'); // 숨김
					$('#idCheckDuplicated').addClass('d-none'); // 숨김
				}
			},
			error: function(error) {
				alert("아이디 중복확인에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	$('#signUpBtn').on('click', function(e) {
		e.preventDefault(); 
		var loginId = $('#loginId').val().trim();
		if (loginId == '') {
			alert("아이디를 입력하세요.");
			return;
		}
		
		var password = $('#password').val().trim();
		var confirmPassword = $('#confirmPassword').val().trim();
		if (password == '' || confirmPassword == '') {
			alert("비밀번호를 입력하세요.");
			return;
		}
		
		// 비밀번호 확인 일치 여부
		if (password != confirmPassword) {
			alert("비밀번호가 일치하지 않습니다. 다시 입력하세요.");
			// 텍스트박스의 값을 초기화 한다.
			$('#password').val('');
			$('#confirmPassword').val('');
			return;
		}
		
		var nickname = $('#nickname').val().trim();
		if (nickname == '') {
			alert("닉네임을 입력하세요.");
			return;
		}
		
		var address = $('#address').val();
		if (address == 'default' || address == 'horizon') {
			alert("주소를 선택해주세요.");
			return;
		}
		
		
		var email = $('#email').val().trim();
		if (email == '') {
			alert("이메일 주소를 입력하세요.");
			return;
		}
		
		// 아이디 중복확인이 완료되었는지 확인
		//-- idCheckOk <div>의 클래스에 d-none이 없으면 사용가능으로 본다.
		if ($('#idCheckOk').hasClass('d-none') == true) {
			alert("아이디 확인을 다시 해주세요.");
			return;
		}
		
		
		
		var formData = new FormData(); // form 객체를 만든다. form 자체와는 달라서 seralize 안해도 된다.
		formData.append("password", password);
		formData.append("nickname", nickname);
		formData.append("address", address);
		formData.append("email", email);
		formData.append("file", $("#file")[0].files[0]); // 파일을 가져온다. 없으면 null
		
		$.ajax({
			type:'POST',
			url:'/user/signup',
			data: formData,
			enctype: 'multipart/form-data', // 파일 업로드를 위한 필수 설정
			processData: false, 			// 파일 업로드를 위한 필수 설정
            contentType: false,				// 파일 업로드를 위한 필수 설정
			success: function(data) {
				if (data.result == 'success') {
					location.reload(); // 새로고침
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				var errorMsg = jqXHR.responseJSON.status;
				alert(errorMsg + ":" + textStatus);
			}
		});
	});
	
});
</script>