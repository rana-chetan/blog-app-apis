package com.example.blog.controller;

import com.example.blog.DTOs.ApiResponse;
import com.example.blog.DTOs.PostDto;
import com.example.blog.DTOs.PostResponse;
import com.example.blog.config.AppConstants;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {

        PostDto post = postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
                                                       @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PostResponse posts = postService.getPostsByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
                                                           @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PostResponse posts = postService.getPostsByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        PostResponse posts = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/search")
    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam("keyword") String keyword) {
        List<PostDto> posts = postService.searchPosts(keyword);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        ApiResponse apiResponse = new ApiResponse("Post deleted Successfully", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

}
