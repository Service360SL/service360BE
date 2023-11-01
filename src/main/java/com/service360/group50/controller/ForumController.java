package com.service360.group50.controller;

import com.service360.group50.entity.ForumQuestion;
import com.service360.group50.service.ForumService;
import com.service360.group50.service.ImageService;
import com.service360.group50.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ForumController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ForumService forumService;

    @Autowired
    private UserService userService;

    @PostMapping("auth/forum/createQuestion")
    public ForumQuestion addQuestion(
            @RequestParam("images") MultipartFile[] images,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("userid") Long userid,
            @RequestParam("role") String role
    ) {
        if( role.equals("SERVICEPROVIDER")){
            String uploadDirectory = "src/main/resources/static/images/Forum/FQImages/";
            String FQimages = "";
            for (MultipartFile imageFile : images) {
                try {
                    FQimages += imageService.saveImageToStorage(uploadDirectory, imageFile) + ",";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

                ForumQuestion forumQuestion = ForumQuestion.builder()
                    .QuestionTitle(title)
                    .Description(description)
                    .QuestionImages(FQimages)
                    .Category(category)
                    .user(userService.getUser(userid))
                    .build();

            forumService.CreateQuestion(forumQuestion);

            return forumQuestion;
        }

        return null;
    }
}
