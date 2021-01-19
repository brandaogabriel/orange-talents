package com.orangetalents.desafio.dtos;

import com.orangetalents.desafio.entities.Lottery;
import com.orangetalents.desafio.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;

	private List<LotteryDTO> lotteries = new ArrayList<>();

	public UserDTO() {
	}

	public UserDTO(String email) {
		this.email = email;
	}

	public UserDTO(User entity, Set<Lottery> lotteries) {
		this.email = entity.getEmail();
		lotteries.forEach(lottery -> this.lotteries.add(new LotteryDTO(lottery)));
	}

	public UserDTO(User entity, LotteryDTO lotteryDTO) {
		this(entity.getEmail());
		this.lotteries.add(lotteryDTO);
	}

	public String getEmail() {
		return email;
	}

	public List<LotteryDTO> getLoteries() {
		return lotteries;
	}
}
