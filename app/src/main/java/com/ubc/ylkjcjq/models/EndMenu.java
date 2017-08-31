package com.ubc.ylkjcjq.models;

import java.io.Serializable;

/**
 * @author cjq
 *
 * @description
 * 
 */
public class EndMenu implements Serializable {

	private int id;
	private String imageUrl;
	private String name;

	public void setId(int id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getName() {
		return name;
	}

	public EndMenu(String imageUrl, String name) {
		this.imageUrl = imageUrl;
		this.name = name;
	}
}
