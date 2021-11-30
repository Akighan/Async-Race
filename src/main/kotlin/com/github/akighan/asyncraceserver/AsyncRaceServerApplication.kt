package com.github.akighan.asyncraceserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AsyncRaceServerApplication

fun main(args: Array<String>) {
    runApplication<AsyncRaceServerApplication>(*args)
}
