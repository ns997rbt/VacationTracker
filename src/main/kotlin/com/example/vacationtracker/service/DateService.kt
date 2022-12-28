package com.example.vacationtracker.service

import com.example.vacationtracker.DateProgression
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class DateService {
    operator fun LocalDate.rangeTo(other: LocalDate) = DateProgression(this, other)

    fun dateToString(date: Date): String {
        return SimpleDateFormat("mm-dd-yyyy", Locale.getDefault()).format(date)
    }

    fun asdf(string: String): LocalDate {
        return LocalDate.parse(string, DateTimeFormatter.ofPattern("d.M.yyyy"))
    }

    fun stringToDate(string: String): LocalDate {
        return LocalDate.parse(string, DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy"))
    }
    
    fun calculateDuration (date1: LocalDate, date2: LocalDate): Int {
        var duration = 0
        if (date1 <= date2) {
            for (date: LocalDate in date1..date2) {
                if ((date.dayOfWeek != DayOfWeek.SATURDAY) && (date.dayOfWeek != DayOfWeek.SUNDAY))
                    duration++
            }
        }
        return duration
    }
}