package org.task.core;

import java.util.List;


/**
 * Pagination data class
 */
public class Page {

	private List result;
	private int pageNumber;
	private int pageSize;
	private boolean hasMore;

	public Page(List result, int pageNumber, int pageSize, boolean hasMore) {
		this.result = result;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.hasMore = hasMore;
	}

	public List getResult() {
		return result;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public boolean hasMore() {
		return hasMore;
	}

	@Override
	public String toString() {
		return "Page{" +
				"result=" + result +
				", pageNumber=" + pageNumber +
				", pageSize=" + pageSize +
				", hasMore=" + hasMore +
				'}';
	}
}
