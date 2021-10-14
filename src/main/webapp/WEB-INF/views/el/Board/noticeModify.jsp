<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
	
	<style type="text/css">
		.uploadResult {
			width: 100%;
			background-color: white;
		}
		
		.uploadResult ul {
			display: flex;
			flex-flow: row;
			justify-content: center;
			align-items: center;
		}
		
		.uploadResult ul li {
			list-style none;
			padding: 10px;
			align-content: center;
			text-align: center;
		}
		
		.uploadResult ul li img {
			width: 100px;
		}
		
		.uploadResult ul li span {
			color: white;
		}
		
		.bigPictureWrapper {
			position: absolute;
			display: none;
			justify-content: center;
			align-items: center;
			top: 0%;
			width: 100%;
			height: 100%;
			background-color: gray;
			z-index: 100;
			background: rgba(255,255,255,0.5);
		}
		
		.bigPicture {
			position: relative;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		
		.bigPicture img {
			width: 600px;
		}
		</style>
<jsp:include page="${request.contextPath}/NewHeader_CSS"></jsp:include>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	 <jsp:include page="${request.contextPath}/el/afterLoginHeader"></jsp:include> 



	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default" style="margin-top: 100px; width: 1030px;margin-left: 250px;">

				<div class="panel-heading">공지사항 수정</div>
				<!-- /.pannel-heading -->
				<div class="panel-body">
					<form role="form" action="./modifyNotice" method="post">
						<div class="form-group">
							<label>글번호</label> <input class="form-control" name="noticeBno"
								value="<c:out value="${info.noticeBno }"/>" readonly="readonly">
						</div>

						<div class="form-group">
							<label>작성자</label> <input class="form-control" name="noticeId"
								value="<c:out value="${info.id }"/>" readonly="readonly">
						</div>

						<div class="form-group">
							<label>제목</label><input class="form-control" name="noticeTitle"
								value="<c:out value="${info.noticeTitle }"/>">
						</div>

						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name="noticeContent">
					<c:out value="${info.noticeContent }" />
					</textarea>
						</div>
	</form>
					
					<!-- 파일첨부 -->
					<div class="bigPictureWrapper">
						<div class="bigPicture"></div>
					</div>
					
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default"
								style="width: 1000px;">
								<div class="panel-heading">첨부파일</div>
								<div class="panel-body">
									<div class="form-group uploadDiv">
										<input type="file" name="uploadFile" multiple="multiple">										
									</div>
								
									<div class="uploadResult">
										<ul>
										</ul>
									</div>
									<!-- <button id="uploadBtn">Upload</button> -->
								</div>
							</div>
						</div>
					</div>
					
					<!-- 	<button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
						<button type="submit" data-oper="list" class="btn btn-info">List</button> -->
					
						<button type="submit" data-oper="modify" class="btn btn-default">수정완료</button>
					
					
				</div>
				<!-- 	end panel-body -->
			</div>
			<!-- 	end pane-body -->
		</div>
		<!-- 	end panel -->
	</div>
	<!-- /.row  -->
	
	<!-- Header Section Begin -->
	<jsp:include page="${request.contextPath}/NewFooter_JS"></jsp:include>
	<!-- Header End -->
	
	<script>
	 //$(document).ready(function() {
     $(document).ready(function(){
		 (function(){
			
			var bno = '<c:out value="${info.noticeBno }"/>';
			
			$.getJSON("/getAttachList",{bno : bno}, function(arr){
				console.log(arr);
				
				var str = "";
				
				$(arr).each(function(i, attach){
					
				//image type
				if(attach.fileType){
					var fileCallPath = encodeURIComponent(attach.uploadPath+"/s_"+attach.uuid+"_"+attach.fileName);
					
					str += "<li data-path='"+attach.uploadPath+"' data-uuid'"+attach.uuid+"'data-filename'"+attach.fileName+"'data-type='"+attach.fileType+"'><div>";
					str += "<span> " + attach.fileName+"</span>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\'data-type='image'";
					str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str += "</li>";
				} else {
					str += "<li data-path='"+attach.uploadPath+"' data-uuid'"+attach.uuid+"'data-filename'"+attach.fileName+"'data-type='"+attach.fileType+"'><div>";
					str += "<span>" + attach.fileName+"</span><br/>"
					str += "<button type='button' data-file=\'"+fileCallPath+"\'data-type='file'";
					str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/resources/img/attach.png'>";
					str += "</div>";
					str += "</li>";
				}
				});
				
				$(".uploadResult ul").html(str);
			}); // end getJSON
		})(); // end function 
	 }); 
	 
     $(".uploadResult").on("click", "button", function(e){
    	 console.log("delete file");
    	
    	 if(confirm("파일을 삭제하시겠습니까?")){
    		 var targetLi = $(this).closest("li");
    		 targetLi.remove();
    	 }
    	 
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
	} // end checkExtension
		
	$("input[type='file']").change(function(e){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		for(var i = 0; i < files.length; i++){
			if(!checkExtension(files[i].name, files[i].size)){
				return false
			}
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: '/uploadAjaxAction',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			dataType: 'json',
			success: function(result){
				console.log(result);
				showUploadResult(result);
			}
			
		}); //end ajax
	}); // end file.change 
	
	function showUploadResult(uploadResultArr){

		if(!uploadResultArr || uploadResultArr.length == 0) {
			return;
		}
		
		var uploadUL = $(".uploadResult ul");
		
		var str = "";
		
		$(uploadResultArr).each(function(i, obj){
			
			if (obj.image) {
				var fileCallPath = encodeURIComponent(obj.uploadPath+ "/s_"+obj.uuid+"_"+obj.fileName);
				
				str += "<li data-path='"+obj.uploadPath+"'";
				str += " data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'";
				str += "><div>";
				str += "<span>"+obj.fileName+"</span>";
				str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/display?fileName="+fileCallPath+"'>";
				str += "</div>";
				str += "</li>";
					   
			} else {
				var fileCallPath = encodeURIComponent(obj.uploadPath+ "/"+obj.uuid+"_"+obj.fileName);
				
				str += "<li "
				str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
				str += "<span>"+obj.fileName+"</span>";
				str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/resources/el/img/attach.png'>";
				str += "</div>";
				str += "</li>";
				
			}
		});
		
		uploadUL.append(str);
	} // end showUploadResult
	
	var formObj = $("form");
	$("button").on("click", function(e){
		
		e.preventDefault();
		
		var operation = $(this).data("oper");
		console.log("operation :: " + operation);
		
		if(operation === 'modify'){
			console.log('submit click');
			var str = "";
			
			$(".uploadResult ul li").each(function(i, obj){
			
			var jobj = $(obj);
			
			console.log(jobj);
			
			str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
			
			});
			formObj.append(str).submit();
		}
		formObj.submit();	
	
	});		
	
	</script>

</body>
</html>