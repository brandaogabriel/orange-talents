package com.orangetalents.desafio.services;

import com.orangetalents.desafio.dtos.LotteryDTO;
import com.orangetalents.desafio.dtos.UserDTO;
import com.orangetalents.desafio.entities.Lottery;
import com.orangetalents.desafio.entities.User;
import com.orangetalents.desafio.repositories.LotteryRepository;
import com.orangetalents.desafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private LotteryRepository lotteryRepository;

	@Transactional(readOnly = true)
	public UserDTO findByEmail(String email) {
		Optional<User> obj = repository.findById(email);
		User user = obj.orElseThrow(() -> new RuntimeException("User not found"));
		return new UserDTO(user, user.getLotteries());
	}

	@Transactional
	public UserDTO insert(UserDTO userDTO) {
		Lottery lottery = new Lottery();
		lottery.generateNumbers();
		lotteryRepository.save(lottery);

		User user;
		if (userExists(userDTO.getEmail())) {
			user = repository.getOne(userDTO.getEmail());
		}
		else {
			user = new User();
			user.setEmail(userDTO.getEmail());
		}

		user.getLotteries().add(lottery);

		user = repository.save(user);
		LotteryDTO lotteryDTO = new LotteryDTO(lottery);
		return new UserDTO(user, lotteryDTO);
	}

	private boolean userExists(String email) {
		Optional<User> obj = repository.findById(email);
		return obj.isPresent();
	}

}
