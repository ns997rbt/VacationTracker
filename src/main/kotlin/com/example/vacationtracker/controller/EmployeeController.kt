package com.example.vacationtracker.controller

import com.example.vacationtracker.model.VacationDTO
import com.example.vacationtracker.service.DateService
import com.example.vacationtracker.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employee")
class EmployeeController {


    // validiraj podatke u mapingu
    // sredi rest
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var dateService: DateService

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

    @PostMapping("/upload/{id}/{date1}/{date2}")
    fun uploadVacation(@PathVariable id: Long,@PathVariable date1: String,@PathVariable date2: String) {
        userService.uploadVacationByUser(dateService.stringToDate(date1),dateService.stringToDate(date2),dateService.calculateDuration(dateService.stringToDate(date1),dateService.stringToDate(date2)),userService.findUserById(id))
    }

    @GetMapping("/search/{id}/{date1}/{date2}")
    fun searchVacation(@PathVariable id: Long,@PathVariable date1: String,@PathVariable date2: String): List<VacationDTO> {
        return userService.searchVacation(id,date1,date2)
    }
}