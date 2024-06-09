package com.example.demo.services;

import com.example.demo.payload.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);

}
