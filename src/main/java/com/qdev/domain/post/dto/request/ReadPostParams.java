package com.qdev.domain.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReadPostParams {

    private final String searchType;
    private final String Keyword;

    @Builder
    public ReadPostParams(String searchType, String keyword) {
        this.searchType = searchType;
        Keyword = keyword;
    }
}
