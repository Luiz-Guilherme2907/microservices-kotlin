package com.guilherme.microserviceskotlin.model

import jakarta.persistence.*

@Entity
@Table(name = "person_table")
data class Person (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "first_name", nullable = false, length = 45)
    var firstName: String = "",

    @Column(name = "last_name", nullable = false, length = 45)
    var lastName: String = "",

    @Column(nullable = false, length = 50)
    var adress: String = "",

    @Column(nullable = false, length = 20)
    var gender: String = ""

)
