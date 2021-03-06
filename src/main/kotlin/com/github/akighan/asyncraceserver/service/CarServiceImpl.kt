package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.controller.dto.request.car.PostCarRequestDto
import com.github.akighan.asyncraceserver.controller.dto.request.car.UpdateCarRequestDto
import com.github.akighan.asyncraceserver.controller.dto.response.car.PostCarResponseDto
import com.github.akighan.asyncraceserver.controller.dto.response.car.UpdateCarResponseDto
import com.github.akighan.asyncraceserver.model.Car
import com.github.akighan.asyncraceserver.model.Engine
import com.github.akighan.asyncraceserver.repository.CarRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class CarServiceImp @Autowired constructor(val carRepository: CarRepository, val modelMapper: ModelMapper) :
    CarService {
    override fun getNumberOfCars(): Long {
        return carRepository.count()
    }

    override fun getAllCars(_page: String, _limit: String): List<Car> {
        val cars: List<Car> = carRepository.getCarsByOrderByIdAsc()
        if (cars.isEmpty()) return cars
        val pageInt = _page.toIntOrNull()
            ?: throw IllegalArgumentException("fail to get cars with illegal argument page: $_page!")
        val limitInt = _limit.toIntOrNull()
            ?: throw IllegalArgumentException("fail to get cars with illegal argument limit: $_limit!")
        val currentPosition = (pageInt - 1) * limitInt
        return if (cars.size > currentPosition + limitInt - 1)
            cars.subList(currentPosition, (currentPosition + limitInt - 1))
        else cars.subList(currentPosition, cars.size)
    }

    override fun saveCar(postCarRequestDto: PostCarRequestDto): PostCarResponseDto {
        val requestCar: Car = modelMapper.map(postCarRequestDto, Car::class.java)
        if (requestCar.color.isEmpty()) throw IllegalArgumentException("Car color must not be empty")
        if (requestCar.name.isEmpty()) throw IllegalArgumentException("Car name must not be empty")
        requestCar.engine = Engine()
        val car: Car = carRepository.saveAndFlush(requestCar)
        return modelMapper.map(car, PostCarResponseDto::class.java)
    }

    override fun updateCar(id: String, updateCarRequestDto: UpdateCarRequestDto): UpdateCarResponseDto {
        val carId = id.toIntOrNull() ?: throw IllegalArgumentException("fail to get car with illegal argument $id!")
        if (!carRepository.existsById(carId)) throw NoSuchElementException("Car with id $carId does not exist!")
        if (updateCarRequestDto.color.isEmpty()) throw IllegalArgumentException("Car color must not be empty")
        if (updateCarRequestDto.name.isEmpty()) throw IllegalArgumentException("Car name must not be empty")
        val resultCar = Car(carId, updateCarRequestDto.name, updateCarRequestDto.color)
        resultCar.engine = Engine()
        val car: Car = carRepository.saveAndFlush(resultCar)
        return modelMapper.map(car, UpdateCarResponseDto::class.java)
    }

    override fun getCar(id: String): Car {
        val carId = id.toIntOrNull() ?: throw IllegalArgumentException("fail to get car with illegal argument $id!")
        return carRepository.findById(carId).orElseThrow { NoSuchElementException("Car with id $id does not exist!") }
    }

    override fun deleteCar(id: String) {
        val carId = id.toIntOrNull() ?: throw IllegalArgumentException("fail to get car with illegal argument $id!")
        if (!carRepository.existsById(carId)) throw NoSuchElementException("Car with id $carId does not exist!")
        carRepository.deleteById(carId)
    }
}