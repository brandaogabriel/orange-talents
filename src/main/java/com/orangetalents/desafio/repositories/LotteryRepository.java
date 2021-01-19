package com.orangetalents.desafio.repositories;

import com.orangetalents.desafio.entities.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, String> {
}
