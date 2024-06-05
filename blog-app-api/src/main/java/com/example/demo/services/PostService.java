package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Post;
import com.example.demo.payload.PostDto;
import com.example.demo.payload.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	List<PostDto> getAllPosts();

	PostDto getPostById(Integer postId);

	List<PostDto> getPostsByUser(Integer userId);

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> searchPosts(String keyword);
	
	PostResponse getPosts(Integer pageNo, Integer pageSize);
	
}
