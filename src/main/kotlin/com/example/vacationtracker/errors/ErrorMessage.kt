package com.example.vacationtracker.errors

enum class ErrorMessage(val msg: String) {

    UPLOADEMPLOYEEPARSER("uploadEmployee parser failure"),
    UPLOADTOTALPARSER("uploadTotal parser failure"),
    UPLOADUSEDPARSER("uploadUsed parser failure"),
    EMPTYFILE("Empty file"),
    BADCSVIMPORT("Error during CSV import"),
    FILENOTFOUND("File not found"),
    EMPLOYEEFILE("File needs to start with 'employee_profiles' and be '.csv' "),
    VACATIONFILE("File needs to start with 'vacations_' and be '.csv'"),
    USEDFILE("File needs to start with 'used_vacation_dates' and be '.csv'"),
    CANTFINDDATA("Data requested does not exist"),
    WRONGRANGE("Start date needs to be less or equal than end date"),
    AFTEROUTOFBOUNDS("Start date out of bounds"),
    BEFOREOUTOFBOUNDS("End date out of bounds"),
    YEARFORMAT("Year must be four digits long"),

}