package com.learn.common.helper;

import com.learn.common.domain.SearchResult;
import org.springframework.data.domain.Page;

public class SearchResultsHelper {

    public static SearchResult<?> convertToSearchResults(Page<?> result) {

        return new SearchResult(
                result.getContent(),
                result.getNumberOfElements(),
                result.getTotalElements(),
                result.getPageable().getPageNumber(),
                result.getTotalPages(),
                result.getPageable().getPageSize(),
                !result.isLast()
        );
    }
}
