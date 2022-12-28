package com.example.vacationtracker.controller

import com.example.vacationtracker.exceptions.CsvException
import com.example.vacationtracker.service.AdminService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


// svuda uraditi validaciju pre prosledjivanja servisu i promeniti tip podatka
@RestController
@RequestMapping("/admin")
class AdminController (private val adminService: AdminService) {
    @PostMapping("/upload-employees")
    @Validated
    fun uploadEmployees(@RequestParam(name = "file", required = true) file: MultipartFile) {
        //if ( file.name.startsWith("employee_profiles") && file.contentType.equals("csv"))
        adminService.uploadEmployee(file)
        //else throw CsvException(ErrorMessage.EMPLOYEEFILE.msg)

    }

    @PostMapping("/upload-total")
    fun uploadTotal(@RequestParam(name = "file", required = true) file: MultipartFile) {
        //if ( file.name.startsWith("vacations_") && file.contentType.equals("csv"))
        adminService.uploadTotal(file)
        //else throw CsvException(ErrorMessage.VACATIONFILE.msg)
    }

    @PostMapping("/upload-used")
    fun uploadUsed(@RequestParam(name = "file", required = true) file: MultipartFile) {
        //if ( file.name.startsWith("used_vacation_dates") && file.contentType.equals("csv"))
        adminService.uploadUsed(file)
       // else throw CsvException(ErrorMessage.USEDFILE.msg)

    }

    @ControllerAdvice
    class ControllerAdviceRequestError : ResponseEntityExceptionHandler(){
    @ExceptionHandler(CsvException::class)
    fun errorHandle(e: CsvException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }}

}