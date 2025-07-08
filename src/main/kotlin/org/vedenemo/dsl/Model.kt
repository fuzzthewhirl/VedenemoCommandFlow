package org.vedenemo.dsl

data class Model(
    val azName: String,
    val visName: String,
    val version: Version,
    val entities: Map<String, Entity>
)