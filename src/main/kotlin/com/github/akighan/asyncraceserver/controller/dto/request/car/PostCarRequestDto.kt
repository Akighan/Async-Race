package com.github.akighan.asyncraceserver.controller.dto.request.car

class PostCarRequestDto() {
    var name: String = ""
    var color: String = ""

    constructor(_name:String, _color:String):this() {
        name = _name
        color = _color
    }
}