package com.github.akighan.asyncraceserver.model

import javax.persistence.*

@Entity
@Table(name = "engine")
class Engine() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Int = 0
    var distance: Int = 50000
    var velocity: Int = 0
    var status: Enum<Status> = Status.STOPPED
    var success:Boolean = false

    @OneToOne(mappedBy = "engine")
    var car: Car? = null


    constructor(
        id: Int,
        distance: Int,
        velocity: Int,
        status: Status,
        success:Boolean
    ) : this() {
        this.id = id
        this.distance = distance
        this.velocity = velocity
        this.status = status
        this.success = success
    }

    enum class Status {
        STARTED, STOPPED, DRIVE
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Engine

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "Engine(id=$id, distance=$distance, velocity=$velocity, status=$status, success=$success)"
    }

}