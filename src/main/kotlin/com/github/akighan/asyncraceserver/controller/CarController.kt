package com.github.akighan.asyncraceserver.controller

import com.github.akighan.asyncraceserver.controller.dto.request.car.PostCarRequestDto
import com.github.akighan.asyncraceserver.controller.dto.request.car.UpdateCarRequestDto
import com.github.akighan.asyncraceserver.controller.dto.response.car.GetCarResponseDto
import com.github.akighan.asyncraceserver.controller.dto.response.car.PostCarResponseDto
import com.github.akighan.asyncraceserver.controller.dto.response.car.UpdateCarResponseDto
import com.github.akighan.asyncraceserver.service.CarService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@CrossOrigin
@RestController
@RequestMapping("/garage")
class CarController @Autowired constructor(val carService: CarService, val modelMapper: ModelMapper) {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException) =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException) =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)


    @GetMapping()
    @CrossOrigin(
        allowedHeaders = ["X-Total-Count"],
        exposedHeaders = ["X-Total-Count"]
    )
    fun getAllCars(
        @RequestParam(name = "_page", defaultValue = "1") _page: String,
        @RequestParam(name = "_limit", defaultValue = "7") _limit: String
    ): ResponseEntity<List<GetCarResponseDto>> {
        val response = HttpHeaders()
        response.set("X-Total-Count", carService.getNumberOfCars().toString())
        return ResponseEntity.ok().headers(response)
            .body(carService.getAllCars(_page, _limit).map {
                modelMapper.map(it, GetCarResponseDto::class.java)
            })
    }

    @GetMapping("/{id}")
    fun getCar(@PathVariable id: String): ResponseEntity<GetCarResponseDto> {
        return ResponseEntity.ok().body(modelMapper.map(carService.getCar(id), GetCarResponseDto::class.java))
    }

    @PostMapping()
    fun addCar(@RequestBody postCarRequestDto: PostCarRequestDto): ResponseEntity<PostCarResponseDto> {
        val responseCar: PostCarResponseDto = carService.saveCar(postCarRequestDto)
        return ResponseEntity.created(URI.create("/garage/" + responseCar.id))
            .body(carService.saveCar(postCarRequestDto))
    }

    @PutMapping("/{id}")
    fun updateCar(
        @PathVariable id: String,
        @RequestBody updateCarRequestDto: UpdateCarRequestDto
    ): ResponseEntity<UpdateCarResponseDto> {
        return ResponseEntity.accepted()
            .body(carService.updateCar(id, updateCarRequestDto))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCar(@PathVariable id: String) {
        carService.deleteCar(id)
    }
}