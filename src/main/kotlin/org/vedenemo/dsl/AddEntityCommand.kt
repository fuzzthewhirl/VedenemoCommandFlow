package org.vedenemo.dsl

data class AddEntityCommand(val entity: Entity) : ModelCommand {
    override fun execute(on: Model): Model {
        val newEntities = on.entities + (entity.azName to entity)
        return on.copy(entities = newEntities)
    }

    override fun undo(): ModelCommand = RemoveEntityCommand(entity.azName)

    override fun toDsl(): String = "entity \"${entity.azName}\" named \"${entity.visName}\""
}

data class RemoveEntityCommand(val azName: String) : ModelCommand {
    override fun execute(on: Model): Model {
        val newEntities = on.entities - azName
        return on.copy(entities = newEntities)
    }

    override fun undo(): ModelCommand = throw UnsupportedOperationException()
    override fun toDsl(): String = "# remove entity \"$azName\" (not reversible here)"
}