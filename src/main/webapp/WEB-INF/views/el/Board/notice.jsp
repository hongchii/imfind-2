<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	공지사항 입니다.

	<div class="wrap">
		<div class="header_wrap">
			<div class="header">
				<a href="/index.do">HOME</a>
			</div>
		</div>
		<div class="content_wrap">


			<div class="contents">
				<h1 class="title">공지사항</h1>
				
				<div class="table_list_wrap" style="margin-left: 200px;">
					<table>
						<caption>게시판 목록</caption>
						<colgroup>
							<col style="width: 5%;" />
							<col style="width: 10%;" />
							<col style="width: 15%;" />
							<col style="width: 10%;" />
							<col style="width: 35%;" />
							<col style="width: 10%;" />
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th>글번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>내용</th>
								<th>작성일</th>
							</tr>
						</thead>

					</table>
				</div>

				<div class="btn_wrap right" style="margin-right: 340px;">
					<!-- <a href="#none" class="btn del">삭제</a>  -->
					<a href="./insert" class="btn write" style="margin-right: 5px;">글쓰기</a>
					<input type="button" class="btn del" id="deleteBtn" value="삭제"
						onclick="check();" style="width: 82px;">
				</div>



			</div>
		</div>
	</div>
</body>
</html>