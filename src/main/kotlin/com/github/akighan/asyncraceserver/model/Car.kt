package com.github.akighan.asyncraceserver.model

import javax.persistence.*

@Entity
@Table
class Car(
    _id: Int = 0,
    _name: String = "",
    _color: String = "",
    _engine: Engine? = null
) {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carSeq")
    @SequenceGenerator(name = "carSeq", initialValue = 1, allocationSize = 1, sequenceName = "Car_Seq")
    @Column(name = "id")
    val id: Int = _id
    val name: String = _name
    val color: String = _color
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "engine_id", referencedColumnName = "id")
    var engine:Engine? =  _engine

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Car

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int = id
}