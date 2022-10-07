package com.ivb.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ivb.entity.Post;

@Repository
public interface PostDAO extends JpaRepository<Post,Long>{
	
	@Transactional 
	@Modifying 
	@Query("update Post p set p.content = ?1 where p.id = ?2") 
	public void updatePost(String content, Long id);
	
	@Transactional 
	@Modifying 
	@Query(value="select * from posts where MATCH(account,content) AGAINST(?1)",nativeQuery = true) 
	public List<Post> search(String keyword);

	@Transactional 
	@Modifying 
	@Query(value="select * from posts where account= ?1",nativeQuery = true) 
	public List<Post> getByUser(String user);

}
