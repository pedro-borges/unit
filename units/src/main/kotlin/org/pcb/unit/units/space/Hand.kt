package org.pcb.unit.units.space

import org.pcb.unit.Quadruple
import org.pcb.unit.Quintuple
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.Hand.HAND

class Hand(amount: Number)
    : LengthAmount<HAND>(amount = amount.toBigBigDecimal(),
                         unit = HAND)
{
    object HAND : Unit<Length>(Length::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override fun <B : Unit<*>> div(second: B) = Pair(this, second)
        override fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
        override fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
        override val symbol = "hh"
        override val scale = 0.1016.toBigBigDecimal()
    }
}