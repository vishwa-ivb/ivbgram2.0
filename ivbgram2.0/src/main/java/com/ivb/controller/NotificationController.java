package com.ivb.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivb.entity.Comments;
import com.ivb.entity.Post;
import com.ivb.entity.Profile;
import com.ivb.service.CommentService;
import com.ivb.service.NotificationService;
import com.ivb.service.PostService;
import com.ivb.service.ProfileService;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationService ndao;
	@Autowired
	ProfileService pdao;
	@Autowired
	PostService postdao;
	@Autowired
	CommentService cdao;
	@Autowired
	LoginController lcon;
	
	@RequestMapping("/notificationReaded")
	public void readednoti() {
		String user= lcon.loggedUser;
		List<Profile> profiles = pdao.findProfile(user);
		Profile profile = null;
		for(Profile pro : profiles) {
			profile = pro;
			break;
		}
		ndao.readednoti(profile.getPid());
	}
	
	@PostMapping("/notification/show")
	public @ResponseBody ResponseEntity<?> connectid(Model m,@RequestParam("connectid") String connectid)
	{
		try {
			long id = Long.parseLong(connectid);
			
			Post post = postdao.getPostById(id);
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
