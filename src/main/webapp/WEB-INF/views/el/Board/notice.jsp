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
				<h1 class="title" style="margin-left: 250px; margin-top: 100px;">공지사항</h1>

				<div class="table_list_wrap"
					style="margin-left: 350px; margin-top: 80px;">
					<table>
						<caption>게시판 목록</caption>
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 15%;" />
							<col style="width: 10%;" />
							<col style="width: 35%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th>글번호</th>
								<th>제목</th>
								<th>작성자</th>
								<!-- <th>내용</th> -->
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody class="text-center">
							<c:forEach items="${notice }" var="notice">
								<tr>
									<td><input type="checkbox" id=${notice.noticeBno }
										name="board_del_yn" value="y"></td>
									<td><c:out value="${notice.noticeBno }" /></td>
									<td class="left"><a
										href="/noticeInfo?noticeBno=${notice.noticeBno }"> <c:out
												value="${notice.noticeTitle }" />
									</a></td>
									<td><c:out value="${notice.id }" /></td>
								<%-- 	<td class="left"><a
										href="/noticeInfo?noticeBno==${notice.noticeBno }"> <c:out
												value="${notice.noticeContent }" />
									</a></td> --%>

									<td><c:out value="${notice.noticeDate }" /></td>
									<td><c:out value="${notice.readcount }" /></td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<tr><td colspan="2">&nbsp;</td></tr>
				<tr align="center" valign="middle">
					<td colspan="5">
						<font size=2>
					<!-- <a href="#none" class="btn del">삭제</a>  -->
					<a href="./insert">[등록]</a>&nbsp;&nbsp;
					<a href="#">[삭제]</a>&nbsp;&nbsp;
				</font>
			</td>
		</tr>



			</div>
		</div>
	</div>



	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>

	<!-- Header Section Begin -->
	<jsp:include page="${request.contextPath}/NewFooter_JS"></jsp:include>
	<!-- Header End -->

</body>
</html>