package com.example.vacationtracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication()
class VacationTrackerApplication

fun main(args: Array<String>) {
    runApplication<VacationTrackerApplication>(*args)
}
