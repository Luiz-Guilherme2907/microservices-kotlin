package com.guilherme.microserviceskotlin

import com.guilherme.microserviceskotlin.exceptions.UnsupportedMathOperationException
import jdk.swing.interop.DragSourceContextWrapper
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception
import java.util.concurrent.atomic.AtomicLong


@RestController
class MathController {
    val counter: AtomicLong = AtomicLong()

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value!")
        return convertToDouble(numberOne) + convertToDouble(numberTwo)
    }
    @RequestMapping(value = ["/sub/{numberOne}/{numberTwo}"])
    fun subtraction(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?
            ): Double{
                if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value!")
                return convertToDouble(numberOne) - convertToDouble(numberTwo)
    }
    @RequestMapping(value = ["/mul/{numberOne}/{numberTwo}"])
    fun multiplication(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?
            ): Double{
                if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value!")
                return convertToDouble(numberOne) * convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/div/{numberOne}/{numberTwo}"])
    fun division(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?
            ): Double{
                if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value!")
                return convertToDouble(numberOne) / convertToDouble(numberTwo)
    }
    @RequestMapping(value = ["/media/{numberOne}/{numberTwo}"])
    fun media(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?
            ): Double{
          if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value!")
          val media = convertToDouble(numberOne) + convertToDouble(numberTwo)
          return media / 2
    }
    @RequestMapping(value = ["/squareroot/{numberOne}/{numberTwo}"])
    fun squareRoot (@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?
            ): Double{
          if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value!")
          val squareRoot  = convertToDouble(numberOne) + convertToDouble(numberTwo)
          return Math.sqrt(squareRoot)
    }
    @RequestMapping(value = ["/squareroot/{numberOne}"])
    fun squareRootNumberOne (@PathVariable(value = "numberOne") numberOne: String?): Double{
          if (!isNumeric(numberOne)) throw UnsupportedMathOperationException("Please set a numeric value!")
            val squareRoot = convertToDouble(numberOne)
          return Math.sqrt(squareRoot)
    }



    private fun convertToDouble(strNumber: String?): Double {
        if(strNumber.isNullOrBlank()) return 0.0
        //BR 10,20 US 10.20
        val number = strNumber.replace(",".toRegex(), ".")
        return if (isNumeric(number)) number.toDouble() else 0.0
    }

    private fun isNumeric(strNumber: String?): Boolean {
        if(strNumber.isNullOrBlank()) return false;
        val number = strNumber.replace(",".toRegex(), ".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }
}