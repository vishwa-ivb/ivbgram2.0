package com.ivb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivb.entity.Comments;
import com.ivb.entity.Post;
import com.ivb.repository.CommentsDAO;

@Service
public class CommentService {

	@Autowired
	private CommentsDAO cdao;
	
	public void saveComment(Comments comment) {
		cdao.save(comment);	
	}

	public List<Comments> getAllComments(Post postId) {
		return cdao.getAllComments(postId);
	}
}
