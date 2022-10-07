package com.ivb.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ivb.entity.Comments;
import com.ivb.entity.Post;

public interface CommentsDAO extends JpaRepository<Comments, Long>{
	
	@Transactional 
	@Modifying 
	@Query("select c from Comments c where c.post = ?1") 
	public List<Comments> getAllComments(Post postId);

}
