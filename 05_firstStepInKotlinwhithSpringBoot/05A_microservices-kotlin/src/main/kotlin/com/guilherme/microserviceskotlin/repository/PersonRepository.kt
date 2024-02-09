package com.guilherme.microserviceskotlin.repository

import com.guilherme.microserviceskotlin.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PersonRepository: JpaRepository<Person, Long?>