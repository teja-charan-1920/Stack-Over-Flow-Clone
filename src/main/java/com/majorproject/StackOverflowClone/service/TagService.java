package com.majorproject.StackOverflowClone.service;

import com.majorproject.StackOverflowClone.dto.PageDto;
import com.majorproject.StackOverflowClone.model.Tag;
import com.majorproject.StackOverflowClone.repository.TagRepository;
import com.majorproject.StackOverflowClone.specification.TagSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    private static final int PAGESIZE = 36;
    private TagSpecification tagSpecification = new TagSpecification();
    @Autowired
    TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }


    public PageDto getQuestionsByTag(String name) {
        PageDto pageDto = new PageDto();
        pageDto.setQuestions(new ArrayList<>(tagRepository.findByName(name).getQuestions()));
        pageDto.setTags(getAllTags());
        return pageDto;
    }

    public long countTotalTags() {
        return tagRepository.count();
    }

    public Object getTags(String search, int page, String sort) {
        Pageable pageable = PageRequest.of(page - 1, PAGESIZE, Sort.by(Sort.Direction.DESC, sort));
        Specification<Tag> specification = Specification.where(null);

        if (search != null) {
            specification = tagSpecification.searchByKeyword(search);
        }
        Page<Tag> tagPage = tagRepository.findAll(specification, pageable);

        PageDto pageDto = new PageDto();
        pageDto.setTags(tagPage.getContent());
        pageDto.setTotalPages(tagPage.getTotalPages());
        pageDto.setPage(page);
        pageDto.setSearch(search);
        pageDto.setSortBy(sort);
        return pageDto;
    }
}
