package com.example.demo.reporitries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
