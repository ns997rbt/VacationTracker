package com.example.vacationtracker.model

import jakarta.persistence.*

@Entity
    data class Vacation(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,
    // timestamp ?
    var startDate: String? = null,
    var endDate: String? = null,
    @ManyToOne
    @JoinColumn(name = "employee")
    var employee: User
)

