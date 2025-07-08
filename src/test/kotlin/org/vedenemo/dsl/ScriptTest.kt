package org.vedenemo.dsl

import kotlin.test.Test
import kotlin.test.assertEquals

class ScriptTest {

    @Test
    fun testScriptExecution() {
        val lines = listOf(
            "entity \"Person\" named \"Person\"",
            "\"Person\" addAttribute \"email\" ofType TEXT since 1.0",
            "\"Person\" addAttribute \"birthDate\" ofType DATE since 1.0"
        )

        val group = loadScript(lines)
        val initialModel = Model("TestModel", "Test Model", Version(1, 0), entities = emptyMap())
        val finalModel = group.execute(initialModel)

        val person = finalModel.entities["Person"]
        requireNotNull(person) { "Entity 'Person' should exist" }
        assertEquals(2, person.attributes.size)
        assertEquals("email", person.attributes[0].azName)
        assertEquals(DataType.TEXT, person.attributes[0].type)

        println("--- Serialized DSL ---")
        println(group.toDsl())
    }
}