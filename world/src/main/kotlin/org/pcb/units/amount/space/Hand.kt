package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.space.Hand.HAND
import org.pcb.units.dimension.Length
import org.pcb.units.toBigBigDecimal

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