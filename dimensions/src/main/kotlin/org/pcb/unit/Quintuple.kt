package org.pcb.unit

data class Quintuple<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>, out D : Unit<*>, out E : Unit<*>>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D,
        val fifth: E)
{
    operator fun <E : Unit<*>> times(sixth: E) = Sextuple(first, second, third, fourth, fifth, sixth)
    operator fun <B : Unit<*>> div(second: B) = Pair(this, second)
    operator fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
    operator fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : Sextuple<*, *, *, *, *, *>> div(second: B) = Pair(this, second)
}
