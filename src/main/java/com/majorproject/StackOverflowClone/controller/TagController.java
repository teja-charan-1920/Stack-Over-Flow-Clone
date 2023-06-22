package com.majorproject.StackOverflowClone.controller;

import com.majorproject.StackOverflowClone.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TagController {
    @Autowired
    TagService tagService;
@GetMapping("/tags")
    public String tags(@RequestParam(name = "search", required = false) String search,
                       @RequestParam(name = "page",required = false,defaultValue = "1") int page,
                       @RequestParam(name = "sort", defaultValue = "createdAt", required = false) String sort,
                       Model model) {
    model.addAttribute("tags",tagService.getTags(search,page,sort));
    return "tags";
}

    @GetMapping("/questions/tagged/{tag}")
    public String tagQuestions(@PathVariable("tag") String name,Model model){
        model.addAttribute("questions",tagService.getQuestionsByTag(name));
        return "allQue";
    }
}
