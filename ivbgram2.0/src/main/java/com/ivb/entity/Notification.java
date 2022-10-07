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
public class Notification {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notiId", nullable = false, unique = true)
	private long notiId;
	@Column(name = "notimsg", nullable = false, unique = true)
	private String notimsg;
	@Column(name = "is_read", nullable = false)
	private int is_read;
	@Column(name = "connect_id", nullable = false)
	private long connectId;
	@ManyToOne(targetEntity = Profile.class)
	@JoinColumn(name="profile", nullable = false)
	private Profile profileNoti;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "noti_date", nullable = false)
	private Date notiDate;
	
	public Notification() {}

	public Notification(String notimsg, int is_read, long connectId, Profile profileNoti, Date notiDate) {
		super();
		this.notimsg = notimsg;
		this.is_read = is_read;
		this.connectId = connectId;
		this.profileNoti = profileNoti;
		this.notiDate = notiDate;
	}

	public long getNotiId() {
		return notiId;
	}

	public void setNotiId(long notiId) {
		this.notiId = notiId;
	}

	public String getNotimsg() {
		return notimsg;
	}

	public void setNotimsg(String notimsg) {
		this.notimsg = notimsg;
	}

	public int getIs_read() {
		return is_read;
	}

	public void setIs_read(int is_read) {
		this.is_read = is_read;
	}

	public long getConnectId() {
		return connectId;
	}

	public void setConnectId(long connectId) {
		this.connectId = connectId;
	}

	public Profile getProfileNoti() {
		return profileNoti;
	}

	public void setProfileNoti(Profile profileNoti) {
		this.profileNoti = profileNoti;
	}

	public Date getNotiDate() {
		return notiDate;
	}

	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}

	@Override
	public String toString() {
		return "Notification [notiId=" + notiId + ", notimsg=" + notimsg + ", is_read=" + is_read + ", connectId="
				+ connectId + ", profileNoti=" + profileNoti + ", notiDate=" + notiDate + "]";
	}
	
}
