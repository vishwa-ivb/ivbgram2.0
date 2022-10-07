package com.ivb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivb.entity.Comments;
import com.ivb.entity.Notification;
import com.ivb.entity.Post;
import com.ivb.entity.Profile;
import com.ivb.repository.CommentsDAO;
import com.ivb.repository.NotificationDAO;

@Service
public class NotificationService {

	@Autowired
	private NotificationDAO ndao;
	
	public void saveNotification(Notification noti) {
		ndao.save(noti);	
	}

	public List<Notification> getAllNotifications(Profile profile) {
		return ndao.getAllNotifications(profile);
	}
	
	public void readednoti(long pid) {
		ndao.readednoti(pid);	
	}
}
