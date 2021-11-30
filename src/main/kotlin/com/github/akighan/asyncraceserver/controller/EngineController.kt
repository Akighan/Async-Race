package com.github.akighan.asyncraceserver.controller

import com.github.akighan.asyncraceserver.model.Engine
import com.github.akighan.asyncraceserver.service.CarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/engine")
class EngineController @Autowired constructor(val carService: CarService) {

    @GetMapping
    fun startOrStopEngine(
        @RequestParam id:String,
        @RequestParam status:String
    ): Engine {
        return carService.startOrStopEngine(id, status)
    }
}