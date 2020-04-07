package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> {
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("total_records")
    private long totalElements;
    private int page;
    private int size;

    @JsonProperty("records")
    private List<T> result;

    public PageResponse() { }

    public PageResponse(Page<T> page) {
        this.result = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber() + 1;
        this.size = page.getSize();
    }

    public PageResponse(List<T> result, int totalPages, long totalElements, int page, int size) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.page = page;
        this.size = size;
        this.result = result;
    }

    public static <T> PageResponse<T> create(Page<T> page) {
        return new PageResponse(page);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}

