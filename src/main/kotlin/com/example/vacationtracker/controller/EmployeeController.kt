package com.example.vacationtracker.controller

import com.example.vacationtracker.errors.ErrorMessage
import com.example.vacationtracker.exceptions.DataException
import com.example.vacationtracker.service.DateService
import com.example.vacationtracker.service.UserService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestController
@RequestMapping("/employee")
class EmployeeController {

    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var dateService: DateService

    @GetMapping("/search/total")
    @ResponseBody
    @Validated
    fun showTotal(@RequestParam(name = "id", required = true) id: Long,@RequestParam(name = "year", required = true) year: String): String? {
        // napravicu listu dozvoljenih godina ovo je privremeno
        if ((year.toInt() != 2019) && (year.toInt() !=2020) && (year.toInt() != 2021))
            throw DataException(ErrorMessage.CANTFINDDATA.msg)
        return jacksonObjectMapper().writeValueAsString(userService.getTotal(id,year))
    }
    @GetMapping("/search/left")
    @ResponseBody
    @Validated
    fun showLeft(@RequestParam(name = "id", required = true) id: Long,@RequestParam(name = "year", required = true) year: String): String? {
        if ((year.toInt() != 2019) && (year.toInt() !=2020) && (year.toInt() != 2021))
            throw DataException(ErrorMessage.CANTFINDDATA.msg)
        return jacksonObjectMapper().writeValueAsString(userService.getLeft(id,year))
    }
    @GetMapping("/search/used")
    @ResponseBody
    @Validated
    fun showUsed(@RequestParam(name = "id", required = true) id: Long,@RequestParam(name = "year", required = true) year: String): String? {
        if ((year.toInt() != 2019) && (year.toInt() !=2020) && (year.toInt() != 2021))
            throw DataException(ErrorMessage.CANTFINDDATA.msg)
        return jacksonObjectMapper().writeValueAsString(userService.getUsed(id,year))
    }



    @PostMapping("/upload/vacation")
    @Validated
    fun uploadVacation(@RequestParam(name = "id", required = true) id: Long,@RequestParam(name = "start", required = true) date1: String,@RequestParam(name = "end", required = true) date2: String) {
        if (date1.length != 10 || date2.length != 10)
            throw DataException(ErrorMessage.YEARFORMAT.msg)
        val d1 = dateService.asdf(date1)
        val d2 = dateService.asdf(date2)
        if (d1.isAfter(d2))
            throw DataException(ErrorMessage.WRONGRANGE.msg)
        userService.uploadVacationByUser(d1,d2,dateService.calculateDuration(d1,d2),userService.findUserById(id))
    }

    @GetMapping("/search/between")
    @Validated
    fun searchVacation(@RequestParam(name = "id", required = true) id: Long,@RequestParam(name = "after", required = true) after: String,@RequestParam(name = "before", required = true) before: String): String? {
        if (after.length != 10 || before.length != 10)
            throw DataException(ErrorMessage.YEARFORMAT.msg)
        val d1 = dateService.asdf(before)
        val d2 = dateService.asdf(after)

        if (d1.year<2019 || d1.year > 2021)
            throw DataException(ErrorMessage.AFTEROUTOFBOUNDS.msg)
        if (d2.year<2019 || d2.year > 2021)
            throw DataException(ErrorMessage.BEFOREOUTOFBOUNDS.msg)
        if (d1.isBefore(d2))
            throw DataException(ErrorMessage.WRONGRANGE.msg)
        return jacksonObjectMapper().writeValueAsString(userService.searchVacation(id,after,before))
    }
    @ControllerAdvice
    class ControllerAdviceRequestError : ResponseEntityExceptionHandler(){
        @ExceptionHandler(DataException::class)
        fun dataExceptions(e: DataException): ResponseEntity<String> {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}