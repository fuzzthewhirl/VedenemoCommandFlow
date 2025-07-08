package org.vedenemo.dsl

data class AddAttributeCommand(val entityAzName: String, val attribute: Attribute) : ModelCommand {
    override fun execute(on: Model): Model {
        val entity = on.entities[entityAzName] ?: return on
        val updated = entity.copy(attributes = entity.attributes + attribute)
        return on.copy(entities = on.entities + (entityAzName to updated))
    }

    override fun undo(): ModelCommand = RemoveAttributeCommand(entityAzName, attribute.azName)

    override fun toDsl(): String = "\"$entityAzName\" addAttribute \"${attribute.azName}\" ofType ${attribute.type} since ${attribute.activeSince}"
}

data class RemoveAttributeCommand(val entityAzName: String, val attrAzName: String) : ModelCommand {
    override fun execute(on: Model): Model {
        val entity = on.entities[entityAzName] ?: return on
        val updated = entity.copy(attributes = entity.attributes.filterNot { it.azName == attrAzName })
        return on.copy(entities = on.entities + (entityAzName to updated))
    }

    override fun undo(): ModelCommand = throw UnsupportedOperationException()
    override fun toDsl(): String = "# remove attribute \"$attrAzName\" from \"$entityAzName\" (not reversible here)"
}