package com.example.mywikiloc.model;

public class Image {
	
	private Integer imageId;
	private String name;
	private float lat;
	private float lon;
	private String imgDescr;
	
	
	
	
	public Image(Integer imageId, String name, float lat, float lon, String imgDescr) {
		super();
		this.imageId = imageId;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.imgDescr = imgDescr;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public String getImgDescr() {
		return imgDescr;
	}

	public void setImgDescr(String imgDescr) {
		this.imgDescr = imgDescr;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", name=" + name + ", lat=" + lat + ", lon=" + lon + ", imgDescr="
				+ imgDescr + "]";
	}
	
	
	
	

}
