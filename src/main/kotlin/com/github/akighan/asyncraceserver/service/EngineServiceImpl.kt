package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.model.Engine
import com.github.akighan.asyncraceserver.repository.EngineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.NoSuchElementException

@Service
class EngineServiceImpl @Autowired constructor(val engineRepository: EngineRepository) : EngineService {
    override fun startOrStopEngine(id: String, status: String): Engine {
        val carId = id.toIntOrNull() ?: throw IllegalArgumentException("fail to get car with illegal argument $id!")
        val engine: Engine = engineRepository.findEngineByCarId(carId)
            .orElseThrow { throw NoSuchElementException("can't find car with id $carId") }

        return if (Engine.Status.valueOf(status.uppercase(Locale.getDefault())) == Engine.Status.STARTED) {
            engine.status = Engine.Status.STARTED
            engine.velocity = (10..100).random()
            engineRepository.saveAndFlush(engine)
        } else if (Engine.Status.valueOf(status) == Engine.Status.STOPPED) {
            engine.status = Engine.Status.STOPPED
            engine.velocity = 0
            engineRepository.saveAndFlush(engine)
        } else throw IllegalArgumentException("fail to get status $status")
    }
}