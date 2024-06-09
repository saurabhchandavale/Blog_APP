package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.AppConstants;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.PostDto;
import com.example.demo.payload.PostResponse;
import com.example.demo.services.FileService;
import com.example.demo.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto post = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<>(post, HttpStatus.CREATED);

	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<PostDto>> getAllPosts() {
		List<PostDto> allPosts = this.postService.getAllPosts();
		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletPost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}

	@PutMapping("{postId}")
	public ResponseEntity<PostDto> updatePostByPotId(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	// http://localhost:8080/api/post/posts?pageNumber=1&pageSize=8&sortBy=postId&sortDir=asc
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PostResponse posts = this.postService.getPosts(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);

	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {
		List<PostDto> searchPosts = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);

	}

	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		String fileName = this.fileService.uploadImage(path, image);
		PostDto postById = this.postService.getPostById(postId);
		postById.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postById, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	// http://localhost:8080/api/post/images/695049dd-0c6d-48f7-b18c-5c17f2f78daf.PNG
	@GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downLoadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws FileNotFoundException {
		InputStream reource = this.fileService.getReource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		try {
			StreamUtils.copy(reource, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
