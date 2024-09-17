package org.pcb.unit.dimensions

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.pcb.unit.Composite
import org.pcb.unit.minus
import org.pcb.unit.plus
import org.reflections.Reflections

class DimensionTest
{
    @Test
    fun correctness()
    {
        val reflections = Reflections("org.pcb.unit.dimensions")

        reflections.getSubTypesOf(Dimension::class.java)
            .forEach { dimension ->
                dimension.methods
                    .filter { method -> Dimension::class.java.isAssignableFrom(method.returnType) }
                    .forEach { method ->
                        val dimensionComposite = dimension.kotlin.annotations.first { it is Composite } as Composite
                        val argumentComposite = method.parameters[0].type.kotlin.annotations.first { it is Composite } as Composite
                        val returnComposite = method.returnType.kotlin.annotations.first { it is Composite } as Composite

                        val methodName =
                            "${dimension.simpleName}.${method.name}(" + method.parameters.joinToString(", ") { it.type.simpleName } + ") = " + method.returnType.simpleName

                        when (method.name)
                        {
                            "div"   -> assertEquals(returnComposite, dimensionComposite - argumentComposite, methodName)
                            "times" -> assertEquals(returnComposite, dimensionComposite + argumentComposite, methodName)
                            else    -> throw AssertionError(method.name)
                        }
                    }
            }
    }

    @Test
    fun combinations()
    {
        val reflections = Reflections("org.pcb.unit.dimensions")

        val dimensions = reflections.getSubTypesOf(Dimension::class.java)

        dimensions
            .sortedBy { it.simpleName }
            .forEach { dimension1 ->
                val dimension1Composite = dimension1.kotlin.annotations.first { it is Composite } as Composite

                println(dimension1.simpleName)
                dimensions.forEach { dimension2 ->
                    val dimension2Composite = dimension2.kotlin.annotations.first { it is Composite } as Composite

                    dimensions
                        .sortedBy { it.simpleName }
                        .forEach { dimension ->
                            val dimensionComposite = dimension.kotlin.annotations.first { it is Composite } as Composite

                            if (dimension1Composite - dimension2Composite == dimensionComposite)
                            {
                                println("\toperator fun div(that: ${dimension2.simpleName}) = ${dimension.simpleName}")
                                assertTrue("${dimension1.simpleName} / ${dimension2.simpleName}) = ${dimension.simpleName}") {
                                    dimension1.methods
                                        .filter { method -> Dimension::class.java.isAssignableFrom(method.returnType) }
                                        .any { method ->
                                            method.name == "div" &&
                                            method.parameters[0].type.isAssignableFrom(dimension2) &&
                                            method.returnType.isAssignableFrom(dimension)
                                        }
                                }
                            }
                            if (dimension1Composite + dimension2Composite == dimensionComposite)
                            {
                                println("\toperator fun times(that: ${dimension2.simpleName}) = ${dimension.simpleName}")
                                assertTrue("Missing ${dimension1.simpleName} * ${dimension2.simpleName}) = ${dimension.simpleName}") {
                                    dimension1.methods
                                        .filter { method -> Dimension::class.java.isAssignableFrom(method.returnType) }
                                        .any { method ->
                                            method.name == "times" &&
                                            method.parameters[0].type.isAssignableFrom(dimension2) &&
                                            method.returnType.isAssignableFrom(dimension)
                                        }
                                }
                            }
                        }
                }
            }
    }
}