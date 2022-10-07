package com.ivb.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ivb.entity.Post;
import com.ivb.entity.Profile;

@Repository
public interface ProfileDAO extends JpaRepository<Profile,Long>{
	
	@Transactional 
	@Modifying 
	@Query(value="select * from profiles where MATCH(account) AGAINST(?1)",nativeQuery = true) 
	public List<Profile> searchProfile(String keyword);
	
	@Transactional 
	@Modifying 
	@Query("update Profile p set p.profilepic = ?1 where p.pid = ?2")
	public void updatepropic(byte[] imageData, Long pid);
	
	@Transactional 
	@Modifying 
	@Query("update Profile p set p.about = ?1 where p.pid = ?2")
	public void updateproabout(String about, Long pid);
	
	@Transactional 
	@Modifying 
	@Query("update Profile p set p.email = ?1 where p.pid = ?2")
	public void updateproemail(String email, Long pid);
	
	@Transactional 
	@Modifying 
	@Query("update Profile p set p.posts = ?1 where p.pid = ?2")
	public void addPost(List<Post> post, Long pid);
	
	@Transactional 
	@Modifying 
	@Query("update Profile p set p.postscount = ?1 where p.pid = ?2")
	public void updatePostCount(Long postCount, Long pid);


}
