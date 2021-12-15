package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.controller.dto.response.Engine.GetEngineDriveModeResponseDto
import com.github.akighan.asyncraceserver.controller.dto.response.Engine.GetEngineResponseDto
import com.github.akighan.asyncraceserver.controller.dto.response.Engine.GetEngineStartOrStopResponseDto
import com.github.akighan.asyncraceserver.model.Engine
import com.github.akighan.asyncraceserver.model.Engine.Status.STARTED
import com.github.akighan.asyncraceserver.model.Engine.Status.STOPPED
import com.github.akighan.asyncraceserver.model.Engine.Status.DRIVE
import com.github.akighan.asyncraceserver.repository.EngineRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.IllegalArgumentException
import kotlin.NoSuchElementException

@Service
class EngineServiceImpl @Autowired constructor(val engineRepository: EngineRepository, val modelMapper: ModelMapper) :
    EngineService {

    override fun startOrStopEngine(id: String, status: String): GetEngineResponseDto {
        val carId = id.toIntOrNull() ?: throw IllegalArgumentException("fail to get car with illegal argument $id!")
        val engine: Engine = engineRepository.findEngineByCarId(carId)
            .orElseThrow { throw NoSuchElementException("can't find car with id $carId") }

        val engineStatus: Engine.Status

        try {
            engineStatus = Engine.Status.valueOf(status.uppercase(Locale.getDefault()))
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("fail to get status $status")
        }

        return when (engineStatus) {
            STARTED -> {
                engine.status = STARTED
                engine.velocity = (15..30).random()
                modelMapper.map(engineRepository.saveAndFlush(engine), GetEngineStartOrStopResponseDto::class.java)
            }
            STOPPED -> {
                engine.status = STOPPED
                engine.velocity = 0
                modelMapper.map(engineRepository.saveAndFlush(engine), GetEngineStartOrStopResponseDto::class.java)
            }
            DRIVE -> {
                val raceTime = engine.distance / engine.velocity
                if ((0..40).random() % 3 == 0) {
                    Thread.sleep((0..raceTime).random().toLong())
                    engine.success = false
                    throw InterruptedException("car has been stopped")
                } else {
                    Thread.sleep(raceTime.toLong())
                }
                engine.success = true
                return modelMapper.map(engineRepository.saveAndFlush(engine), GetEngineDriveModeResponseDto::class.java)
            }
        }
    }
}