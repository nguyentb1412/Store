package com.caphephin.voucher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "sticky")
public class Sticky {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "stickys")
	@JsonIgnore
	private String stickys;


	@Column(name = "phone")
	private String phone;
	public Sticky() {

	}

	@JsonProperty
	public String stickJson() {
		return stickys.replace("\"","").replace("[","").replace("]","");
	}

	public String getStickys() {
		return stickys;
	}

	public void setStickys(String stickys) {
		this.stickys = stickys;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}

