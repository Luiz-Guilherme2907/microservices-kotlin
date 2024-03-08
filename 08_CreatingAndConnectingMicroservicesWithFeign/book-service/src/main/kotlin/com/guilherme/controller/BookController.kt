package com.guilherme.controller

import com.guilherme.model.Book
import com.guilherme.proxy.CambioProxy
import com.guilherme.repository.BookRepository
import com.guilherme.response.Cambio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.HashMap

@RestController
@RequestMapping("book-service")
class BookController {
//http://localhost:8100/book-service/1/BRL
    @Autowired
    private lateinit var environment: Environment

    @Autowired
    private lateinit var repository: BookRepository

    @Autowired
    private lateinit var proxy: CambioProxy
    //Utilizando Feign
    @GetMapping(value = ["/{id}/{currency}"])
    fun findBook(
        @PathVariable("id") id: Long,
        @PathVariable("currency") currency: String
    ): Book?{
        val book = repository.findById(id).orElseThrow{RuntimeException("Book not Found")}

        val cambio = proxy.getCambio(book.price, "USD", currency)

        val port = environment.getProperty("local.server.port")
        book.environment = "$port FEIGN"
        book.currency = currency
        book.price = cambio!!.convertedValue
        return book
    }
   // Utilizando Rest Template
    @GetMapping(value = ["/v1/{id}/{currency}"])
    fun findBookV1(
        @PathVariable("id") id: Long,
        @PathVariable("currency") currency: String
    ): Book?{
        val book = repository.findById(id).orElseThrow{RuntimeException("Book not Found")}

        val params = HashMap<String, String>()
        params["amount"] = book.price.toString()
        params["from"] = "USD"
        params["to"] = currency

        val response = RestTemplate()
            .getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}",
                Cambio::class.java,
                params
                )
        val cambio = response.body

        val port = environment.getProperty("local.server.port")
        book.environment = port
        book.currency = currency
        book.price = cambio!!.convertedValue
        return book
    }
}