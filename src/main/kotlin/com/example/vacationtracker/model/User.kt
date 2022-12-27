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
    //@OneToMany(mappedBy = "employee")
    @OneToMany(mappedBy = "id")
    var vacations: MutableList<Vacation> = mutableListOf()
)

//@ElementCollection
//    @CollectionTable(name = "order_item_mapping",
//      joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
//    @MapKeyColumn(name = "item_name")
//    @Column(name = "price")
//    private Map<String, Double> itemPriceMap;