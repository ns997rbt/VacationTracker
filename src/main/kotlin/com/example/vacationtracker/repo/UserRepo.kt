package com.example.vacationtracker.repo

import com.example.vacationtracker.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: JpaRepository<User, Long> {

    @Query(value = "select days_total from vacation_total where employee_id = ?1 and year = ?2",
        nativeQuery = true)
    fun getTotal(id: Long, year: String): Int
    @Query(value = "select days_left from vacation_left where employee_id = ?1 and year = ?2",
        nativeQuery = true)
    fun getLeft(id: Long, year: String): Int

    @Modifying
    @Query(value = "update User u set u.vacationDaysLeft = ?1")
    fun updateVacation(days: Int)

    fun findByEmail(email: String): User

    fun findUserById(id: Long): User

    // spring mapiranje je MAGIJA
//    fun findAllByEmailEqualsAndIdLessThanEqualAndVacationDaysLeftLessThanEqualAndVacationDaysTotal(email: String, id: String)
}