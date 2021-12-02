package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.model.Engine

interface EngineService {
    fun startOrStopEngine(id:String, status:String):Engine
}