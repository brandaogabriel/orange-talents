package com.orangetalents.desafio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_lottery")
public class Lottery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String lotteryNumber;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;

	public Lottery() {
	}

	public Lottery(Long id, String lotteryNumber) {
		this.id = id;
		this.lotteryNumber = lotteryNumber;
	}

	public void generateNumbers() {
		lotteryNumbers();
	}

	private void lotteryNumbers() {
		this.lotteryNumber = "";
		Integer max = 60;
		Integer min = 1;
		List<Integer> numbersSelected = new ArrayList<>();

		int size = 6;
		while(size > 0) {
			Integer sortedNumber = (int) Math.floor(Math.random() * (max - min) + 1);
			if(numbersSelected.contains(sortedNumber))
				continue;

			numbersSelected.add(sortedNumber);
			this.lotteryNumber += this.lotteryNumber.equals("") ? sortedNumber :
					" " + sortedNumber;
			size--;
		}
	}

	public String getLotteryNumber() {
		return lotteryNumber;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Long getId() {
		return id;
	}

	@PrePersist
	public void prePersistency() {
		this.createdAt = Instant.now();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Lottery lottery = (Lottery) o;
		return Objects.equals(id, lottery.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
