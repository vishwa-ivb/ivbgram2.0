package com.ivb.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ivb.entity.Notification;
import com.ivb.entity.Profile;

public interface NotificationDAO extends JpaRepository<Notification, Long>{

	@Transactional 
	@Modifying 
	@Query("select n from Notification n where n.profileNoti = ?1") 
	List<Notification> getAllNotifications(Profile profile);
	
	@Transactional 
	@Modifying 
	@Query(value="update Notification set is_Read = 1 where profile= ?1",nativeQuery = true) 
	void readednoti(long pid);

}
