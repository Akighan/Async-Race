package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.controller.dto.response.Engine.GetEngineResponseDto

interface EngineService {
    fun startOrStopEngine(id:String, status:String):GetEngineResponseDto
}