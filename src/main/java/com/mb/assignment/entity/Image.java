package com.mb.assignment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Image
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String imageOne;
	private String imageTwo;
	private String imageThree;
	private String imageFour;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImageOne() {
		return imageOne;
	}
	public void setImageOne(String imageOne) {
		this.imageOne = imageOne;
	}
	public String getImageTwo() {
		return imageTwo;
	}
	public void setImageTwo(String imageTwo) {
		this.imageTwo = imageTwo;
	}
	public String getImageThree() {
		return imageThree;
	}
	public void setImageThree(String imageThree) {
		this.imageThree = imageThree;
	}
	public String getImageFour() {
		return imageFour;
	}
	public void setImageFour(String imageFour) {
		this.imageFour = imageFour;
	}
	
	
	
	
	
	
}