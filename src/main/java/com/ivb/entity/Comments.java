package com.ivb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Comments {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid", nullable = false, unique = true)
	private long commentId;
	
	@Column(name = "commentator", nullable = false)
	private String commentator;
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "comment_date", nullable = false)
	private Date commentDate;
	
	@ManyToOne(targetEntity = Post.class)
	@JoinColumn(name="post_id", nullable = false)
	private Post post;
	
//	@ManyToOne()
//	@JoinColumn( nullable = false)
//	private long postId;
	
	public Comments() {
		
	}

	public String getCommentator() {
		return commentator;
	}

	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", comment=" + comment + ", commentDate=" + commentDate + ", post="
				+ post + "]";
	}

}
