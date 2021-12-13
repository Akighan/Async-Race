package com.github.akighan.asyncraceserver.controller.dto.response.car

class GetCarResponseDto() {
    var name: String = ""
    var color: String = ""
    var id: Int = 0

    constructor(
        _name: String,
        _color: String,
        _id: Int
    ) : this() {
        name = _name
        color = _color
        id = _id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetCarResponseDto

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}