package com.example.vacationtracker.service

import com.example.vacationtracker.model.User
import com.example.vacationtracker.model.Vacation
import com.example.vacationtracker.model.VacationDTO
import com.example.vacationtracker.repo.UserRepo
import com.example.vacationtracker.repo.VacationRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserService {

    @Autowired
    lateinit var userRepo: UserRepo

    @Autowired
    lateinit var vacationRepo: VacationRepo
    @Autowired
    lateinit var dateService: DateService

    fun getTotal(id: Long, year: String): Int {
        return userRepo.getTotal(id,year)
    }

    fun getUsed(id: Long, year: String): Int {
        return userRepo.getTotal(id,year)-userRepo.getLeft(id,year)
    }

    fun getLeft(id: Long, year: String): Int {
        return getTotal(id,year)-getUsed(id,year)
    }

    fun uploadUser(user: User) {
        userRepo.save(user)
    }

    fun uploadVacation(vacation: Vacation) {
        vacationRepo.save(vacation)
    }

    fun searchVacation(id: Long, date1: String, date2: String): List<VacationDTO> {

        val d1: LocalDate = dateService.stringToDate(date1)
        val d2: LocalDate = dateService.stringToDate(date2)
        val u: User = findUserById(id)

        val dtoList: MutableList<VacationDTO> = mutableListOf()
        for ( vacation in u.vacations ) {

            if ( (d1 <= vacation.startDate) && (d2 > vacation.startDate) )
                dtoList.add(VacationDTO(vacation.startDate.toString(),vacation.endDate.toString()))
            if ( (d1 > vacation.startDate) && (d1 <= vacation.endDate))
                dtoList.add(VacationDTO(vacation.startDate.toString(),vacation.endDate.toString()))
        }
        return dtoList
    }

    fun findByEmail(email: String) :User{
        return userRepo.findByEmail(email)
    }

    fun findUserById(id: Long): User {
        return userRepo.findUserById(id)
    }
    fun uploadVacationByUser(date1: LocalDate, date2: LocalDate, duration: Int,user: User) {
        println(vacationRepo.checkDuplicates(date1,date2,user))
        if (vacationRepo.checkDuplicates(date1,date2,user) <= 0) {
            val year: Int = date1.year
            if (user.vacationDaysLeft[year.toString()]!! >= duration) {
                user.vacationDaysLeft[year.toString()] = user.vacationDaysLeft[year.toString()]!! - duration
                val vacation = Vacation(startDate = date1, endDate = date2, duration = duration, employee = user)
                user.vacations.add(vacation)
                vacationRepo.save(vacation)
            }
        }
    }
}
