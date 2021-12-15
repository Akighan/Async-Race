package com.github.akighan.asyncraceserver.controller.dto.response.Engine

class GetEngineDriveModeResponseDto() : GetEngineResponseDto {
    var success: Boolean = false

    constructor(success: Boolean) : this() {
        this.success = success
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetEngineDriveModeResponseDto

        if (success != other.success) return false

        return true
    }

    override fun hashCode(): Int {
        return success.hashCode()
    }

    override fun toString(): String {
        return "GetEngineDriveModeResponseDto(success=$success)"
    }
}