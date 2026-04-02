package bean;

import java.time.LocalDateTime;

public class Board2 implements java.io.Serializable {
	private int id;
	private LocalDateTime myDate;
	private String loginId;
	private String contents;
	private int likes;
	private int disLikes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getMyDate() {
		return myDate;
	}
	public void setMyDate(LocalDateTime myDate) {
		this.myDate = myDate;
	}
	public String getIoginId() {
		return loginId;
	}
	public void setIoginId(String ioginId) {
		this.loginId = ioginId;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDisLikes() {
		return disLikes;
	}
	public void setDisLikes(int disLikes) {
		this.disLikes = disLikes;
	}
	

}
