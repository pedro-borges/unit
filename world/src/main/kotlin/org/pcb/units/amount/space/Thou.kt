package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.space.Thou.THOU
import org.pcb.units.dimension.Length
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Thou(amount: Number)
    : LengthAmount<THOU>(amount.toBigBigDecimal(), THOU)
{
    object THOU : Unit<Length>(Length::class)
    {
        override val symbol = "th"
        override val scale = 0.0000254.toBigBigDecimal()
    }
}