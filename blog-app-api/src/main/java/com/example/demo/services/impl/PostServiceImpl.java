package com.example.demo.services.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.PostDto;
import com.example.demo.payload.PostResponse;
import com.example.demo.reporitries.CategoryRepo;
import com.example.demo.reporitries.PostRepo;
import com.example.demo.reporitries.UserRepo;
import com.example.demo.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("Default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		post.setTitle(postDto.getTitle());
		System.out.println("SCE32: ---------" + postDto.getTitle());
		System.out.println("SCE32: ---------" + postDto.getContent());
		//post.setCategory(postDto.getCategory());
		//post.setUser(postDto.getUser());
		post.setImageName(postDto.getImageName());
		post.setContent(postDto.getContent());
		
		Post update = this.postRepo.save(post);
		return this.modelMapper.map(update,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		this.postRepo.delete(post);
		
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Post> all = this.postRepo.findAll();
		List<PostDto> collect = all.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Post> postByUser = this.postRepo.findByUser(user);
		List<PostDto> collect = postByUser.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return collect;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", categoryId));
		System.out.println(category.toString()+"-----------------");
		List<Post> postByCategory = this.postRepo.findByCategory(category);
		List<PostDto> collect = postByCategory.stream()
				.map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return collect;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostResponse getPosts(Integer pageNo, Integer pageSize) {
		PageRequest p = PageRequest.of(pageNo, pageSize);
		Page<Post> all = this.postRepo.findAll(p);
		List<Post> content = all.getContent();
		List<PostDto> collect = content.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse post = new PostResponse();
		post.setContent(collect);
		post.setPageNumber(all.getNumber());
		post.setPageSize(all.getSize());
		post.setTotalElements(all.getTotalElements());
		post.setTotalPages(all.getTotalPages());
		post.setLastPage(all.isLast());
		
		return post;
	}

}
