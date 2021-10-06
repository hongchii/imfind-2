package com.spring.imfind.el.paging;

public class Criteria {
	private int page; // 현재 페이지 번호
	private int perPageNum; // 한 페이지당 보여줄 게시글의 갯수

	public int getPageStart() { // 특정 페이지의 게시글 시작 번호, 게시글 시작 행 번호
		return (this.page - 1) * perPageNum;
	}

	public Criteria() {
		this.page = 1; // 현재페이지
		this.perPageNum = 15; // 보여줄 게시글의 수
	} // 처음 게시판에 들어왔을때 기본생성자를 통해 기본 값 셋팅

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) { // 페이지가 음수값이 되지 않게 설정.
			this.page = 1; // 음수가 되면 1페이지
		} else {
			this.page = page;
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int pageCount) {
		int cnt = this.perPageNum;
		if (pageCount != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = pageCount;
		}
	}
}
