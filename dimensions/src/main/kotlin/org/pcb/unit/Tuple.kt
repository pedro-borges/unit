package org.pcb.unit

data class Tuple<out A : Unit<*>, out B : Unit<*>>(
        val first: A,
        val second: B)
{
    operator fun <C: Unit<*>>times(third: C) =
        Triple(first, second, third)
}
