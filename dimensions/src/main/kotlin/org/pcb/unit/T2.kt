package org.pcb.unit

data class T2<out A : Unit<*>, out B : Unit<*>>(
        val first: A,
        val second: B)
{
    infix fun <B : Unit<*>> to(second: B) = Pair(this, second)
    infix fun <B : T2<*, *>> to(second: B) = Pair(this, second)
    infix fun <B : T3<*, *, *>> to(second: B) = Pair(this, second)
    infix fun <B : T4<*, *, *, *>> to(second: B) = Pair(this, second)
    infix fun <B : T5<*, *, *, *, *>> to(second: B) = Pair(this, second)
    infix fun <B : T6<*, *, *, *, *, *>> to(second: B) = Pair(this, second)
}
