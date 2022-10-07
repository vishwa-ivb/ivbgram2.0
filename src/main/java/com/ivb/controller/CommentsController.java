package com.ivb.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivb.entity.Comments;
import com.ivb.entity.Post;
import com.ivb.service.CommentService;
import com.ivb.service.PostService;

@Controller
public class CommentsController {
	
	@Autowired
	CommentService cdao;
	@Autowired
	PostService pdao;
	
	@PostMapping("/post/comment")
	public @ResponseBody ResponseEntity<?> commentPost(Model m,@RequestParam("postId") String postId,
			@RequestParam("comment") String comment,@RequestParam("commentator") String commentator)
	{
		try {
			long id = Long.parseLong(postId);
			Comments cobj = new Comments();
			Post post = pdao.getPostById(id);
			Date date = new Date();
			
			
			cobj.setComment(comment);
			cobj.setPost(post);
			cobj.setCommentDate(date);
			cobj.setCommentator(commentator);
			
			cdao.saveComment(cobj);
		
//			List<Post> posts = serviceDao.getAllActivePosts();
//			Collections.sort(posts,Comparator.comparingLong(Post::getId));
//			Collections.reverse(posts);
//			m.addAttribute("images", posts);
//			return "home";
			return new ResponseEntity<>("Post Commented Successfully", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/post/allComments")
	public @ResponseBody ResponseEntity<?> allComment(Model m,@RequestParam("postIdo") String postIdo)
	{
		try {
			long id = Long.parseLong(postIdo);
			
			Post post = pdao.getPostById(id);
			List<Comments> comments = cdao.getAllComments(post);
			Collections.reverse(comments);
			
			m.addAttribute("comments", comments);

			return new ResponseEntity<>("Post Commented Successfully", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
