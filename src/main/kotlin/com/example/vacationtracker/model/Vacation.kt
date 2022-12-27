package com.example.vacationtracker.model

import jakarta.persistence.*
import java.time.LocalDate
@Entity
    data class Vacation(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,
    // timestamp ?
    @Column(name = "start_date")
    var startDate: LocalDate,
    @Column(name = "end_date")
    var endDate: LocalDate,
    @Column(name = "duration")
    var duration: Int,
    @ManyToOne
    @JoinColumn(name = "employee_id")
    var employee: User
)

