package com.github.akighan.asyncraceserver.model

import javax.persistence.*

@Entity
@Table(name = "engine")
class Engine {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name="id")
    var id:Int = 0
    val distance:Int = 50000
    var velocity:Int = 0
    var status:Enum<Status> = Status.STOPPED

    @OneToOne(mappedBy = "engine")
    val car:Car? = null

    enum class Status {
        STARTED, STOPPED, DRIVE
    }
}