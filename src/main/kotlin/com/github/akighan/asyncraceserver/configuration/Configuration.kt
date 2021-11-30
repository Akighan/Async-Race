package com.github.akighan.asyncraceserver.configuration

import com.github.akighan.asyncraceserver.model.Car
import com.github.akighan.asyncraceserver.model.Winner
import com.github.akighan.asyncraceserver.repository.CarRepository
import com.github.akighan.asyncraceserver.repository.WinnersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration @Autowired constructor(val carRepository: CarRepository, val winnerRepository: WinnersRepository) {

    @Bean
    fun saveCars() {
        carRepository.saveAll(listOf(Car(_name = "Lamborgini", _color = "Red")))
        winnerRepository.saveAll(listOf(Winner (_wins = 1, _time = 2.02)))
    }
}