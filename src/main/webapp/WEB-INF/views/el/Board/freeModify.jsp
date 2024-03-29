<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 수정</title>
	
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

				<div class="panel-heading">자유게시판 수정</div>
				<!-- /.pannel-heading -->
				<div class="panel-body">
					<form role="form" action="./modifyFree" method="post">
						<div class="form-group">
							<label>글번호</label> <input class="form-control" name="freeBno"
								value="<c:out value="${info.freeBno }"/>" readonly="readonly">
						</div>

						<div class="form-group">
							<label>작성자</label> <input class="form-control" name="freeId"
								value="<c:out value="${info.id }"/>" readonly="readonly">
						</div>

						<div class="form-group">
							<label>제목</label><input class="form-control" name="freeTitle"
								value="<c:out value="${info.freeTitle }"/>">
						</div>

						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name="freeContent">
					<c:out value="${info.freeContent }" />
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
	


</body>
</html>