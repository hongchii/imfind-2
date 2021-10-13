<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 게시판</title>
	<div class="bigPictureWrapper">
		<div class="bigPicture"></div>
	</div>
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

			<div class="panel-heading">공지사항</div>
			<!-- /.pannel-heading -->
			<div class="panel-body" style="margin-top: 30px; width: 1030px;">

				<div class="form-group">
					<label>글번호</label> <input class="form-control" name="noticeBno"
						value="<c:out value="${info.noticeBno }"/>" readonly="readonly">
					
					<label>조회수</label> <input class="form-control" name="readCount"
						value="<c:out value="${info.readcount }"/>" readonly="readonly">	
				</div>
				
				<div class="form-group">
					<label>작성자</label> <input class="form-control" name="noticeId"
						value="<c:out value="${info.id }"/>" readonly="readonly">
				</div>

				<div class="form-group">
					<label>제목</label><input class="form-control" name="noticeTitle"
						value="<c:out value="${info.noticeTitle }"/>" readonly="readonly">
				</div>

				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="3" name="noticeContent"
						readonly="readonly">
					<c:out value="${info.noticeContent }" />
					</textarea>
				</div>
				
				<!-- 파일첨부 -->
			
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default"
							style="width: 1000px;">
							<div class="panel-heading">첨부파일</div>
							<div class="panel-body">
								<div class="uploadResult">
									<ul>
									</ul>
								</div>
								<!-- <button id="uploadBtn">Upload</button> -->
							</div>
						</div>
					</div>
				</div>
	

				<button data-oper="modify" class="btn btn-default"
					onclick="location.href='./getModifyNotice?noticeBno=<c:out value="${info.noticeBno }"/>'">수정</button>
				<button data-oper="list" class="btn btn-info"
					onclick="location.herf='./notice'">목록</button>
				<button data-oper="delete" class="btn btn-default"
					onclick="location.href='./deleteNotice?noticeBno=<c:out value="${info.noticeBno }"/>'">삭제</button>	

			</div>
			<!-- end panel-body -->
		</div>
		<!-- end pane-body -->
	</div>
	<!-- end panel -->
</div>

	<!-- Header Section Begin -->
	<jsp:include page="${request.contextPath}/NewFooter_JS"></jsp:include>
	<!-- Header End -->
	
	<script>
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
					str += "<img src='/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str += "</li>";
				} else {
					str += "<li data-path='"+attach.uploadPath+"' data-uuid'"+attach.uuid+"'data-filename'"+attach.fileName+"'data-type='"+attach.fileType+"'><div>";
					str += "<span>" + attach.fileName+"</span><br/>"
					str += "<img src='/resources/img/attach.png'>";
					str += "</div>";
					str += "</li>";
				}
				});
				
				$(".uploadResult ul").html(str);
			}); // end getJSON
		})(); // end function 
		
		/*
		$(".uploadResult").on("click", "li", function(e){
			console.log("view image");
	
			var liObj = $(this);
			console.log("this" + $(this));
			
			var path = encodeURIComponent(liObj.data("path")+"/"+liObj.data("uuid")+"_"+liObj.data("fileName"));
			console.log("path 확인 : " + path );
			console.log("1path:: "+liObj.data("path"));
			console.log("2uuid:: "+liObj.data("uuid"));
			console.log("3filename:: "+liObj.data("filename"));
			
			console.log(liObj.data("type"));
			if(liObj.data("type")){
				//showImage(path.replace(new RegExp(/\\/g),"/"));
				showImage(path);
				console.log("if");
			} else {
				showImage(path);
				//self.location = "/download?fileName="+path;
				console.log("else");
			}
			
		}); // end uploadResult click
	
		
		
		function showImage(fileCallPath){
			alert(fileCallPath);
			
			$(".bigPictureWrapper").css("display", "flex").show();
			
			$(".bigPicture")
			.html("<img src='/display?fileName="+fileCallPath+"'>")
			.animate({width: '100%', height: '100%'}, 1000);
			
		}
		
		$(".bigPictureWrapper").on("click", function(e){
			$(".bigPicture").animate({width: '100%', height: '100%'}, 1000);
			setTimeout(function(){
				$('.bigPictureWrapper').hide();
			}, 1000);
		});
		
			*/
			
	}); // end document ready
	
	</script>
</body>
</html>