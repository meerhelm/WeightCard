package com.demo.weightcard.logic.validation

interface Validator<in T> {
    //returns error string when value is not valid. Returns null when value is valid
    fun validate(value: T?): String?
}