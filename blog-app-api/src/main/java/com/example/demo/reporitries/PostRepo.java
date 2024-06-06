package com.example.demo.reporitries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;

@EnableJpaRepositories
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
	/*
	 * 	@Query("select * from post  where title like :key")
	List<Post> searchByTitle(@Param("key") String title); 
}
	 */
}
