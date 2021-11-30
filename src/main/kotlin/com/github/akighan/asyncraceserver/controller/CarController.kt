package com.github.akighan.asyncraceserver.controller

import com.github.akighan.asyncraceserver.model.Car
import com.github.akighan.asyncraceserver.service.CarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@CrossOrigin
@RestController
@RequestMapping("/garage")
class CarController @Autowired constructor(val carService: CarService) {

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
    ): ResponseEntity<List<Car>> {
        val response = HttpHeaders()
        response.set("X-Total-Count", carService.getNumberOfCars().toString())
        return ResponseEntity.ok().headers(response)
            .body(carService.getAllCars(_page, _limit))
    }

    @GetMapping("/{id}")
    fun getCar(@PathVariable id: String): Car = carService.getCar(id)

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun addCar(@RequestBody car: Car): Car = carService.saveCar(car)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateCar(@PathVariable id: String, @RequestBody car: Car): Car = carService.updateCar(id, car)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCar(@RequestBody id: String) {
        carService.deleteCar(id)
    }
}