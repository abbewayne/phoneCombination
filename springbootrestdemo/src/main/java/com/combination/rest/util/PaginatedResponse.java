package com.combination.rest.util;

import java.util.List;

public class PaginatedResponse {
    private int pageNumber;
    private long totalRow;
    private int row = 10;
    private int totalPage;
    private List<String> result;

    public PaginatedResponse(int page, List<String> input) {
        setPageNumber(page);
        int start = 0;
        int end = 0;
        start = start + (page-1) * row;
        int lastPageRow = input.size() % row;

        end = start + row;
        if (end > input.size()) {
            end = input.size();
        }

        if (page == 0) {
            setResult(input);
        }else {
            setResult(input.subList(start, end));
        }

        setTotalRow(input.size());

        int totalPage = 0;
        if (lastPageRow == 0) {
            totalPage = input.size() / row;
        }else {
            totalPage = input.size() / row + 1;
        }
        setTotalPage(totalPage);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(long totalRow) {
        this.totalRow = totalRow;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
