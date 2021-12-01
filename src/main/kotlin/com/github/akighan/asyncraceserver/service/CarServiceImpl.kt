package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.model.Car
import com.github.akighan.asyncraceserver.model.Engine
import com.github.akighan.asyncraceserver.repository.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class CarServiceImp @Autowired constructor(val carRepository: CarRepository) : CarService {
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

    override fun saveCar(car: Car): Car {
        if (car.color.isEmpty()) throw IllegalArgumentException("Car color must not be empty")
        if (car.name.isEmpty()) throw IllegalArgumentException("Car name must not be empty")
        car.engine = Engine()
        return carRepository.saveAndFlush(car)
    }

    override fun updateCar(id: String, car: Car): Car {
        val carId = id.toIntOrNull() ?: throw IllegalArgumentException("fail to get car with illegal argument $id!")
        if (!carRepository.existsById(carId)) throw NoSuchElementException("Car with id ${car.id} does not exist!")
        if (car.color.isEmpty()) throw IllegalArgumentException("Car color must not be empty")
        if (car.name.isEmpty()) throw IllegalArgumentException("Car name must not be empty")
        carRepository.saveAndFlush(Car(carId, car.color, car.name, Engine()))
        return carRepository.getById(carId)
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

    override fun switchToDriveMode(): Engine {
        TODO("Not yet implemented")
    }
}