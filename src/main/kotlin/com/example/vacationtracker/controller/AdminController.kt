package com.example.vacationtracker.controller

import com.example.vacationtracker.service.AdminService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


// svuda uraditi validaciju pre prosledjivanja servisu i promeniti tip podatka
@RestController
@RequestMapping("/admin")
class AdminController (private val adminService: AdminService) {
    @PostMapping("/upload-employees")
    fun uploadEmployees(@RequestParam("file") file: MultipartFile) {
        adminService.uploadEmployee(file)
    }

    @PostMapping("/upload-total")
    fun uploadTotal(@RequestParam("file") file: MultipartFile) {
        adminService.uploadTotal(file)
    }

    @PostMapping("/upload-used")
    fun uploadUsed(@RequestParam("file") file: MultipartFile) {
        adminService.uploadUsed(file)
    }
}