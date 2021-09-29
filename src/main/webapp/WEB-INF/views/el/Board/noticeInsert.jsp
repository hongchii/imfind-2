<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
</head>
<body>
	<!-- 게시판 등록 -->
	<form action="./addNotice" method="post" name="boardform"
		enctype="multipart/form-data">
		<table cellpadding="0" cellspacing="0">
			<tr align="center" valign="middle">
				<td colspan="5">공지사항</td>
			</tr>
			<td><input type="hidden" name="noticeBno" value="14"></td>
			<tr>
				<td style="font-family: 돋음; font-size: 12" height="16">
					<div align="center">글쓴이</div>
				</td>
				<td><input name="id" type="text" size="50" maxlength="100" ></td>
			</tr>
			<tr>
				<td style="font-family: 돋음; font-size: 12" height="16">
					<div align="center">제 목</div>
				</td>
				<td><input name="noticeTitle" type="text" size="50" maxlength="100"
					value="" /></td>
			</tr>
			<tr>
				<td style="font-family: 돋음; font-size: 12">
					<div align="center">내 용</div>
				</td>
				<td><textarea name="noticeContent" cols="67" rows="15"></textarea></td>
			</tr>
			<tr bgcolor="cccccc">
				<td colspan="2" style="height: 1px;"></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr align="center" valign="middle">
				<button type="submit" style="width: 82px;" class="btn write">저장</button>
			</tr>
		</table>
	</form>
</body>
</html>