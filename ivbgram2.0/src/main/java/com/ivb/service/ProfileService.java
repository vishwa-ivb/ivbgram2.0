package com.ivb.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ivb.entity.Post;
import com.ivb.entity.Profile;
import com.ivb.repository.ProfileDAO;

@Service
public class ProfileService {

	@Autowired
	private ProfileDAO profiledao;
	
	public Profile getProfile(Long id) {
		return profiledao.findById(id).get();
	}

	public List<Profile> findProfile(String keyword){
		return profiledao.searchProfile(keyword);
	}
	
	public List<Profile> findEveryProfile(String keyword){
		return profiledao.searchEveryProfile(keyword);		
	}
	
	public void updateProfileEmail(String email, Long pid) {
		profiledao.updateproemail(email, pid);
	}
	
	public void updateProfileAbout(String about, Long pid) {
		profiledao.updateproabout(about, pid);
	}
	
	public void updateProfile(byte[] imageData, Long pid) {
		profiledao.updatepropic(imageData, pid);
	}
	
	public void addPost(List<Post> posts, Long pid) {
		profiledao.addPost(posts, pid);
	}
	
	public void updatePostCount(Long postCount, Long pid) {
		profiledao.updatePostCount(postCount, pid);
	}
	
	public void save(Profile pro) {
		profiledao.save(pro);
		
	}

}