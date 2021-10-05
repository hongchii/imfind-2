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
	<script>
function check() {
		
	var confirm_val = confirm("정말 삭제하시겠습니까?");
		
		if(confirm_val) {
			
			
			var cnt = $("input[name='free_del_yn']:checked").length; // 체크된것의 갯수를 구함.
			var freeBno = new Array(); // 체크된 것의 bno를  배열에 담기위해. 
			$("input[name='free_del_yn']:checked").each(function() {
				
				freeBno.push($(this).attr('id'));
				//each함수를 사용해 체크된 것의 id = bno 를 checkArr 배열에 담아줌.
				
			});
			delBno(freeBno);
			console.log("체크1 ====> " + freeBno)
			
		} else {
			
			alert("#");
			
		}
	
}	
function delBno(freeBno) {
		console.log("체크2 ====> " + freeBno)
		
		$.ajax({
			  url : '/deleteArrFree',
			  contentType : 'application/x-www-form-urlencoded;charset=utf-8',
			  data : { "freeBno" : freeBno },
			  type : 'POST',
			  dataType : 'JSON',
			  success : function(data){
				  	console.log("console--" + data);
				 	if(data.RESULT == 'success') {
				 		alert("삭제 성공")
				 		location.href = "/free";
				 	} else {
				 		alert("삭제 실패")
				 	}
			  },//success끝
			  error:function(request,status,error){
			        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
			  }
		  }); 
		  
}
</script>
	
</head>
<body>
	<jsp:include page="${request.contextPath}/el/afterLoginHeader"></jsp:include>


	<div class="wrap">

		<div class="content_wrap">


			<div class="contents">
				<h1 class="title" style="margin-left: 250px; margin-top: 100px;">자유게시판</h1>

				<div class="table_list_wrap"
					style="margin-left: 350px; margin-top: 80px;">
					<table>
						<caption>게시판 목록</caption>
						<colgroup>
							<col style="width: 10%;" />
							<col style="width: 10%;" />
							<col style="width: 15%;" />
							<col style="width: 10%;" />
							<col style="width: 10%;" />
			
						</colgroup>
						<thead>
							<tr class="text-center">
								<th></th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody class="text-center">
							<c:forEach items="${free }" var="free">
								<tr>
									<td><input type="checkbox" id="${free.freeBno }"
										name="free_del_yn" value=""></td>
									<td class="left"><a
										href="/freeInfo?freeBno=${free.freeBno }"> <c:out
												value="${free.freeTitle }" />
									</a></td>
									<td><c:out value="${free.id }" /></td>
									<td><c:out value="${free.freeDate }" /></td>
									<td><c:out value="${free.readcount }" /></td>
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
					<<!-- a href="./insert">[등록]</a>&nbsp;&nbsp;
					<a href="#">[삭제]</a>&nbsp;&nbsp; -->
					<a href="./insert" class="btn write" style="margin-right: 5px;">등록</a>
					<input type="button" class="btn del" id="deleteBtn" value="삭제" onclick="check();" style="width: 82px;">
				</font>
			</td>
		</tr>

				<div class="paging" style="margin-left: 500px;">
					<ul class="btn-group pagination" style = "display : flex;">
					    <c:if test="${pageMaker.prev }">
					    <li>
					        <a href='<c:url value="/free?page=${pageMaker.startPage-1}"/>'><i class="fa fa-chevron-left"></i></a>
					    </li>
					    </c:if>
					    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
					    <li>
					        <a href='<c:url value="/free?page=${pageNum}"/>'><i class="fa">${pageNum }</i></a>
					    </li>
					    </c:forEach>
					    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					    <li>
					        <a href='<c:url value="/free?page=${pageMaker.endPage+1}"/>'><i class="fa fa-chevron-right"></i></a>
					    </li>
					    </c:if>
					</ul>

				</div>
				

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