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
					<c:forEach items="${free }" var="free">
						<tr>
							<td><input type="checkbox" id="${free.freeBno }"
										name="free_del_yn" value="">             ${free.freeBno }</td>
							<td><a href="/freeInfo?freeBno=${free.freeBno }"> 
									<c:out value="${free.freeTitle }" />
							</a></td>
								<td><c:out value="${free.id }" /></td>
								<td><c:out value="${free.freeDate }" /></td>
								<td><c:out value="${free.readcount }" /></td>
						</tr>
					</c:forEach>
				</table>
				<!-- /.table-responsive -->
				
				<!-- 검색 -->
				<div class='row'>
					<div class="col-lg-12">
						<form id='searchForm' action="/free" method="get">
							<select name='type'>
								<option value=""
								<c:out value="${pageMaker.cri.type == null ?'selected':'' }"/>>선택</option>
								<option value="T"
								<c:out value="${pageMaker.cri.type eq 'T' ?'selected':'' }"/>>제목</option>
								<option value="C"
								<c:out value="${pageMaker.cri.type eq 'T' ?'selected':'' }"/>>내용</option>
								<option value="TC"
								<c:out value="${pageMaker.cri.type eq 'TC' ?'selected':'' }"/>>제목 or 내용</option>
								
							</select>
							<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword }"/>'/>
						    <input type="hidden" name="pageNum" value='${pageMaker.cri.page}' />
							<input type="hidden" name="perPageNum" value='${pageMaker.cri.perPageNum}' />
							<button class='btn btn-default'>검색</button>
						</form>
					</div>
				</div>
				
				
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
 					<input type="hidden" name="type" value='${pageMaker.cri.type }'/>
					<input type="hidden" name="keyword" value='${pageMaker.cri.keyword}' /> 

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

	<script>
	function check() {
			
		var confirm_val = confirm("정말 삭제하시겠습니까?");
			
			if(confirm_val) {
				
				
				var cnt = $("input[name='free_del_yn']:checked").length; // 체크된것의 갯수를 구함.
				var freeBno = new Array(); // 체크된 것의 bno를  배열에 담기위해. 
				$("input[name='free_del_yn']:checked").each(function() {
					
					noticeBno.push($(this).attr('id'));
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
	
	// 검색 이벤트
	var searchForm = $("#searchForm");
	
	$("#searchForm button").on("click", function(e){
		
		if(!searchForm.find("option:selected").val()){
			alert("검색종류를 선택하세요");		
			return false;
		}
		
		if(!searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하세요");		
			return false;
		}
		
		serachForm.find("input[name='pageNum']").val("1");
			e.preventDefault();
			serachForm.submit();
	
	}); // end searchForm onclick
</script>
</body>
</html>