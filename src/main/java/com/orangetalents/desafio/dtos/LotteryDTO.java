package com.orangetalents.desafio.dtos;

import com.orangetalents.desafio.entities.Lottery;

import java.io.Serializable;
import java.time.Instant;

public class LotteryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String lotteryNumbers;
	private Instant createdAt;

	public LotteryDTO() {
	}

	public LotteryDTO(String lotteryNumbers, Instant createdAt) {
		this.lotteryNumbers = lotteryNumbers;
		this.createdAt = createdAt;
	}

	public LotteryDTO(Lottery entity) {
		this.lotteryNumbers = entity.getLotteryNumber();
		this.createdAt = entity.getCreatedAt();
	}

	public String getLotteryNumbers() {
		return lotteryNumbers;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

}
