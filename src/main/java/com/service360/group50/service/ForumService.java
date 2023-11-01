package com.service360.group50.service;

import com.service360.group50.entity.ForumQuestion;
import com.service360.group50.repo.ForumQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumService {

    @Autowired
    private ForumQuestionRepository forumQuestionRepository;

    public ForumQuestion CreateQuestion(ForumQuestion question) {
        return forumQuestionRepository.save(question);
    }
}
