package com.github.akighan.asyncraceserver.controller.dto.request.winner

class UpdateWinnerRequestDto() {
    var wins:Int = 0
    var time:Double = 0.0

    constructor(
        wins:Int,
        time:Double
    ):this() {
        this.wins = wins
        this.time = time
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UpdateWinnerRequestDto

        if (wins != other.wins) return false
        if (time != other.time) return false

        return true
    }

    override fun hashCode(): Int {
        var result = wins
        result = 31 * result + time.hashCode()
        return result
    }
}