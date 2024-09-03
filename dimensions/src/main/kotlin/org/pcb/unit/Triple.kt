package org.pcb.unit

data class Triple<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>>(
        val first: A,
        val second: B,
        val third: C)
{
    operator fun <D: Unit<*>>times(fourth: D) =
        Quadruple(first, second, third, fourth)
}
