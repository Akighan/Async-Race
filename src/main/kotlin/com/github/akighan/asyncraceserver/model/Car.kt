package com.github.akighan.asyncraceserver.model

import javax.persistence.*

@Entity
@Table
class Car() {

    constructor(
        id: Int,
        name: String,
        color: String
    ) : this() {
        this.id = id
        this.name = name
        this.color = color
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carSeq")
    @SequenceGenerator(name = "carSeq", initialValue = 1, allocationSize = 1, sequenceName = "Car_Seq")
    @Column(name = "id")
    var id: Int = 0
    var name: String = ""
    var color: String = ""

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id", referencedColumnName = "id")
    var engine: Engine? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Car

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int = id


    override fun toString(): String {
        return "Car(id=$id, name='$name', color='$color')"
    }
}