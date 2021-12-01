package com.github.akighan.asyncraceserver.controller

import com.github.akighan.asyncraceserver.model.Engine
import com.github.akighan.asyncraceserver.service.EngineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/engine")
class EngineController @Autowired constructor(val engineService: EngineService) {

    @GetMapping
    fun startOrStopEngine(
        @RequestParam id:String,
        @RequestParam status:String
    ): Engine {
        return engineService.startOrStopEngine(id, status)
    }
}