package com.github.akighan.asyncraceserver.controller

import com.github.akighan.asyncraceserver.controller.dto.request.winner.PostWinnerRequestDto
import com.github.akighan.asyncraceserver.controller.dto.request.winner.UpdateWinnerRequestDto
import com.github.akighan.asyncraceserver.controller.dto.response.winner.UpdateWinnerResponseDto
import com.github.akighan.asyncraceserver.controller.dto.response.winner.GetWinnerResponseDto
import com.github.akighan.asyncraceserver.controller.dto.response.winner.PostWinnerResponseDto
import com.github.akighan.asyncraceserver.model.Winner
import com.github.akighan.asyncraceserver.service.WinnerService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@CrossOrigin
@RestController
@RequestMapping("/winners")
class WinnerController @Autowired constructor(
    val winnerService: WinnerService,
    val modelMapper: ModelMapper
) {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException) =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(e: NoSuchElementException) =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    @CrossOrigin(
        allowedHeaders = ["X-Total-Count"],
        exposedHeaders = ["X-Total-Count"]
    )
    fun getWinners(
        @RequestParam(name = "_page", defaultValue = "1") page: String,
        @RequestParam(name = "_limit", defaultValue = "7") limit: String,
        @RequestParam(name = "_sort", defaultValue = "id") sort: String,
        @RequestParam(name = "_order", defaultValue = "ASC") order: String
    ): ResponseEntity<List<GetWinnerResponseDto>> {
        val response = HttpHeaders()
        response.set(
            "X-Total-Count",
            winnerService.getNumberOfWinners().toString()
        )
        return ResponseEntity.ok()
            .headers(response)
            .body(winnerService.getWinners(page, limit, sort, order).map {
                modelMapper.map(it, GetWinnerResponseDto::class.java)
            })
    }

    @GetMapping("/{id}")
    fun getWinner(@PathVariable id: String): ResponseEntity<GetWinnerResponseDto> =
        ResponseEntity.ok().body(modelMapper.map(winnerService.getWinner(id), GetWinnerResponseDto::class.java))

    @PostMapping
    fun addWinner(@RequestBody postWinnerRequestDto: PostWinnerRequestDto): ResponseEntity<PostWinnerResponseDto> {
        val requestWinner: Winner = modelMapper.map(postWinnerRequestDto, Winner::class.java)
        val responseWinner: Winner = winnerService.saveWinner(requestWinner)
        return ResponseEntity.created(URI.create("/winners/" + responseWinner.id))
            .body(modelMapper.map(responseWinner, PostWinnerResponseDto::class.java))
    }

    @DeleteMapping("/{id}")
    fun deleteWinner(@PathVariable id: String) {
        winnerService.deleteWinner(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateWinner(
        @PathVariable id: String,
        @RequestBody updateWinnerRequest: UpdateWinnerRequestDto
    ): ResponseEntity<UpdateWinnerResponseDto> {
        val requestWinner = modelMapper.map(updateWinnerRequest, Winner::class.java)
        val responseWinner = winnerService.updateWinner(id, requestWinner)
        return ResponseEntity.ok().body(modelMapper.map(responseWinner, UpdateWinnerResponseDto::class.java))
    }
}