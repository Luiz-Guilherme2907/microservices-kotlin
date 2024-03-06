package com.guilherme.controller

import com.guilherme.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("book-service")
class BookController {
//http://localhost:8100/book-service/1/BRL
    @Autowired
    private lateinit var environment: Environment

    @GetMapping(value = ["/{id}/{currency}"])
    fun findBook(
        @PathVariable("id") id: Long,
        @PathVariable("currency") currency: String
    ): Book?{
        val port = environment.getProperty("local.server.port")
        return Book(
            id = 1L,
            author = "Nigel",
            title = "Docker Deep Dive",
            launchData = Date(),
            price = 15.8.toDouble(),
            currency = currency,
            environment = port
        )

    }
}