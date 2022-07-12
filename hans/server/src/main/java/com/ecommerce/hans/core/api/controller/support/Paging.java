package com.ecommerce.hans.core.api.controller.support;

public class Paging {

    private int pageNumber;

    private int pageSize;

    private int totalPages;

    private long totalCount;

    /**
     * @param { pageNumber}
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * @return { pageNumber }
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * @param { pageSize}
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @return { pageSize }
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @param { totalPages}
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @return { totalPages }
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @param { totalCount}
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * @return { totalCount }
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
