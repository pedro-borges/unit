package org.pcb.unit

data class Quadruple<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>, out D : Unit<*>>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D)
{
    operator fun <E : Unit<*>> times(fifth: E) = Quintuple(first, second, third, fourth, fifth)
    operator fun <B : Unit<*>> div(second: B) = Pair(this, second)
    operator fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
    operator fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : Sextuple<*, *, *, *, *, *>> div(second: B) = Pair(this, second)
}
