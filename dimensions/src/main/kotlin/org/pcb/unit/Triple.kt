package org.pcb.unit

data class Triple<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>>(
        val first: A,
        val second: B,
        val third: C)
{
    operator fun <D : Unit<*>> times(fourth: D) = Quadruple(first, second, third, fourth)
    operator fun <B : Unit<*>> div(second: B) = Pair(this, second)
    operator fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
    operator fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
}
