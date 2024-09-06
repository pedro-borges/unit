package org.pcb.unit

data class T2<out A : Unit<*>, out B : Unit<*>>(
        val first: A,
        val second: B)
{
    operator fun <B : Unit<*>> div(second: B) = Fraction(this, second)
    operator fun <B : T2<*, *>> div(second: B) = Fraction(this, second)
    operator fun <B : T3<*, *, *>> div(second: B) = Fraction(this, second)
    operator fun <B : T4<*, *, *, *>> div(second: B) = Fraction(this, second)
    operator fun <B : T5<*, *, *, *, *>> div(second: B) = Fraction(this, second)
    operator fun <B : T6<*, *, *, *, *, *>> div(second: B) = Fraction(this, second)
}
