package com.example.vacationtracker.repo

import com.example.vacationtracker.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: JpaRepository<User, Long> {

    @Query(value = "select days_total from vacation_total where id = ?1 and year = ?2",
        nativeQuery = true)
    fun getTotal(id: Long, year: String): Int
    @Query(value = "select days_left from vacation_left where id = ?1 and year = ?2",
        nativeQuery = true)
    fun getUsed(id: Long, year: String): Int
    @Query(value = "select email from employees where id = ?1",
        nativeQuery = true)
    fun findByEmail(email: String): User

    // spring mapiranje je MAGIJA
//    fun findAllByEmailEqualsAndIdLessThanEqualAndVacationDaysLeftLessThanEqualAndVacationDaysTotal(email: String, id: String)
}