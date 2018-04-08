package com.example.aven.kalkulator

import java.lang.reflect.Array
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

/**
 * Created by Aven on 2018-04-05.
 */
class Kalkulator {
    private val stackOfNumbers: Stack<BigDecimal> = Stack()

    fun addToStack(number: BigDecimal){
        stackOfNumbers.push(number)
    }

    fun swapTop(){
        if(moreThanOne()) {
            val tempValTop = stackOfNumbers.pop()
            val tempVal2ndTop = stackOfNumbers.pop()
            stackOfNumbers.push(tempValTop)
            stackOfNumbers.push(tempVal2ndTop)
        }
    }

    fun clearStackTop(){
        stackOfNumbers.pop()
    }

    fun clearStack() = stackOfNumbers.clear()

    fun getStackSize(): Int = stackOfNumbers.size

    fun isStackEmpty(): Boolean = stackOfNumbers.isEmpty()

    fun moreThanOne(): Boolean = stackOfNumbers.size>1

    fun addTop2(){
        if(moreThanOne()) {
            val topNumber = stackOfNumbers.pop()
            val anotherNumber = stackOfNumbers.pop()
            stackOfNumbers.push(topNumber.add(anotherNumber))
        }
    }

    fun subtractTop2(){
        if(moreThanOne()) {
            val topNumber = stackOfNumbers.pop()
            val anotherNumber = stackOfNumbers.pop()
            stackOfNumbers.push(anotherNumber.subtract(topNumber))
        }
    }

    fun multiplyTop2(){
        if(moreThanOne()) {
            val topNumber = stackOfNumbers.pop()
            val anotherNumber = stackOfNumbers.pop()
            stackOfNumbers.push(anotherNumber.multiply(topNumber))
        }
    }

    fun divideTop2(){
        if(moreThanOne()) {
            val topNumber = stackOfNumbers.pop()
            val anotherNumber = stackOfNumbers.pop()
            stackOfNumbers.push(anotherNumber.divide(topNumber))
        }
    }

    fun powTop(){
        val topNumber = stackOfNumbers.pop()
        stackOfNumbers.push(topNumber.pow(2))
    }

    fun sqrtTop(){
        val topNumber = stackOfNumbers.pop()
        val temp = Math.sqrt(topNumber.toDouble())
        stackOfNumbers.push(BigDecimal(temp))
    }

    fun changeTopSign(){
        val topNumber = stackOfNumbers.pop()
        val newNumber = topNumber.toDouble()
        stackOfNumbers.push(BigDecimal(-newNumber))
    }

    fun getTop4Numbers(): ArrayList<BigDecimal> {
        val numbers: ArrayList<BigDecimal> = ArrayList()
        for(i in stackOfNumbers.size-1 downTo stackOfNumbers.size-5){
            if(i>=0)
                numbers.add(stackOfNumbers[i])
            else
                numbers.add(BigDecimal(0))
        }
        return numbers
    }

    fun setPrecision(newPrec: Int){
        stackOfNumbers.forEach { i -> i.setScale(newPrec, RoundingMode.HALF_EVEN) }
    }
}