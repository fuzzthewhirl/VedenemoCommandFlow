package org.vedenemo.dsl

data class CommandGroup(val label: String, val commands: List<ModelCommand>) : ModelCommand {
    override fun execute(on: Model): Model = commands.fold(on) { acc, cmd -> cmd.execute(acc) }
    override fun undo(): CommandGroup = CommandGroup("Undo $label", commands.map { it.undo() }.reversed())
    override fun toDsl(): String = commands.joinToString("\n") { it.toDsl() }
}