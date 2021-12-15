package com.github.akighan.asyncraceserver.controller.dto.response.Engine

class GetEngineStartOrStopResponseDto():GetEngineResponseDto {
    var velocity:Int = 0
    var distance:Int = 0

    constructor(velocity:Int, distance:Int):this(){
        this.velocity = velocity
        this.distance = distance
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetEngineStartOrStopResponseDto

        if (velocity != other.velocity) return false
        if (distance != other.distance) return false

        return true
    }

    override fun hashCode(): Int {
        var result = velocity
        result = 31 * result + distance
        return result
    }

    override fun toString(): String {
        return "GetEngineResponseDto(velocity=$velocity, distance=$distance)"
    }
}