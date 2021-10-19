<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
	
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

			<div class="panel-heading">자유게시판</div>
			<!-- /.pannel-heading -->
			<div class="panel-body" style="margin-top: 30px; width: 1030px;">

				<div class="form-group">
					<label>글번호</label> <input class="form-control" name="FreeBno"
						value="<c:out value="${info.freeBno }"/>" readonly="readonly">
					
					<label>조회수</label> <input class="form-control" name="readCount"
						value="<c:out value="${info.readcount }"/>" readonly="readonly">	
				</div>
				
				<div class="form-group">
					<label>작성자</label> <input class="form-control" name="freeId"
						value="<c:out value="${info.id }"/>" readonly="readonly">
				</div>

				<div class="form-group">
					<label>제목</label><input class="form-control" name="freeTitle"
						value="<c:out value="${info.freeTitle }"/>" readonly="readonly">
				</div>

				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="3" name="freeContent"
						readonly="readonly">
					<c:out value="${info.freeContent }" />
					</textarea>
				</div>
				
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
					onclick="location.href='./getModifyFree?freeBno=<c:out value="${info.freeBno }"/>'">수정</button>
				<button data-oper="list" class="btn btn-info" onclick="location.href='./free'">목록</button>
				<button data-oper="delete" class="btn btn-default" onclick="location.href='./deleteFree?freeBno=<c:out value="${info.freeBno }"/>'">삭제</button>	

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
	<script type="text/javascript" src="/resources/el/js/Board/freeReply.js"></script>
	<script>
	$(document).ready(function(){
		
		(function(){
			
			
			var bno = '<c:out value="${info.freeBno }"/>';

			replyService.add(
				{reply_f: "JS Test", replyer_f : "tester", bno : bno},
				function(result){
					alert("RESULT: " + result)
				}
			);
			
			$.getJSON("/getAttachList_f",{bno : bno}, function(arr){
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
		
		
	}); // end document ready
	
	
	
	</script>
</body>
</html>