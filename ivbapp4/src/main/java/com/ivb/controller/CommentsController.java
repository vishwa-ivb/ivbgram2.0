package com.ivb.controller;

import java.util.Collections;
import java.util.Comparator;
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
import com.ivb.entity.Notification;
import com.ivb.entity.Post;
import com.ivb.entity.Profile;
import com.ivb.service.CommentService;
import com.ivb.service.NotificationService;
import com.ivb.service.PostService;
import com.ivb.service.ProfileService;

@Controller
public class CommentsController {
	
	@Autowired
	CommentService cdao;
	@Autowired
	PostService pdao;
	@Autowired
	ProfileService prodao;
	@Autowired
	NotificationService ndao;
	
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
			
			Notification noti = new Notification();
			List<Profile> profileList= prodao.findProfile(post.getAccount());
			Profile profile=null;
			for(Profile pro: profileList) {
				profile = pro;
				break;
			}
			noti.setProfileNoti(profile);
			noti.setNotimsg(commentator+" commented \""+ comment +"\" on your post.");
			noti.setConnectId(id);
			noti.setIs_read(0);
			noti.setNotiDate(date);
			ndao.saveNotification(noti);
		
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
			Collections.sort(comments,Comparator.comparingLong(Comments::getCommentId));
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
