package com.ivb.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="profiles")
public class Profile {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pid", nullable = false, unique = true)
	private Long pid;
	
	@Column(name = "account", nullable = false, unique = true)
	private String account;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Lob
    @Column(name = "profilepic", length = Integer.MAX_VALUE, nullable = true)
    private byte[] profilepic;
	
	@Column(name = "about", nullable = true)
	private String about;
	
	@Column(name = "postscount", nullable = true)
	private Long postscount;
	
	@OneToMany()
	@Column(name = "posts", nullable = true)
	private List<Post> posts = new ArrayList<Post>();
	
	public Profile()
	{
		
	}
	
	public Profile(String account, String email) {
		super();
		this.account = account;
		this.email = email;
		this.postscount=0l;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(byte[] profilepic) {
		this.profilepic = profilepic;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Long getPostscount() {
		return postscount;
	}

	public void setPostscount(Long postscount) {
		this.postscount = postscount;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Profile [pid=" + pid + ", account=" + account + ", email=" + email + ", profilepic="
				+ Arrays.toString(profilepic) + ", about=" + about + ", postscount=" + postscount + ", posts=" + posts
				+ "]";
	}

}
