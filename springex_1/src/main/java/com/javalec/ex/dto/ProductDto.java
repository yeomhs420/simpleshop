package com.javalec.ex.dto;

public class ProductDto {
	private String id;
	private String name;
	private int amount;
	private int price;
	private String image_url;
	
	public ProductDto() {
		
	}
	
	public ProductDto(String id, String name, int amount, int price, String image_url) {
		this.id=id;
		this.name=name;
		this.amount=amount;
		this.price=price;
		this.image_url=image_url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String img_url) {
		this.image_url = img_url;
	}
	
}
