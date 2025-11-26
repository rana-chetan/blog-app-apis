package com.example.blog.service;

import com.example.blog.DTOs.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, int postId, int userId);

    void deleteComment(int commentId);
}
