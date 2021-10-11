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
	<script>
function check() {
		
	var confirm_val = confirm("정말 삭제하시겠습니까?");
		
		if(confirm_val) {
			
			
			var cnt = $("input[name='notice_del_yn']:checked").length; // 체크된것의 갯수를 구함.
			var noticeBno = new Array(); // 체크된 것의 bno를  배열에 담기위해. 
			$("input[name='notice_del_yn']:checked").each(function() {
				
				noticeBno.push($(this).attr('id'));
				//each함수를 사용해 체크된 것의 id = bno 를 checkArr 배열에 담아줌.
				
			});
			delBno(noticeBno);
			console.log("체크1 ====> " + noticeBno)
			
		} else {
			
			alert("#");
			
		}
	
}	
function delBno(noticeBno) {
		console.log("체크2 ====> " + noticeBno)
		
		$.ajax({
			  url : '/deleteArrNotice',
			  contentType : 'application/x-www-form-urlencoded;charset=utf-8',
			  data : { "noticeBno" : noticeBno },
			  type : 'POST',
			  dataType : 'JSON',
			  success : function(data){
				  	console.log("console--" + data);
				 	if(data.RESULT == 'success') {
				 		alert("삭제 성공")
				 		location.href = "/notice";
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
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default" style="margin-top: 100px; width: 1030px;margin-left: 250px;">
			<div class="panel-heading">
				공지사항
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body" style="margin-top: 30px; width: 1030px;">
				<table width="50%"
					class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<c:forEach items="${notice }" var="notice">
						<tr>
							<td><input type="checkbox" id="${notice.noticeBno }"
										name="notice_del_yn" value="">             ${notice.noticeBno }</td>
							<td><a href="/noticeInfo?noticeBno=${notice.noticeBno }">
									<c:out value="${notice.noticeTitle }" />
							</a></td>
							<td><c:out value="${notice.id }" /></td>
							<td><c:out value="${notice.noticeDate }" /></td>
							<td><c:out value="${notice.readcount }" /></td>
						</tr>
					</c:forEach>
				</table>
				<!-- /.table-responsive -->
				
				<div class="paging" style="margin-left: 500px;">
					<ul class="btn-group pagination" style = "display : flex;">
					    <c:if test="${pageMaker.prev }">
					    <li>
					        <a href='<c:url value="/notice?page=${pageMaker.startPage-1}"/>'><i class="fa fa-chevron-left"></i></a>
					    </li>
					    </c:if>
					    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
					    <li>
					        <a href='<c:url value="/notice?page=${pageNum}"/>'><i class="fa">${pageNum }</i></a>
					    </li>
					    </c:forEach>
					    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					    <li>
					        <a href='<c:url value="/notice?page=${pageMaker.endPage+1}"/>'><i class="fa fa-chevron-right"></i></a>
					    </li>
					    </c:if>
					</ul>

				</div>
				
				<button data-oper="create" class="btn btn-default"
					onclick="location.href='./insert'">등록</button>
				<button data-oper="delete" class="btn btn-default"
					onclick="check();">삭제</button>	
										
				<!-- Modal 추가 -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aira-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Modal title</h4>
							</div>
							<div class="modal-body">처리가 완료되었습니다.</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss='modal'>Close</button>
								<button type="button" class="btn btn-primary">Save
									changes</button>
							</div>
						</div>
						<!-- modal-content -->
					</div>
					<!-- modal-dialog -->
				</div>
				<!-- /.modal -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>

	<!-- Header Section Begin -->
	<jsp:include page="${request.contextPath}/NewFooter_JS"></jsp:include>
	<!-- Header End -->

</body>
</html>