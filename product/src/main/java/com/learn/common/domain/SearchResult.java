package com.learn.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchResult<T> {

    private List<T> results;
    private int numberOfElements;
    private long totalElements;
    private int currentPageNumber;
    private int totalPages;
    private int pageSize;
    private boolean hasMoreElements;
}
