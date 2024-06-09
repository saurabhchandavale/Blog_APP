package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CommentDto;
import com.example.demo.services.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
		CommentDto comment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Comment", true), HttpStatus.OK);

	}
}
