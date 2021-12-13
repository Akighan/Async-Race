package com.github.akighan.asyncraceserver.controller.dto.response.winner

class UpdateWinnerResponseDto() {
    var id:Int = 0
    var wins:Int = 0
    var time:Double = 0.0

    constructor(
        id:Int,
        wins:Int,
        time:Double
    ):this() {
        this.id = id
        this.wins = wins
        this.time = time
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UpdateWinnerResponseDto

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}