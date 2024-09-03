package org.pcb.unit

data class Quadruple<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>, out D : Unit<*>>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D)
{
    operator fun <E: Unit<*>>times(fifth: E) =
        Quintuple(first, second, third, fourth, fifth)
}
