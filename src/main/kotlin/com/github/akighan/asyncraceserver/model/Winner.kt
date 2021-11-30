package com.github.akighan.asyncraceserver.model

import javax.persistence.*

@Entity
@Table
class Winner (
    _id:Int = 0,
    _wins:Int = 0,
    _time:Double = 0.0
        ){
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "winSeq")
    @SequenceGenerator(name = "winSeq", initialValue = 1, allocationSize = 1, sequenceName = "Win_Seq")
    @Column(name = "id")
    val id: Int = _id
    val wins:Int = _wins
    val time:Double = _time

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