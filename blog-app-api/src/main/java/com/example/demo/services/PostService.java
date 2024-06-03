package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Post;
import com.example.demo.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	Post updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	List<Post> getAllPosts();

	Post getPostById(Integer postId);

	List<PostDto> getPostsByUser(Integer userId);

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> searchPosts(String keyword);
}
