package com.github.akighan.asyncraceserver.model

abstract class Engine {
    val distance:Int = 50000
    var velocity:Int = 0
    var status:Enum<Status> = Status.STOPPED
    abstract fun onStartEngine():Car
    abstract fun onStopEngine():Car
    abstract fun driveMode():Boolean

    enum class Status {
        STARTED, STOPPED, DRIVE
    }
}