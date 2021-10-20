
console.log("댓글 !");

var replyService = (function(){
	
	function add(reply, callback, error){
		console.log(" 댓글 달기");
		
		$.ajax({
			type : 'POST',
			url : '/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	
	
	function getList(param, callback, error){
		var bno = param.bno;
		var page = param.page || 1;
		//debugger;
		$.getJSON("/pages/" + bno + "/" + page + ".json",
			function(data){
				if (callback){
				//	debugger;
					callback(data);
				}
			}).fail(function(xhr, status, err){
				if (error){
					error();
				}
			});
	}
	
	function remove(rno, callback, error){
		$.ajax({
			type : 'delete',
			url : rno,
			success : function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function update(reply, callback, error){
		console.log("댓글 번호 --  :: " + reply.rno);
		
		$.ajax({
			type : 'put',
			url : reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if (callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if (error){
					error(er);
				}
			}
		});
	}
	
	function get(rno, callback, error){
		$.get(rno + ".json", function(result){
			if(callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}	
		return {
			add : add,
			getList : getList,
			remove : remove,
			update : update,
			get : get
			
	};
	
})();