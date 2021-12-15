package com.github.akighan.asyncraceserver.model

import javax.persistence.*

@Entity
@Table
class Winner() {
    @Id
    @Column(name = "id")
    var id: Int = 0
    var wins: Int = 0
    var time: Double = 0.0

    constructor(
        id: Int,
        time: Double,
        wins: Int
    ) : this() {
        this.id = id
        this.time = time
        this.wins = wins
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Winner

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }


}