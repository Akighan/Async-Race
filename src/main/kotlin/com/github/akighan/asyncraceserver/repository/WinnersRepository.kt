package com.github.akighan.asyncraceserver.repository

import com.github.akighan.asyncraceserver.model.Winner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface WinnersRepository:JpaRepository <Winner, Int> {
    fun findAllByOrderByIdAsc():List<Winner>
    fun findAllByOrderByIdDesc():List<Winner>
    fun findAllByOrderByWinsAsc():List<Winner>
    fun findAllByOrderByWinsDesc():List<Winner>
    fun findAllByOrderByTimeAsc():List<Winner>
    fun findAllByOrderByTimeDesc():List<Winner>
    @Modifying
    @Transactional
    @Query(
        value = "UPDATE Winner w SET w.time = :time, w.wins = :wins where w.id = :id"
    )
    fun updateById(id: Int, time: Double, wins: Int): Int
}