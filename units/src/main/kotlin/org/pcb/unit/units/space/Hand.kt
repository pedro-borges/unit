package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount

class Hand(amount: Number)
    : LengthAmount<Unit<Length>>(
    amount = amount,
    unit = HAND)
{
    object HAND : Unit<Length>(Length::class)
    {
        override val symbol = "hh"
        override val scale = 0.1016.toBigBigDecimal()
    }
}