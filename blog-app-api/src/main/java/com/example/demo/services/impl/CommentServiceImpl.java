package com.example.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.CommentDto;
import com.example.demo.reporitries.CommentRepo;
import com.example.demo.reporitries.PostRepo;
import com.example.demo.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper moddelMapper;
	@Autowired
	private PostRepo postRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		Comment comment = this.moddelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment save = this.commentRepo.save(comment);

		return this.moddelMapper.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
		this.commentRepo.delete(comment);
	}

}
