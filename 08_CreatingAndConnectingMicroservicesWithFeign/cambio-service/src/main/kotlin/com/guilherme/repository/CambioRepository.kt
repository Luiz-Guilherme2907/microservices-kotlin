package com.guilherme.repository

import com.guilherme.model.Cambio
import org.springframework.data.jpa.repository.JpaRepository

interface CambioRepository : JpaRepository<Cambio, Long?>  {
    fun findByFromAndTo(from : String, to : String): Cambio?

}