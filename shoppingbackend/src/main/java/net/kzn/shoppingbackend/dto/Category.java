package net.kzn.shoppingbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
/*
 private fields;
 * */
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

private String name;

@Column(name="DESCRIPTION ")
private String descrption;

@Column(name="image_url")
private String imageURL;

@Column(name="is_active")
private boolean active=true;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescrption() {
	return descrption;
}
public void setDescrption(String descrption) {
	this.descrption = descrption;
}
public String getImageURL() {
	return imageURL;
}
public void setImageURL(String imageURL) {
	this.imageURL = imageURL;
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}
@Override
public String toString() {
	return "Category [id=" + id + ", name=" + name + ", descrption=" + descrption + ", imageURL=" + imageURL
			+ ", active=" + active + "]";
}

	
}
