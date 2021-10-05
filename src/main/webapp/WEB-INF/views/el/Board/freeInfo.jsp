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
	
	<div class="wrap">
		<div class="content_wrap">
			<div class="contents">
				<div class="table_detail_wrap"
					style="width: 1000px; margin-left: 200px;">
					<h1 class="title" style="margin-left: 250px; margin-top: 100px;">자유게시판</h1>
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
				<td style="font-family:돋음; font-size:12" height="16">
					<div align="center">제 목&nbsp;&nbsp;</div>
				</td>
				
				<td style="font-family:돋음; font-size:12">
				${info.freeTitle }
				</td>
			</tr>
			
			<tr bgcolor="cccccc">
				<td colspan="2" style="height:1px;">
				</td>
			</tr>
			
			<tr>
				<td style="font-family:돋음; font-size:12">
					<div align="center">내 용</div>
				</td>
				<td style="font-family:돋음; font-size:12">
					<table border=0 width=490 height=250 style="table-layout:fixed">
						<tr>
							<td valign=top style="font-family:돋음; font-size:12">
							${info.freeContent }
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
						</tbody>
					</table>
				</div>
					<tr align="center" valign="middle">
						<td colspan="5">
							<font size=2>
							<a href="./getModifyFree?freeBno=${info.freeBno }">[수정]</a>&nbsp;&nbsp;
							<a href="./deleteFree?freeBno=${info.freeBno }">[삭제]</a>&nbsp;&nbsp;
							<a href="./free">[목록]</a>&nbsp;&nbsp;
							</font>
						</td>
					</tr>
			</div>
		</div>
	</div>

	<!-- Header Section Begin -->
	<jsp:include page="${request.contextPath}/NewFooter_JS"></jsp:include>
	<!-- Header End -->

</body>
</html>