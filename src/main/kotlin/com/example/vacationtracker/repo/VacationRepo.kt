package com.example.vacationtracker.repo

import com.example.vacationtracker.model.User
import com.example.vacationtracker.model.Vacation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface VacationRepo: JpaRepository<Vacation,Long> {

    @Query(value = "select count (e) from Vacation e where e.startDate = ?1 and e.endDate = ?2 and e.employee = ?3")
    fun checkDuplicates(date1: LocalDate, date2: LocalDate, employee: User): Int

    // before start before end
    // before start after end
    // after start before end
    // after start after end
}