package com.caphephin.voucher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.time.DateUtils;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "voucher")
public class Voucher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "phone")
	private String phone;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "used_date")
	private String usedDate;

	@Column(name = "status")
	private String status;

	public Voucher() {

	}

	@JsonProperty
	public String expiredDate() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(createdDate);
		Date newDate = DateUtils.addMonths(date, 1);
		return dateFormat.format(newDate);
	}

	public long getId() {
		return id;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(String usedDate) {
		this.usedDate = usedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}

