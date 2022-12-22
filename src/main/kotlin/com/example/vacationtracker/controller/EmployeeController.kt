package com.example.vacationtracker.controller

import com.example.vacationtracker.model.VacationDTO
import com.example.vacationtracker.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employee")
class EmployeeController {


    // validiraj podatke u mapingu
    // sredi rest
    lateinit var userService: UserService

    @GetMapping("/vacation/total/{id}/{year}")
    @ResponseBody
    fun showTotal(@PathVariable id: Long,@PathVariable year: String): Int {
        return userService.getTotal(id,year)
    }

    @GetMapping("/vacation/left/{id}/{year}")
    @ResponseBody
    fun showLeft(@PathVariable id: Long,@PathVariable year: String): Int {
        return userService.getLeft(id,year)
    }

    @GetMapping("/vacation/used/{id}/{year}")
    @ResponseBody
    fun showUsed(@PathVariable id: Long,@PathVariable year: String): Int {
        return userService.getUsed(id,year)
    }

    @GetMapping("/upload/{id}/{date1}/{date2}")
    fun uploadVacation(@PathVariable id: Long,@PathVariable date1: String,@PathVariable date2: String) {
        return userService.uploadVacation(id,date1,date2)
    }

    @GetMapping("/search/{id}/{date1}/{date2}")
    fun searchVacation(@PathVariable id: Long,@PathVariable date1: String,@PathVariable date2: String): List<VacationDTO> {
        return userService.SearchVacation(id,date1,date2)
    }
}