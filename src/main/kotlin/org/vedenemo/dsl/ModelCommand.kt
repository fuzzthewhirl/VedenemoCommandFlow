package org.vedenemo.dsl

interface ModelCommand {
    fun execute(on: Model): Model
    fun undo(): ModelCommand
    fun toDsl(): String
}