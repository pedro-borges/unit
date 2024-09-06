package org.pcb.unit

data class T4<out A : Unit<*>, out B : Unit<*>, out C : Unit<*>, out D : Unit<*>>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D)
{
    infix fun <B : Unit<*>> to(second: B) = Pair(this, second)
    infix fun <B : T2<*, *>> to(second: B) = Pair(this, second)
    infix fun <B : T3<*, *, *>> to(second: B) = Pair(this, second)
    infix fun <B : T4<*, *, *, *>> to(second: B) = Pair(this, second)
    infix fun <B : T5<*, *, *, *, *>> to(second: B) = Pair(this, second)
    infix fun <B : T6<*, *, *, *, *, *>> to(second: B) = Pair(this, second)
}
