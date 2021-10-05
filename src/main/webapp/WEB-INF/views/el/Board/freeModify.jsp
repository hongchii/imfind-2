<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>

<jsp:include page="${request.contextPath}/NewHeader_CSS"></jsp:include>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="${request.contextPath}/el/afterLoginHeader"></jsp:include>



	<!-- 게시판 수정 -->

	<div class="table_list_wrap"
		style="margin-left: 350px; margin-top: 80px;">
		<form action="./modifyFree" method="post" name="boardform"
			enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0">
				<tr align="center" valign="middle">
					<td colspan="5">자유게시판</td>
				</tr>
				<tr>
					<td><input type="hidden" name="noticeBno" value="${info.freeBno }"> </td>
				</tr>
				<tr>
					<td style="font-family: 돋음; font-size: 12" height="16">
						<div align="center">작성자</div>
					</td>
					<td><input name="id" type="text" size="50" maxlength="100" value="${info.id }"></td>
				</tr>
				<tr>
					<td style="font-family: 돋음; font-size: 12" height="16">
						<div align="center">제 목</div>
					</td>
					<td><input name="noticeTitle" type="text" size="50"
						maxlength="100" value="${info.freeTitle }"/></td>
				</tr>
				<tr>
					<td style="font-family: 돋음; font-size: 12">
						<div align="center">내 용</div>
					</td>
					<td><textarea name="noticeContent" cols="67" rows="15">${info.freeContent }</textarea></td>
				</tr>
				<tr bgcolor="cccccc">
					<td colspan="2" style="height: 1px;"></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr align="center" valign="middle">
					<button type="submit" style="width: 82px;" class="btn write">수정완료</button>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>