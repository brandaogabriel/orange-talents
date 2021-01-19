package com.orangetalents.desafio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	@ManyToMany
	@JoinTable(name = "tb_user_lottery",
		joinColumns = @JoinColumn(name = "user_email"),
		inverseJoinColumns = @JoinColumn(name = "lottery_number")
	)
	private Set<Lottery> lotteries = new HashSet<>();

	public User() {
	}

	public User(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Lottery> getLotteries() {
		return lotteries;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return email.equals(user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
}
