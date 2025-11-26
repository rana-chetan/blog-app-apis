package com.example.blog.service.impl;

import com.example.blog.DTOs.PostDto;
import com.example.blog.DTOs.PostResponse;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.model.Category;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.repository.categoryRepository;
import com.example.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private categoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

//        postDto.setPostImage("default.png");
//        postDto.setAddedDate(String.valueOf(new Date().getTime()));
//        postDto.setCategory(this.modelMapper.map(category, CategoryDto.class));
//        postDto.setUser(this.modelMapper.map(user, UserDto.class));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setPostImage("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);

        postRepository.save(post);

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setPostImage(postDto.getPostImage());

        Post updatedPost = postRepository.save(post);

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        postRepository.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> all = postRepository.findAll(pageable);

        List<Post> allPosts = all.getContent();

        List<PostDto> allPostDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostDtos);
        postResponse.setPageNumber(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setTotalElements(all.getTotalElements());
        postResponse.setTotalPages(all.getTotalPages());
        postResponse.setLastPage(all.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Post> all = postRepository.findByCategory(category, pageable);

        List<Post> byCategory = all.getContent();

        List<PostDto> postDtoList = byCategory.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setTotalElements(all.getTotalElements());
        postResponse.setTotalPages(all.getTotalPages());
        postResponse.setLastPage(all.isLast());
        
        return postResponse;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Post> all = postRepository.findByUser(user, pageable);

        List<Post> byUser = all.getContent();

        List<PostDto> postDtoList = byUser.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setTotalElements(all.getTotalElements());
        postResponse.setTotalPages(all.getTotalPages());
        postResponse.setLastPage(all.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> byPostTitleContainingIgnoreCase = postRepository.findByPostTitleContainingIgnoreCase(keyword);
        List<PostDto> postDtoList = byPostTitleContainingIgnoreCase.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).toList();
        return postDtoList;
    }
}
