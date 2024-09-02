package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.space.Hand.HAND
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Hand(amount: Number)
    : LengthAmount<HAND>(amount.toBigBigDecimal(), HAND)
{
    object HAND : Unit<Length>(Length::class)
    {
        override val symbol = "hh"
        override val scale = 0.1016.toBigBigDecimal()
    }
}