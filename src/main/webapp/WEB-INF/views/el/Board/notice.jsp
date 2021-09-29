<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<div class="top_search" >
					<select id="search">
						<option value="">선택</option>
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="type">게시판 타입</option>
						<option value="date">날짜</option>
					</select> 
					<input type="text" placeholder="검색어를 입해주세요." /> 
					<a href="#none" class="btn">검색</a>
				</div>
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
							<col style="width: 10%;" />
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th>공지</th>
								<th>제목</th>
								<th>작성자</th>
								<th>내용</th>
								<th>작성일</th>
								<th>파일</th>
							</tr>
						</thead>
						<tbody class="text-center">
							<c:forEach items="${list }" var="boardlist">
								<tr>
									<td><input type="checkbox" id=${boardlist.board_seq } name="board_del_yn" value="y"></td> 
									<td><c:out value="${boardlist.board_notice }"/></td>
									<td class="left">
										<a href="/soo_project_board/soo_project1_notice_view.do?board_seq=${boardlist.board_seq }">
										<c:out value="${boardlist.board_title }" /></a></td>
									<!-- 기본 가운데 정렬 왼쪽 정렬 하려면 클래스 left 추가 -->
									<td><c:out value="${boardlist.board_writer_name }" /></td>
									<td class="left">
										<a href="/soo_project_board/soo_project1_notice_view.do?board_seq=${boardlist.board_seq }">
										<c:out value="${boardlist.board_content_text }" /></a></td>
									
									<td><c:out value="${boardlist.board_reg_date }" /></td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
		
				<c:if test="${sessionScope.usertype eq '0' }"> 
					<div class="btn_wrap right" style="margin-right: 340px;">
						<!-- <a href="#none" class="btn del">삭제</a>  -->
						<a href="/soo_project_board/soo_project1_notice_board_insert.do" class="btn write" style="margin-right: 5px;">글쓰기</a>
						<input type="button" class="btn del" id="deleteBtn" value="삭제" onclick="check();" style="width: 82px;">
					</div>
				</c:if>
				
				<div class="paging" style="margin-left: 500px;">
					<ul class="btn-group pagination" style = "display : flex;">
					    <c:if test="${pageMaker.prev }">
					    <li>
					        <a href='<c:url value="/soo_project_board/Soo_Edu_Board_Notice_List.do?page=${pageMaker.startPage-1}"/>'><i class="fa fa-chevron-left"></i></a>
					    </li>
					    </c:if>
					    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
					    <li>
					        <a href='<c:url value="/soo_project_board/Soo_Edu_Board_Notice_List.do?page=${pageNum}"/>'><i class="fa">${pageNum }</i></a>
					    </li>
					    </c:forEach>
					    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					    <li>
					        <a href='<c:url value="/soo_project_board/Soo_Edu_Board_Notice_List.do?page=${pageMaker.endPage+1}"/>'><i class="fa fa-chevron-right"></i></a>
					    </li>
					    </c:if>
					</ul>

				</div>
				
			</div>
		</div>
	</div>
</body>
</html>