package org.pcb.unit.units.mass

import org.pcb.unit.Quadruple
import org.pcb.unit.Quintuple
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.mass.Ounce.OUNCE

class Ounce(amount: Number)
    : MassAmount<OUNCE>(amount = amount.toBigBigDecimal(),
                        unit = OUNCE)
{
    object OUNCE : Unit<Mass>(Mass::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override fun <B : Unit<*>> div(second: B) = Pair(this, second)
        override fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
        override fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
        override val symbol = "oz"
        override val scale = 0.028349523125.toBigBigDecimal()
    }
}