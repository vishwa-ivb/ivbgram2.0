package com.ivb.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ivb.entity.Post;
import com.ivb.entity.Profile;
import com.ivb.service.LoginService;
import com.ivb.service.PostService;
import com.ivb.service.ProfileService;

@Controller
public class ProfileController {

	@Value("${uploadDir}")
	private String uploadFolder;
	
	@Autowired
	LoginService logindao;
	@Autowired
	ProfileService dao;
	@Autowired
	PostService postdao;
	@Autowired
	LoginController loginc;
	
	
	@RequestMapping("/showprofile")
	public String veiwProfile(Model m,@RequestParam("account") String account,@RequestParam("currentuser") String currentuser)
	{
		String loggedUser = loginc.loggedUser;
		if(loggedUser.equals(currentuser))
		{
			List <Profile> profiles = dao.findProfile(account);
			for(Profile p : profiles)
			{
				m.addAttribute("profile", p);
				List<Post> userposts = p.getPosts();
				Collections.sort(userposts,Comparator.comparingLong(Post::getId));
				Collections.reverse(userposts);
				m.addAttribute("userposts", userposts);
				m.addAttribute("currentuser", currentuser);
				break;
			}
		}
		
		return "profile";
	}
	
	@RequestMapping("/showotherprofile")
	public String veiwotherProfile(Model m,@RequestParam("account") String account,@RequestParam("currentuser") String currentuser)
	{
		List <Profile> profiles = dao.findProfile(account);
		for(Profile p : profiles)
		{
			m.addAttribute("profile", p);
			List<Post> userposts = p.getPosts();
			Collections.sort(userposts,Comparator.comparingLong(Post::getId));
			Collections.reverse(userposts);
			m.addAttribute("userposts", userposts);
			m.addAttribute("currentuser", currentuser);
			break;
		}
		
		return "profile";
	}
	
	
	@PostMapping("/profile/update")
	public @ResponseBody ResponseEntity<?> updateProfilePic(Model m,@RequestParam("user") String user,
			@RequestParam("emailinput") String emailinput,@RequestParam("aboutinput") String aboutinput,
			HttpServletRequest request,final @RequestParam("propicupload") MultipartFile propic)
	{

		try {
			String loggedUser = loginc.loggedUser;
			if(loggedUser.equals(user))
			{
			if(propic.isEmpty())
			{
				List <Profile> profiles = dao.findProfile(user);
				for(Profile p : profiles)
				{
					dao.updateProfileAbout(aboutinput, p.getPid());
					dao.updateProfileEmail(emailinput, p.getPid());
					logindao.updateEmailId(emailinput, p.getAccount());
					break;
				}
			}
			else
			{
				byte[] imageData = propic.getBytes();
				List <Profile> profiles = dao.findProfile(user);
				for(Profile p : profiles)
				{
					dao.updateProfile(imageData, p.getPid());
					dao.updateProfileAbout(aboutinput, p.getPid());
					dao.updateProfileEmail(emailinput, p.getPid());
					logindao.updateEmailId(emailinput, p.getAccount());
					break;
				}
			}
			}
			
			return new ResponseEntity<>("Post Saved With File - " , HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@GetMapping(value={"/profile/picture/{id}"})
	@ResponseBody
	
	public void showImage(Model m,HttpServletResponse response,@PathVariable("id") String pid,Profile profile) throws IOException
	{
		long id = Long.parseLong(pid);
		profile = dao.getProfile(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		byte[] image = profile.getProfilepic();
		response.getOutputStream().write(image);
		response.getOutputStream().close();
	}
	
}
