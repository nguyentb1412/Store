package com.caphephin.voucher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "username")
	private String username;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "birthday")
	private String birthday;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "email")
	private String email;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	public User() {

	}

	public User( String userName, String fullName, String email, String birthday, String avatar) {
		this.username = userName;
		this.birthday = birthday;
		this.email = email;
		this.fullName = fullName;
		this.avatar = avatar;
	}

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

}
