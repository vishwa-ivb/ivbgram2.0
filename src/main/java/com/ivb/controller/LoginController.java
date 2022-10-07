package com.ivb.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ivb.entity.Login;
import com.ivb.entity.Post;
import com.ivb.entity.Profile;
import com.ivb.repository.LoginDAO;
import com.ivb.repository.PostDAO;
import com.ivb.repository.ProfileDAO;
import com.ivb.service.PostService;
import com.ivb.service.ProfileService;

@Controller
public class LoginController {

	@Autowired
	LoginDAO dao;
	@Autowired
	ProfileService profiledao;
	@Autowired
	PostDAO postdao;
	@Autowired
	PostService serviceDao;
	
	@RequestMapping(value={"/","signin"})
	public String login() {
		return "index";
	}
	
	@RequestMapping("home")
	public String signin(Model m, @RequestParam("uname")String uname, @RequestParam("pass")String pass) {
		if(uname.equals(""))
		{
			m.addAttribute("no_acc", "Username Required!");
			return "index";
		}
		else if(pass.equals("")) 
		{
			m.addAttribute("no_acc", "Password Required!");
			return "index";
		}
		else {
			Login username;
			String password;
			Login password4home;
			
			if(dao.existsById(uname)) 
			{
				username = dao.getReferenceById(uname);
				password4home = dao.getReferenceById(pass);
				m.addAttribute("user", username.getUname());
				m.addAttribute("pass", password4home.getUname());
				password = username.getPass();
				if(password.equals(pass))
				{
					List<Post> posts = serviceDao.getAllActivePosts();
					Collections.sort(posts,Comparator.comparingLong(Post::getId));
					Collections.reverse(posts);
					m.addAttribute("images", posts);
					
					List <Profile> profiles = profiledao.findProfile(uname);
					for(Profile p : profiles)
					{
						Profile pro = new Profile();
						pro.setPid(p.getPid());
						pro.setAbout(p.getAbout());
						pro.setProfilepic(p.getProfilepic());
						pro.setAccount(p.getAccount());
						pro.setEmail(p.getEmail());
						
						Long postCount = (long) serviceDao.getByUser(uname).size();
						pro.setPostscount(postCount);
						profiledao.save(pro);
						break;
					}
					for(Profile p : profiles)
					{
						m.addAttribute("profile", p);
						break;
					} 
					
					return "home";
				}
				else
				{
					m.addAttribute("no_acc", "! Invalid Username or Password");
					return "index";
				}
				
			}
			else {
				m.addAttribute("no_acc", "! Invalid Username or Password");
				return "index";
			}
		}
	}
	
	@RequestMapping("signup")
	public String signup() {
		return "signup";
	}
	
	@RequestMapping("createAcc")
	public String createAcc(Model m,@RequestParam("uname")String uname,@RequestParam("pass")String pass,@RequestParam("emailId")String emailId,@RequestParam("cpass")String cpass) {
		if(uname.equals("") || pass.equals("") || emailId.equals("") || cpass.equals(""))
		{
			m.addAttribute("create_properly_warning", "Every Fields are Required!");
			return "signup";
		}
		else if(dao.existsById(uname))
		{
			m.addAttribute("create_properly_warning", "Username Unavailable!");
			return "signup";
		}
		else if(!(pass.equals(cpass)))
		{
			m.addAttribute("create_properly_warning", "Confirm Password is Different!");
			return "signup";
		}
		else 
		{
			Login create = new Login(uname,pass,emailId);
			dao.save(create);
			Profile createProfile = new Profile(uname,emailId);
			profiledao.save(createProfile);
			m.addAttribute("no_acc", "Account Created Successfully!");
			return "index";
		}
	}
	
	@RequestMapping(value={"logout"})
	public String logout(Model m) {
		return "index";
	}
	
//	@PostMapping("/post/search")
//	public String searchPost(Model m,@RequestParam("searchkey") String searchkey)
//	{
//		
//			List<Post> searchresults = postdao.search(searchkey);
//			//m.addAttribute("sr", searchresults);
//			for(Post p:searchresults) {
//				System.out.println(p);
//				m.addAttribute("sr", p.getAccount());
//				System.out.println(p.getAccount()+"   bad");
//				break;
//			}
//			return "profile";
//		
//	}
}