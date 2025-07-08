package org.vedenemo.dsl

enum class DataType { TEXT, DATE }

data class Attribute(
    val azName: String,
    val visName: String,
    val type: DataType,
    val activeSince: Version
)