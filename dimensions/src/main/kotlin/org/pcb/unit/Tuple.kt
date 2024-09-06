package org.pcb.unit

data class Tuple<out A : Unit<*>, out B : Unit<*>>(
        val first: A,
        val second: B)
{
    operator fun <C : Unit<*>> times(third: C) = Triple(first, second, third)
    operator fun <B : Unit<*>> div(second: B) = Fraction(this, second)
    operator fun <B : Tuple<*, *>> div(second: B) = Fraction(this, second)
    operator fun <B : Triple<*, *, *>> div(second: B) = Fraction(this, second)
    operator fun <B : Quadruple<*, *, *, *>> div(second: B) = Fraction(this, second)
    operator fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Fraction(this, second)
    operator fun <B : Sextuple<*, *, *, *, *, *>> div(second: B) = Fraction(this, second)
}
