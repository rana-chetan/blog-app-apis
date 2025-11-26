package com.example.blog.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private int postId;
    private String postTitle;
    private String postContent;
    private String postImage;
    private String addedDate;
    private CategoryDto category;
    private UserDto user;
    private List<CommentDto> comments;
}
