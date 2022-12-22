package com.example.vacationtracker.service

import com.example.vacationtracker.model.User
import com.example.vacationtracker.model.VacationDTO
import com.example.vacationtracker.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class UserService {

    @Autowired
    lateinit var userRepo: UserRepo

    fun getTotal(id: Long, year: String): Int {
        return userRepo.getTotal(id,year)
    }

    fun getUsed(id: Long, year: String): Int {
        return userRepo.getUsed(id,year)
    }

    fun getLeft(id: Long, year: String): Int {
        return getTotal(id,year)-getUsed(id,year)
    }

    fun uploadUser(user: User) {
       userRepo.save(user)
    }

    fun uploadVacation(id: Long, date1: String, date2: String) {

    }

    fun SearchVacation(id: Long, date1: String, date2: String): List<VacationDTO> {

        return emptyList()
    }

    fun findByEmail(email: String): User {
        return userRepo.findByEmail(email)
    }

    fun  format (date: Date): String {
        return SimpleDateFormat("dd.MM.YYYY.", Locale.getDefault()).format(date)
    }

}