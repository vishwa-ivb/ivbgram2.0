package com.ivb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
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
import com.ivb.service.PostService;
import com.ivb.service.ProfileService;

@Controller
public class ProfileController {

	@Value("${uploadDir}")
	private String uploadFolder;
	
	@Autowired
	ProfileService dao;
	@Autowired
	PostService postdao;
	
	@RequestMapping("/myprofile")
	public String veiwProfile(Model m,@RequestParam("account") String account)
	{
		List <Profile> profiles = dao.findProfile(account);
		for(Profile p : profiles)
		{
			m.addAttribute("profile", p);
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
			if(propic.isEmpty())
			{
				List <Profile> profiles = dao.findProfile(user);
				for(Profile p : profiles)
				{
					dao.updateProfileAbout(aboutinput, p.getPid());
					dao.updateProfileEmail(emailinput, p.getPid());
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
					break;
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
