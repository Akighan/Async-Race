package com.github.akighan.asyncraceserver.service

import com.github.akighan.asyncraceserver.model.Winner
import com.github.akighan.asyncraceserver.repository.WinnersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class WinnerServiceImpl @Autowired constructor(val winnersRepository: WinnersRepository) : WinnerService {
    override fun getWinners(page: String, limit: String, sort: String, order: String): List<Winner> {
        val winners: List<Winner>
        when (sort) {
            "id" -> {
                winners = if (order == "ASC")
                    winnersRepository.findAllByOrderByIdAsc()
                else
                    winnersRepository.findAllByOrderByIdDesc()
            }
            "wins" -> {
                winners = if (order == "ASC")
                    winnersRepository.findAllByOrderByWinsAsc()
                else
                    winnersRepository.findAllByOrderByWinsDesc()
            }
            "time" -> {
                winners = if (order == "ASC")
                    winnersRepository.findAllByOrderByTimeAsc()
                else
                    winnersRepository.findAllByOrderByTimeDesc()
            }
            else -> winners = winnersRepository.findAllByOrderByIdAsc()
        }
        if (winners.isEmpty()) return winners
        val pageInt = page.toIntOrNull()
            ?: throw IllegalArgumentException("fail to get winners with illegal argument page: $page!")
        val limitInt = limit.toIntOrNull()
            ?: throw IllegalArgumentException("fail to get winners with illegal argument limit: $limit!")
        val currentPosition = (pageInt - 1) * limitInt
        return if (winners.size >= currentPosition + limitInt - 1)
            winners.subList(currentPosition, (currentPosition + limitInt))
        else winners.subList(currentPosition, winners.size)
    }

    override fun getNumberOfWinners(): Long =
        winnersRepository.count()

    override fun getWinner(id: String): Winner {
        val winnerId =
            id.toIntOrNull() ?: throw IllegalArgumentException("fail to get winner with illegal argument $id!")
        return winnersRepository.findById(winnerId)
            .orElseThrow { NoSuchElementException("Car with id $id does not exist!") }
    }

    override fun saveWinner(winner: Winner): Winner {
        if (winnersRepository.existsById(winner.id)) throw IllegalArgumentException("insert failed, duplicated id ${winner.id}")
        return winnersRepository.saveAndFlush(winner)
    }

    override fun deleteWinner(id: String) {
        val winnerId =
            id.toIntOrNull() ?: throw IllegalArgumentException("fail to get winner with illegal argument $id!")
        winnersRepository.deleteById(winnerId)
    }

    override fun updateWinner(id: String, winner: Winner): Winner {
        val winnerId = id.toIntOrNull() ?: throw IllegalArgumentException ("fail to get winner with illegal argument $id!")
        if (!winnersRepository.existsById(winnerId)) throw NoSuchElementException("Winner with id ${winner.id} does not exist!")
        winnersRepository.updateById(winnerId, winner.time, winner.wins)
        return winnersRepository.getById(winnerId)
    }
}