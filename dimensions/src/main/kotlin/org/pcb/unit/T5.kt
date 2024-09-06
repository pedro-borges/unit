package org.pcb.unit

data class T5<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>, out D : Unit<*>, out E : Unit<*>>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D,
        val fifth: E)
{
    operator fun <B : Unit<*>> div(second: B) = Pair(this, second)
    operator fun <B : T2<*, *>> div(second: B) = Pair(this, second)
    operator fun <B : T3<*, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : T4<*, *, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : T5<*, *, *, *, *>> div(second: B) = Pair(this, second)
    operator fun <B : T6<*, *, *, *, *, *>> div(second: B) = Pair(this, second)
}
