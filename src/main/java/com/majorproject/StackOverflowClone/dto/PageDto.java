package com.majorproject.StackOverflowClone.dto;

import com.majorproject.StackOverflowClone.model.Question;
import com.majorproject.StackOverflowClone.model.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageDto {
    private String sortBy;
    private String search;
    private int page;
    private int totalPages;
    private int pageSize;
    private List<Question> questions;
    private List<Tag> tags;
}
