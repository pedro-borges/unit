package org.pcb.unit

data class Sextuple<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>, out D : Unit<*>, out E : Unit<*>, out F : Unit<*>>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D,
        val fifth: E,
        val sixth: F)
{
    operator fun <B : Unit<*>> div(second: B) = Pair(this, second)
    operator fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
    operator fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
}
