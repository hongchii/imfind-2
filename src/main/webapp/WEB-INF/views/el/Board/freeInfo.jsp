<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
	
	<style type="text/css">
		.uploadResult {
			width: 100%;
			background-color: white;
		}
		
		.uploadResult ul {
			display: flex;
			flex-flow: row;
			justify-content: center;
			align-items: center;
		}
		
		.uploadResult ul li {
			list-style none;
			padding: 10px;
			align-content: center;
			text-align: center;
		}
		
		.uploadResult ul li img {
			width: 100px;
		}
		
		.uploadResult ul li span {
			color: white;
		}
		
		.bigPictureWrapper {
			position: absolute;
			display: none;
			justify-content: center;
			align-items: center;
			top: 0%;
			width: 100%;
			height: 100%;
			background-color: gray;
			z-index: 100;
			background: rgba(255,255,255,0.5);
		}
		
		.bigPicture {
			position: relative;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		
		.bigPicture img {
			width: 600px;
		}
	
	</style>
<jsp:include page="${request.contextPath}/NewHeader_CSS"></jsp:include>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	
</head>
<body>
	<jsp:include page="${request.contextPath}/el/afterLoginHeader"></jsp:include>
	<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default" style="margin-top: 100px; width: 1030px;margin-left: 250px;">

			<div class="panel-heading">자유게시판</div>
			<!-- /.pannel-heading -->
			<div class="panel-body" style="margin-top: 30px; width: 1030px;">

				<div class="form-group">
					<label>글번호</label> <input class="form-control" name="FreeBno"
						value="<c:out value="${info.freeBno }"/>" readonly="readonly">
					
					<label>조회수</label> <input class="form-control" name="readCount"
						value="<c:out value="${info.readcount }"/>" readonly="readonly">	
				</div>
				
				<div class="form-group">
					<label>작성자</label> <input class="form-control" name="freeId"
						value="<c:out value="${info.id }"/>" readonly="readonly">
				</div>

				<div class="form-group">
					<label>제목</label><input class="form-control" name="freeTitle"
						value="<c:out value="${info.freeTitle }"/>" readonly="readonly">
				</div>

				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="3" name="freeContent"
						readonly="readonly">
					<c:out value="${info.freeContent }" />
					</textarea>
				</div>
				
				<!-- 파일첨부 -->
				<div class="bigPictureWrapper">
					<div class="bigPicture"></div>
				</div>
				
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default"
							style="width: 1000px;">
							<div class="panel-heading">첨부파일</div>
							<div class="panel-body">
								<div class="uploadResult">
									<ul>
									</ul>
								</div>
								<!-- <button id="uploadBtn">Upload</button> -->
							</div>
						</div>
					</div>
				</div>
	

				<button data-oper="modify" class="btn btn-default"
					onclick="location.href='./getModifyFree?freeBno=<c:out value="${info.freeBno }"/>'">수정</button>
				<button data-oper="list" class="btn btn-info" onclick="location.href='./free'">목록</button>
				<button data-oper="delete" class="btn btn-default" onclick="location.href='./deleteFree?freeBno=<c:out value="${info.freeBno }"/>'">삭제</button>	
				
			</div>
			<!-- end panel-body -->
		</div>
		<!-- end pane-body -->
	</div>
	<!-- end panel -->
</div>

<div class="row">
	<div class="col-lg-12">
	<!-- panel -->
		<div class="panel panel-default" style="width: 1030px;margin-left: 250px;">
			<!-- <div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i> 댓 글
			</div> -->
			<div class="panel-heading">
			<i class="fa fa-comments fa-fw"></i> 댓 글
				<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>댓글 달기</button>
			
			<div class="panel-body">
				
				<ul class="chat">
					<!-- start reply -->
					<li class="left clearfix" data-rno='12'>
					 <div>
					  <div class="header">
						<strong class="primary-font">user00</strong>
						<small class="pull-right text-muted">2021-01-01 11:11</small>
					  </div>
					  	<p>댓글 입니다.</p>
					</div>
				  </li>
				</ul>
			</div>
		</div>
	</div>
</div>

	<!-- Header Section Begin -->
	<jsp:include page="${request.contextPath}/NewFooter_JS"></jsp:include>
	<!-- Header End -->
	
	<!-- 댓글 모달 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
		<div class="dialog">
			<div class="modal-content" style="width: 1030px; margin-left: 250px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">댓글 등록</h4>
				</div>
				
				<div class="modal-body">
					<div class="form-group">
						<label>댓글 내용</label>
						<input class="form-control" name="reply_f" value="New Reply!">
					</div>
					<div class="form-group">
						<label>작성자</label>
						<input class="form-control" name="replyer_f" value="replyer">
					</div>
					<div class="form-group">
						<label>Reply Date</label>
						<input class="form-control" name="reply_f_date" value="">
					</div>
				</div>
				
				<div class="modal-footer">
					<button id="modalModBtn" type="button" class="btn btn-warning">수정</button>
					<button id="modalRemoveBtn" type="button" class="btn btn-danger">삭제</button>
					<button id="modalRegisterBtn" type="button" class="btn btn-default" data-dismiss="modal">등록</button>
					<button id="modalCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript" src="/resources/el/js/Board/freeReply.js"></script>
	<script>
	$(document).ready(function(){
		
		(function(){
			
			
			var bno = '<c:out value="${info.freeBno }"/>';
			var replyUL = $(".chat");
			
				showList(1);
				
				function showList(page){
					
					replyService.getList( { bno : bno, page : page || 1 }, function(list) {
						
						var str = "";
						
						if(list == null || list.length == 0){
							replyUL.html("");
							
							return;
						}
						for(var i = 0, len = list.length || 0; i < len; i++){
							str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
							str += " <div><div class='header'><strong class='primary-font'>"+list[i].replyer_f+"</strong>";
						//	str += "  <small class='pull-right text-muted'>"+replyService.displayTime(list[i].reply_f_date)+"</small></div>";
							str += "  <small class='pull-right text-muted'>"+list[i].reply_f_date+"</small></div>";
							str += "   <p>"+list[i].reply_f+"</p></div></li>";
						}
						replyUL.html(str);
					});
				}
	
			// 댓글 모달
			var modal = $(".modal");
		    var modalInputReply = modal.find("input[name='reply_f']");
		    var modalInputReplyer = modal.find("input[name='replyer_f']");
		    var modalInputReplyDate = modal.find("input[name='reply_f_date']");
			
		    var modalModBtn = $("#modalModBtn");
		    var modalRemoveBtn = $("#modalRemoveBtn");
		    var modalRegisterBtn = $("#modalRegisterBtn");
		    
		    $("#addReplyBtn").on("click", function(e){
		    	modal.find("input").val("");
		    	modalInputReplyDate.closest("div").hide();
		    	modal.find("button[id !='modalCloseBtn']").hide();
		    	
		    	modalRegisterBtn.show();
		    	
		    	$(".modal").modal("show");
		    });

		    
		    modalRegisterBtn.on("click", function(e){
		    	var reply = {
		    			reply_f : modalInputReply.val(),
		    			replyer_f : modalInputReplyer.val(),
		    			bno: bno
		    	};
		    	
		    	replyService.add(reply, function(result){
		    		
		    		alert("댓글을 등록했습니다.");
		    		
		    		modal.find("input").val("");
		    		modal.modal("hide");
		    		
		    		showList(1);
		    	});
		    });
		    	
		    
		  	$(".chat").on("click", "li", function(e){
		  		var rno = $(this).data("rno");
		  		console.log("rno" + rno);
		  		
		  		replyService.get(rno, function(reply){
					modalInputReply.val(reply.reply_f);
					modalInputReplyer.val(reply.replyer_f);
					modalInputReplyDate.val(reply.reply_f_date).attr("readonly","readonly");
					modal.data("rno", reply.rno);
					
					modal.find("button[id !='modalCloseBtn']").hide();
					modalModBtn.show();
					modalRemoveBtn.show();
					
					$(".modal").modal("show");
				});
		  	});
		  	
		  	modalModBtn.on("click", function(e){
		  		var reply = {rno:modal.data("rno"), reply_f: modalInputReply.val()};
		  		
		  		replyService.update(reply, function(result){
					
		  			alert("댓글을 수정했습니다.");
		  			modal.modal("hide");
		  			showList(1);
				});
		  	});
		  	
		  	modalRemoveBtn.on("click", function(e){
		  		var rno = modal.data("rno");

				replyService.remove(rno, function(result){
					
					alert("댓글을 삭제했습니다.");
					modal.modal("hide");
					showList(1);
				});
				
		  	})
		  	
		  	
		  	
		  	// 첨부 파일
		    $.getJSON("/getAttachList_f",{bno : bno}, function(arr){
				console.log(arr);
				
				var str = "";
				
				$(arr).each(function(i, attach){
					
				//image type
				if(attach.fileType){
					var fileCallPath = encodeURIComponent(attach.uploadPath+"/s_"+attach.uuid+"_"+attach.fileName);
					
					str += "<li data-path='"+attach.uploadPath+"' data-uuid'"+attach.uuid+"'data-filename'"+attach.fileName+"'data-type='"+attach.fileType+"'><div>";
					str += "<img src='/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str += "</li>";
				} else {
					str += "<li data-path='"+attach.uploadPath+"' data-uuid'"+attach.uuid+"'data-filename'"+attach.fileName+"'data-type='"+attach.fileType+"'><div>";
					str += "<span>" + attach.fileName+"</span><br/>"
					str += "<img src='/resources/img/attach.png'>";
					str += "</div>";
					str += "</li>";
				}
				});
				
				$(".uploadResult ul").html(str);
			}); // end getJSON
		})(); // end function 
		
		
	}); // end document ready
	
	
	
	</script>
</body>
</html>