<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>

<jsp:include page="${request.contextPath}/NewHeader_CSS"></jsp:include>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script>
		$(document).ready(function(e){
			var formObj = $("form[role='form']");
			
			$("button[type='submit']").on("click",function(e){
				e.preventDefault();
				console.log("submit clicked");
			});
		});
		
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880;
		
		function checkExtendion(fileName, fileSize){
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
			if(regex.test(fileName)){
				alert("해당 종류의 파일은 업로드할 수 없습니다.");
				return false;
			}
			return ture;
		}
		
		$("input[type='file']").charge(function(e){
			var formData = new FormData();
			
			var inputFile = $("input[name='uploadFile']");
			
			var files = inputFile[0].files;
			
			for(var i = 0; i < files.length; i++){
				if(!checkExtension(files[i].name, files[i].size)){}
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: '/uploadAjaxAction',
			processData: false,
			contentType: false, data:
			formData:'json',
			success: function(result){
				console.log(result);
			}
		}); 
		});
	</script>
</head>
<body>
	<jsp:include page="${request.contextPath}/el/afterLoginHeader"></jsp:include>



	<!-- 게시판 등록 -->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default"
				style="margin-top: 100px; width: 1030px; margin-left: 250px;">

				<div class="panel-heading">공지사항 등록</div>
				<!-- /.pannel-heading -->
				<div class="panel-body">
					<form action="./addNotice" method="post" name="boardform"
						enctype="multipart/form-data">
						<div class="form-group">
							<label>글쓴이</label> <input class="form-control" name="id" value="">
						</div>

						<div class="form-group">
							<label>제목</label><input class="form-control" name="noticeTitle"
								value="">
						</div>

						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name="noticeContent"></textarea>

						</div>
						<button type="submit" class="btn btn-default">등록</button>
					</form>
				</div>
				<!-- end panel-body -->
			</div>
			<!-- end pane-body -->
		</div>
		<!-- end panel -->
	</div>
	<!-- /.row -->

	<!-- 파일첨부 -->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default"
				style="width: 1030px; margin-left: 250px;">
				<div class="panel-heading">첨부파일</div>
				<div class="panel-body">
					<div class="form-group uploadDiv">
						<input type="file" name="uploadFile" multiple>
					</div>


				</div>
			</div>
		</div>
	</div>
</body>
</html>