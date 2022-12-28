package com.example.vacationtracker.model

import jakarta.persistence.*

@Entity
@Table(name = "employees")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long?= null,
    @Column(name = "email",unique = true)
    var email: String? = null,
    @Column(name = "password")
    var password: String? = null,
    @ElementCollection
    @CollectionTable(name = "vacation_total",
        joinColumns = [JoinColumn(name = "employee_id")])
    @MapKeyColumn(name = "year")
    @Column(name = "days_total")
    var vacationDaysTotal: MutableMap<String,Int> = mutableMapOf(),
    @ElementCollection
    @CollectionTable(name = "vacation_left",
        joinColumns = [JoinColumn(name = "employee_id")])
    @MapKeyColumn(name = "year")
    @Column(name = "days_left")
    var vacationDaysLeft: MutableMap<String,Int> = mutableMapOf(),
    @OneToMany
    var vacations: MutableList<Vacation> = mutableListOf()
)