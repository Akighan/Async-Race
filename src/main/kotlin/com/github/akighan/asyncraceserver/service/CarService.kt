package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.model.Car
import com.github.akighan.asyncraceserver.model.Engine

interface CarService {
    fun getNumberOfCars():Long
    fun getAllCars(_page:String, _limit:String): List<Car>
    fun saveCar(car: Car): Car
    fun updateCar(id:String, car: Car): Car
    fun getCar(id:String):Car
    fun deleteCar(id: String)
    fun startOrStopEngine(id:String, status:String):Engine
    fun switchToDriveMode():Engine
}