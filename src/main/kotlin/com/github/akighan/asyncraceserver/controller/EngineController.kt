package com.github.akighan.asyncraceserver.controller

import com.github.akighan.asyncraceserver.controller.dto.response.Engine.GetEngineResponseDto
import com.github.akighan.asyncraceserver.service.EngineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/engine")
class EngineController @Autowired constructor(val engineService: EngineService) {

    @ExceptionHandler(InterruptedException::class)
    fun handleCarStoppedException (e:InterruptedException) =
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)

    @GetMapping
    fun startOrStopEngine(
        @RequestParam id:String,
        @RequestParam status:String
    ): ResponseEntity<GetEngineResponseDto> {
        return ResponseEntity.ok().body(engineService.startOrStopEngine(id, status))
    }
}