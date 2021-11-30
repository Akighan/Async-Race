package com.github.akighan.asyncraceserver.controller

import com.github.akighan.asyncraceserver.model.Winner
import com.github.akighan.asyncraceserver.service.WinnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/winners")
class WinnerController @Autowired constructor(val winnerService: WinnerService) {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException) =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(e: NoSuchElementException) =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(
        allowedHeaders = ["X-Total-Count"],
        exposedHeaders = ["X-Total-Count"]
    )
    fun getWinners(
        @RequestParam(name = "_page", defaultValue = "1") page: String,
        @RequestParam(name = "_limit", defaultValue = "7") limit: String,
        @RequestParam(name = "_sort", defaultValue = "id") sort: String,
        @RequestParam(name = "_order", defaultValue = "ASC") order: String
    ): ResponseEntity<List<Winner>> {
        val response = HttpHeaders()
        response.add(
            "X-Total-Count",
            winnerService.getNumberOfWinners().toString()
        )
        return ResponseEntity.ok()
            .headers(response)
            .body(winnerService.getWinners(page, limit, sort, order))
    }

    @GetMapping("/{id}")
    fun getWinner(@PathVariable id: String): Winner =
        winnerService.getWinner(id)

    @PostMapping
    fun addWinner(winner: Winner) = winnerService.saveWinner(winner)

    @DeleteMapping("/{id}")
    fun deleteWinner(@PathVariable id: String) {
        winnerService.deleteWinner(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateWinner(@PathVariable id: String, @RequestBody winner: Winner): Winner =
        winnerService.updateWinner(id, winner)
}