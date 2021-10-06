<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 게시판</title>

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

</body>
</html>