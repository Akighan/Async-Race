package com.github.akighan.asyncraceserver.repository

import com.github.akighan.asyncraceserver.model.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Int> {
    fun getCarsByOrderByIdAsc():List<Car>
}
