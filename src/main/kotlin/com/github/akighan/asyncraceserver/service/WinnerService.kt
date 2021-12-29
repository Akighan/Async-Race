package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.controller.dto.request.winner.PostWinnerRequestDto
import com.github.akighan.asyncraceserver.controller.dto.request.winner.UpdateWinnerRequestDto
import com.github.akighan.asyncraceserver.controller.dto.response.winner.PostWinnerResponseDto
import com.github.akighan.asyncraceserver.controller.dto.response.winner.UpdateWinnerResponseDto
import com.github.akighan.asyncraceserver.model.Winner

interface WinnerService {
    fun getWinners(page: String, limit: String, sort: String, order: String): List<Winner>
    fun getNumberOfWinners(): Long
    fun getWinner(id: String): Winner
    fun saveWinner(postWinnerRequestDto: PostWinnerRequestDto): PostWinnerResponseDto
    fun deleteWinner(id: String)
    fun updateWinner(id: String, updateWinnerRequestDto: UpdateWinnerRequestDto): UpdateWinnerResponseDto
}