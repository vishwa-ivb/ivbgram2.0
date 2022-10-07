package com.ivb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import com.ivb.entity.Login;
import com.ivb.entity.Post;
import com.ivb.entity.Profile;
import com.ivb.repository.LoginDAO;
import com.ivb.service.PostService;
import com.ivb.service.ProfileService;

@Controller
public class PostController {

	@Value("${uploadDir}")
	private String uploadFolder;
	
	@Autowired
	PostService dao;
	@Autowired
	ProfileService profiledao;
	
	@PostMapping("/post/savePostDetails")
	public @ResponseBody ResponseEntity<?> createPost(@RequestParam("account") String account,
			 @RequestParam("content") String content, Model model, HttpServletRequest request
			,final @RequestParam("image") MultipartFile file) {
		try {
			//String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
			String[] accounts = account.split(",");
			String[] contents = content.split(",");
			Date createDate = new Date();
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			Post post = new Post();
			post.setAccount(accounts[0]);
			post.setImage(imageData);
			post.setContent(contents[0]);
			post.setCreateDate(createDate);
			dao.savePost(post);
			
			List <Profile> profiles = profiledao.findProfile(account);
			for(Profile p : profiles)
			{
				List<Post> arrList = p.getPosts();
				arrList.add(post);
				profiledao.addPost(arrList,p.getPid());
				
				Long postCount = (long) p.getPosts().size();
				profiledao.updatePostCount(postCount, p.getPid());
				
				break;
			}
			return new ResponseEntity<>("Post Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value={"post","image/display/{id}"})
	@ResponseBody
	
	public void showImage(Model m,HttpServletResponse response,@PathVariable("id") String sId,Post posts) throws IOException
	{
		long id = Long.parseLong(sId);
		posts = dao.getPostById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		byte[] image = posts.getImage();
		response.getOutputStream().write(image);
		response.getOutputStream().close();
	}
	
	@PostMapping("/post/delete")
	public @ResponseBody ResponseEntity<?> deletePost(@RequestParam("deleteId") String deleteId)
	{
		try {
			long id = Long.parseLong(deleteId);
			dao.deletePost(id);
			return new ResponseEntity<>("Post Deleted Successfully", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/post/update")
	public @ResponseBody ResponseEntity<?> updatePost(@RequestParam("updateId") String updateId,
			@RequestParam("updateContent") String updateContent)
	{
		try {
			long id = Long.parseLong(updateId);
			dao.updatePost(updateContent, id);
			return new ResponseEntity<>("Post Updated Successfully", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
//	@PostMapping("/post/search")
//	public @ResponseBody ResponseEntity<?> searchPost(Model m,@RequestParam("searchkey") String searchkey)
//	{
//		try {
//			List<Post> searchresults = dao.search(searchkey);
//			m.addAttribute("sr", searchresults);
//			return new ResponseEntity<>("Post Search Successfull", HttpStatus.OK);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}
	
//	@RequestMapping(value="/post/search")
//    public ModelAndView user(@RequestParam("searchkey") String searchkey){
//        ModelAndView mav = new ModelAndView("searchPostbtn") ;
//        List<Post> searchresults = dao.search(searchkey);
//        return mav;
//    }
	
	@PostMapping("/search")
	public String searchPost(Model m,@RequestParam("searchkey") String searchkey,@RequestParam("searchinguser") String searchinguser)
	{
		
			List<Post> searchresults = dao.search(searchkey);
			Collections.sort(searchresults,Comparator.comparingLong(Post::getId));
			Collections.reverse(searchresults);
			int totalnum = searchresults.size();
			if(searchresults.isEmpty())
			{
				m.addAttribute("noresults", "<p id=\"noresults\">No results found !</p>");
			}
			System.out.println(totalnum);
			m.addAttribute("totalnum", totalnum);
			m.addAttribute("searchkey", searchkey);
			m.addAttribute("searchinguser", searchinguser);
			m.addAttribute("searchresult", searchresults);
			return "search";
	}
	
	@RequestMapping("/userhome")
	public String signin(Model m, @RequestParam("searchinguser")String searchinguser) {
		
				m.addAttribute("user", searchinguser);
				
					List<Post> posts = dao.getAllActivePosts();
					Collections.sort(posts,Comparator.comparingLong(Post::getId));
					Collections.reverse(posts);
					m.addAttribute("images", posts);
					
					List <Profile> profiles = profiledao.findProfile(searchinguser);
					for(Profile p : profiles)
					{
						Profile pro = new Profile();
						pro.setPid(p.getPid());
						pro.setAbout(p.getAbout());
						pro.setProfilepic(p.getProfilepic());
						pro.setAccount(p.getAccount());
						pro.setEmail(p.getEmail());
						
						Long postCount = (long) dao.getByUser(searchinguser).size();
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
}
