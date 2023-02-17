package com.example.weather.domain.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}