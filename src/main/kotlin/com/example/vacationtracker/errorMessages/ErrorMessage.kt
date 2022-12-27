package com.example.vacationtracker.errorMessages

enum class ErrorMessage(val msg: String) {

    UPLOADEMPLOYEEPARSER("uploadEmployee parser failure"),
    UPLOADTOTALPARSER("uploadTotal parser failure"),
    UPLOADUSEDPARSER("uploadUsed parser failure"),
    EMPTYFILE("Empty file"),
    BADCSVIMPORT("Error during CSV import"),
}