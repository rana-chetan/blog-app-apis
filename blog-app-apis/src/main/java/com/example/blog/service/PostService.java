package com.example.blog.service;

import com.example.blog.DTOs.PostDto;
import com.example.blog.DTOs.PostResponse;

import java.util.List;

public interface PostService{

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostDto getPostById(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

    PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize);

    List<PostDto> searchPosts(String keyword);
}
