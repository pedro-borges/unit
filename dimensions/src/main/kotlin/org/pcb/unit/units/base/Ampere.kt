package org.pcb.unit.units.base

import org.pcb.unit.Quadruple
import org.pcb.unit.Quintuple
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricCurrentAmount
import org.pcb.unit.units.base.Ampere.AMPERE

@Suppress("unused")
class Ampere(amount: Number)
    : ElectricCurrentAmount<AMPERE>(amount, AMPERE)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object AMPERE : Unit<ElectricCurrent>(ElectricCurrent::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override fun <B : Unit<*>> div(second: B) = Pair(this, second)
        override fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
        override fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)

        override val symbol = "A"
        override val scale = 1.toBigBigDecimal()
    }
}
