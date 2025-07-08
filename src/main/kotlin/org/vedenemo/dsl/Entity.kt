package org.vedenemo.dsl

data class Entity(
    val azName: String,
    val visName: String,
    val attributes: List<Attribute> = emptyList()
)