package com.ivb.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivb.entity.Post;
import com.ivb.repository.PostDAO;

@Service
public class PostService {

	@Autowired
	private PostDAO postdao;
	
	public void savePost(Post post) {
		postdao.save(post);	
	}
	
	public void updatePost(String content, Long id) {
		postdao.updatePost(content, id);
	}
	
	public void deletePost(Long id) {
		postdao.deleteById(id);
	}

	public List<Post> getAllActivePosts() {
		return postdao.findAll();
	}

//	public Optional<Post> getPostById(Long id) {
//		return postdao.findById(id);
//	}
	
	public Post getPostById(Long id) {
		return postdao.findById(id).get();
	}
	
	public List<Post> search(String keyword){
		return postdao.search(keyword);
	}

	public List<Post> getByUser(String user) {
		return postdao.getByUser(user);
	}
}