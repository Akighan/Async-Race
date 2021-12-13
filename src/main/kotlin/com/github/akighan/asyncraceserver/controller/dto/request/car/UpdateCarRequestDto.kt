package com.github.akighan.asyncraceserver.controller.dto.request.car

class UpdateCarRequestDto() {
    var name: String = ""
    var color: String = ""

    constructor(
        name: String,
        color: String
    ) : this() {
        this.name = name
        this.color = color
    }
}