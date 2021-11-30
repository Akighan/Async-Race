package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.model.Winner

interface WinnerService {
    fun getWinners(page: String, limit: String, sort: String, order: String): List<Winner>
    fun getNumberOfWinners(): Long
    fun getWinner(id: String): Winner
    fun saveWinner(winner: Winner): Winner
    fun deleteWinner(id: String)
    fun updateWinner(id: String, winner: Winner): Winner
}