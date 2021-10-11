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
					
					<div class="uploadResult">
						<ul>
						</ul>
					</div>
					<button id="uploadBtn">Upload</button>
				</div>
			</div>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="crossorigin="anonymous"></script>
	<script>
		
		$(document).ready(function(e){
			var formObj = $("form[role='form']");
			
			$("button[type='submit']").on("click",function(e){
				e.preventDefault();
				console.log("submit clicked");
				
				var str ="";
				
				$(".uploadResult ul li").each(function(i, obj){
					str += "<input type='hidden' name="attachList["+i+"].fileName'value='"+jobj.data("filename")+"'>";
					str += "<input type='hidden' name="attachList["+i+"].uuid'value='"+jobj.data("uuid")+"'>";
					str += "<input type='hidden' name="attachList["+i+"].uploadPath'value='"+jobj.data("path")+"'>";
					str += "<input type='hidden' name="attachList["+i+"].fileType'value='"+jobj.data("type")+"'>";
				});
				formObj.append(str).submit();
			});
		});
		
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880;
		
		function checkExtension(fileName, fileSize){
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
			if(regex.test(fileName)){
				alert("해당 종류의 파일은 업로드할 수 없습니다.");
				return false;
			}
			return true;
		});
		var cloneObj = $(".uploadDiv").clone();
		//$("input[type='file']").change(function(e){
		//$(document).ready(function(){
		$('#uploadBtn').on("click", function(e){
			var formData = new FormData();
			
			var inputFile = $("input[name='uploadFile']");
			
			var files = inputFile[0].files;
			
			console.log("files " +files);
			 for(var i = 0; i < files.length; i++){
				if(!checkExtension(files[i].name, files[i].size)){
				return false;
				}

				formData.append("uploadFile", files[i]);
			 }
				$.ajax({
					url: '/uploadAjaxAction',
					processData: false,
					contentType: false, 
					data: formData, 
					type: 'POST',
					//dataType: 'json',
					success: function(result){
						console.log(result)
						
						$(".uploadDiv").html(cloneObj.html());
						showUploadResult(result);
					}
				});  // ajax end
		
		});
		
		var uploadResult = $(".uploadResult ul");
		
		function showUploadResult(uploadResultArr){
			if(!uploadResultArr || uploadResultArr.length == 0){
				return;
			}
			
			var uploadUL = $(".uploadResult ul");
			
			var str = "";
			
			$(uploadResultArr).each(function(i, obj){
				/*
			
				if(obj.image){
					var fileCallPath = encodeURIComponent(obj.uploadPath+ "/s_"+obj.uuid+"_"+obj.fileName);
					str += "<li><div>";
					str += "<span> " + obj.fileName +"</span>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\'data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str +"</li>";
				} else {
					var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid + "_" + obj.fileName);
					var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
					str += "<li><div>";
					str += "<span> " + obj.fileName+"</span>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\'data-type='file' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/resources/img/attach.png'></a>";
					str += "</div>";
					str +"</li>";
				}
				*/
				if(obj.image){
					var fileCallPath = encodeURIComponent(obj.uploadPath+ "/s_"+obj.uuid+"_"+obj.fileName);
					str += "<li data-path='"+obj.uuid+"'";
					str += " data-uuid='" +obj.uuid+"'data-filename='"+obj.fileName+"'data-type='"+obj.image+"'"
					str += "><div>";
					str += "<span> " + obj.fileName +"</span>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\'"
					str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str +"</li>";
				} else {
					var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid + "_" + obj.fileName);
					var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
					str += "<li "
					str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
					str += "<span> " + obj.fileName+"</span>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\'data-type='file'"
					str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/resources/img/attach.png'></a>";
					str += "</div>";
					str +"</li>";
				}
			});
			
			uploadUL.append(str);
		}
		
		
		
		$(".uploadResult").on("click", "button", function(e){
			console.log("delete file");	
			
			var targetFile = $(this).data("file");
			var type = $(this).data("type");
			
			var targetLi = $(this).closest("li");
			
			$.ajax({
				url: '/deleteFile',
				data: {fileName: targetFile, type: type}, 
				dataType: 'text',
				type: 'POST',
				success: function(result){
					alert(result);
					targetLi.remove();
				}
			});  // ajax end
		});
		
	</script>
	
	
</body>
</html>