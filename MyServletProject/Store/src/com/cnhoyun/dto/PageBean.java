package com.cnhoyun.dto;

import java.util.List;

/**
 * 用于展示分页
 * @author: huakaimay
 * @since: 2020-11-24
 */
public class PageBean<T> {
    // 当前页
    private int currentPage;
    // 当前页数据
    private List<T> pageList;
    // 总页数
    private int totalPage;
    // 页面大小
    private int pageSize;
    // 所有的数据大小
    private int totalSize;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageList=" + pageList +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", totalSize=" + totalSize +
                '}';
    }
}
