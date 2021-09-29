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
	<div class="wrap">
		<div class="content_wrap">
			<div class="contents">
				<div class="table_detail_wrap"
					style="width: 1000px; margin-left: 200px;">
					<h1 class="title" style="margin-left: 250px; margin-top: 100px;">공지사항</h1>
					<table>
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 15%;" />
							<col style="width: 10%;" />
							<col style="width: 35%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
						</colgroup>

						<tbody>
							<tr>
								<th>작성자</th>
								<td colspan="3">${info.id }</td>
							</tr>
							<tr>
								<th>작성일</th>
								<td colspan="3">${info.noticeDate }</td>
							</tr>
							<tr>
								<th>제목</th>
								<td colspan="3">${info.noticeTitle }</td>
							</tr>
							<tr>
								<th>내용</th>
								<td colspan="3"><textarea value="" disabled>${info.noticeContent }</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="btn_wrap right" style="margin-right: 100px;">
					<!-- <a href="#none" class="btn del">삭제</a>  -->
					<a href="./getModifyNotice?noticeBno=${info.noticeBno }" class="btn write" style="margin-right: 5px;">수정</a>
					<a href="./notice" class="btn write" style="margin-right: 5px;">목록</a>
					
				</div>
			</div>
		</div>
	</div>

	<!-- Header Section Begin -->
	<jsp:include page="${request.contextPath}/NewFooter_JS"></jsp:include>
	<!-- Header End -->

</body>
</html>