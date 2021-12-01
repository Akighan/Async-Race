package com.github.akighan.asyncraceserver.repository

import com.github.akighan.asyncraceserver.model.Engine
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EngineRepository : JpaRepository<Engine, Int> {
    fun findEngineByCarId(carId:Int): Optional<Engine>
}