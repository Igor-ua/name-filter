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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Page page = (Page) o;

		if (pageNumber != page.pageNumber) return false;
		if (pageSize != page.pageSize) return false;
		if (hasMore != page.hasMore) return false;
		return result != null ? result.equals(page.result) : page.result == null;
	}

	@Override
	public int hashCode() {
		int result1 = result != null ? result.hashCode() : 0;
		result1 = 31 * result1 + pageNumber;
		result1 = 31 * result1 + pageSize;
		result1 = 31 * result1 + (hasMore ? 1 : 0);
		return result1;
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
