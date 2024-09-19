package org.pcb.unit.dimensions

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.Composite
import org.pcb.unit.minus
import org.pcb.unit.plus
import org.reflections.Reflections

class DimensionTest
{
    companion object
    {
        @JvmStatic
        val dimensions =
            Reflections("org.pcb.unit.dimensions")
                .getSubTypesOf(Dimension::class.java)
                .sortedBy { it.simpleName }
                .toList()
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun correctness(dimension: Class<out Dimension>)
    {
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

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun uniqueness(dimension: Class<out Dimension>)
    {
        dimensions
            .filter { !it.isAssignableFrom(dimension) }
            .forEach {
                val dimensionComposite = dimension.kotlin.annotations.first { it is Composite } as Composite
                val itComposite = it.kotlin.annotations.first { it is Composite } as Composite

                assertNotEquals(dimensionComposite, itComposite)
            }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun combinations(dimension: Class<out Dimension>)
    {
        val dimensionComposite = dimension.kotlin.annotations.first { it is Composite } as Composite

        val regions = HashMap<String, String>()

        dimensions.forEach { operatorDimension ->
            val operatorDimensionComposite = operatorDimension.kotlin.annotations.first { it is Composite } as Composite

            dimensions
                .sortedBy { it.simpleName }
                .forEach { resultDimension ->
                    val resultDimensionComposite = resultDimension.kotlin.annotations.first { it is Composite } as Composite
                    var code = regions[resultDimension.simpleName] ?: ""

                    if (dimensionComposite - operatorDimensionComposite == resultDimensionComposite)
                    {

                        code += "\toperator fun div(that: ${operatorDimension.simpleName}) = ${resultDimension.simpleName}\n"
//                        assertTrue("${dimension.simpleName} / ${operatorDimension.simpleName}) = ${resultDimension.simpleName}") {
//                            dimension.methods
//                                .filter { method -> Dimension::class.java.isAssignableFrom(method.returnType) }
//                                .any { method ->
//                                    method.name == "div" &&
//                                    method.parameters[0].type.isAssignableFrom(operatorDimension) &&
//                                    method.returnType.isAssignableFrom(resultDimension)
//                                }
//                        }
                    }
                    if (dimensionComposite + operatorDimensionComposite == resultDimensionComposite)
                    {
                        code += "\toperator fun times(that: ${operatorDimension.simpleName}) = ${resultDimension.simpleName}\n"
//                        assertTrue("Missing ${dimension.simpleName} * ${operatorDimension.simpleName}) = ${resultDimension.simpleName}") {
//                            dimension.methods
//                                .filter { method -> Dimension::class.java.isAssignableFrom(method.returnType) }
//                                .any { method ->
//                                    method.name == "times" &&
//                                    method.parameters[0].type.isAssignableFrom(operatorDimension) &&
//                                    method.returnType.isAssignableFrom(resultDimension)
//                                }
//                        }
                    }

                    if (code != "") regions[resultDimension.simpleName] = code
                }

        }

        val stub = this.javaClass.classLoader.getResource("org/pcb/unit/dimensions/" + dimension.simpleName)!!

        val contents = stub.readText()
            .replace(oldValue = "\$operators",
                     newValue = regions.keys
                         .asSequence()
                         .sorted()
                         .sortedBy { it != Scalar::class.simpleName }
                         .sortedBy { it != dimension.simpleName }
                         .map { regions[it] }
                         .joinToString(separator = "\n"))

        println(contents)
    }
}