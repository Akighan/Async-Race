package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.controller.dto.request.car.PostCarRequestDto
import com.github.akighan.asyncraceserver.controller.dto.request.car.UpdateCarRequestDto
import com.github.akighan.asyncraceserver.controller.dto.response.car.PostCarResponseDto
import com.github.akighan.asyncraceserver.controller.dto.response.car.UpdateCarResponseDto
import com.github.akighan.asyncraceserver.model.Car

interface CarService {
    fun getNumberOfCars(): Long
    fun getAllCars(_page: String, _limit: String): List<Car>
    fun saveCar(postCarRequestDto: PostCarRequestDto): PostCarResponseDto
    fun updateCar(id: String, updateCarRequestDto: UpdateCarRequestDto): UpdateCarResponseDto
    fun getCar(id: String): Car
    fun deleteCar(id: String)
}