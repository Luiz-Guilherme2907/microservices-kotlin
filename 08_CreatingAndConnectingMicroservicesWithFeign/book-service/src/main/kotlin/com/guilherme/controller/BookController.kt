package com.guilherme.controller

import com.guilherme.model.Book
import com.guilherme.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException
import java.util.*

@RestController
@RequestMapping("book-service")
class BookController {
//http://localhost:8100/book-service/1/BRL
    @Autowired
    private lateinit var environment: Environment

    @Autowired
    private lateinit var repository: BookRepository
    @GetMapping(value = ["/{id}/{currency}"])
    fun findBook(
        @PathVariable("id") id: Long,
        @PathVariable("currency") currency: String
    ): Book?{
        val book = repository.findById(id).orElseThrow{RuntimeException("Book not Found")}
        val port = environment.getProperty("local.server.port")
        book.environment = port
        book.currency = currency
        return book
    }
}