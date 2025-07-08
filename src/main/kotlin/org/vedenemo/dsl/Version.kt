package org.vedenemo.dsl

data class Version(val major: Int, val minor: Int) {
    override fun toString(): String = "\$major.\$minor"
    companion object {
        fun parse(str: String): Version {
            val parts = str.split(".")
            return Version(parts[0].toInt(), parts[1].toInt())
        }
    }
}