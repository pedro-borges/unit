package org.pcb.unit.units.space

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.Hand.HAND

@Suppress("unused")
class Hand(amount: Number)
    : LengthAmount<HAND>(amount.toBigBigDecimal(), HAND)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object HAND : Unit<Length>(Length::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "hh"
        override val scale = 0.1016.toBigBigDecimal()
    }
}