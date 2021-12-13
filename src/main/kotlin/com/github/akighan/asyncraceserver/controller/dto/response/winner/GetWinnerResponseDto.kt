package com.github.akighan.asyncraceserver.controller.dto.response.winner

class GetWinnerResponseDto() {
    var id:Int = 0
    var wins:Int = 0
    var time:Double = 0.0

    constructor(
        _id:Int,
        _wins:Int,
        _time:Double
    ) : this() {
        id = _id
        wins = _wins
        time = _time
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetWinnerResponseDto

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}