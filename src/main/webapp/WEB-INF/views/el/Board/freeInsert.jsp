<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 등록</title>

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

				<div class="panel-heading">자유게시판 등록</div>
				<!-- /.pannel-heading -->
				<div class="panel-body">
					<form role="form" action="./addFree" method="post" name="boardform"
						enctype="multipart/form-data">
						<div class="form-group">
							<label>글쓴이</label> <input class="form-control" name="id" value="">
						</div>

						<div class="form-group">
							<label>제목</label><input class="form-control" name="freeTitle"
								value="">
						</div>

						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name="freeContent"></textarea>

						</div>
					</form>

				<!-- 파일첨부 -->
			
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default"
							style="width: 1000px;">
							<div class="panel-heading">첨부파일</div>
							<div class="panel-body">
								<div class="form-group uploadDiv">
									<input type="file" name="uploadFile" multiple>
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
				
				<button type="submit" class="btn btn-default">등록</button>
				</div>
				<!-- end panel-body -->
			</div>
			<!-- end pane-body -->
		</div>
		<!-- end panel -->
	</div>
	<!-- /.row -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="crossorigin="anonymous"></script>

	
</body>
</html>