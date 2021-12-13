package com.github.akighan.asyncraceserver.configuration

import com.github.akighan.asyncraceserver.model.Car
import com.github.akighan.asyncraceserver.model.Engine
import com.github.akighan.asyncraceserver.model.Winner
import com.github.akighan.asyncraceserver.repository.CarRepository
import com.github.akighan.asyncraceserver.repository.WinnersRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration @Autowired constructor(val carRepository: CarRepository, val winnerRepository: WinnersRepository) {

    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.isAmbiguityIgnored = true
        return modelMapper
    }

    @Bean
    fun saveCars() {
        carRepository.saveAll(listOf(Car(id = 1, name = "Lamborgini", color = "Red", engine = Engine())))
        winnerRepository.saveAll(listOf(Winner(_wins = 1, _time = 2.02)))
    }
}